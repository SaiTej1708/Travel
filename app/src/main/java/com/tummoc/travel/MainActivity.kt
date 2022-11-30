package com.tummoc.travel

import android.app.ActionBar.LayoutParams
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.gms.maps.model.LatLng
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.tummoc.travel.custom.Utils
import com.tummoc.travel.databinding.ActivityMainBinding
import com.tummoc.travel.databinding.MainCardViewBinding
import com.tummoc.travel.fragments.BusFragment
import com.tummoc.travel.fragments.MetroFragment
import com.tummoc.travel.mvvm.*
import com.tummoc.travel.travel.BusRouteDetails
import com.tummoc.travel.travel.Route
import com.tummoc.travel.travel.TravelDataItem
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: TravelViewModel

    private lateinit var mainBinding: ActivityMainBinding

    private var changeSelector =  MutableLiveData<String>()

    init {
        changeSelector.value = "Bus"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mainBinding.root)
        supportActionBar?.hide()
        mainBinding.chooseMetro.alpha = 1.0f
        mainBinding.chooseBus.alpha = 0.5f


        val dao = TravelDatabase.getInstance(this.applicationContext).travelDao
        val repository = TravelRepository(dao)
        val factory = TravelFactory(repository)
        viewModel = ViewModelProvider(this,factory)[TravelViewModel::class.java]

        val jsonString: String? =  Utils.getJsonFromAssets(this@MainActivity)
        val gson = Gson()
        val type = object : TypeToken<ArrayList<TravelDataItem>>(){}.type
        val list : ArrayList<TravelDataItem> = gson.fromJson(jsonString,type)
        configureFragment(MetroFragment.getInstance(list))

        mainBinding.chooseMetro.setOnClickListener {
            mainBinding.chooseMetro.alpha = 1.0f
            mainBinding.chooseBus.alpha = 0.5f
            changeSelector.value = "Metro"
            configureFragment(MetroFragment.getInstance(list))
        }
        mainBinding.chooseBus.setOnClickListener {
            mainBinding.chooseMetro.alpha = 0.5f
            mainBinding.chooseBus.alpha = 1.0f
            changeSelector.value = "Bus"
            configureFragment(BusFragment.getInstance(list))
        }

    }

    private fun configureFragment(fragment: Fragment){
        val manager = supportFragmentManager
        val transaction = manager.beginTransaction()
        transaction.replace(mainBinding.fragmentFrame.id,fragment,"Travel")
        transaction.commit()
    }


}