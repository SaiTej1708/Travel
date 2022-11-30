package com.tummoc.travel

import android.graphics.drawable.Drawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.tummoc.travel.databinding.ActivityDetailBinding
import com.tummoc.travel.databinding.TransportCardBinding
import com.tummoc.travel.travel.Route
import com.tummoc.travel.travel.TravelDataItem

class MapActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding

    private var listOfRoutes : List<Route> = arrayListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()

        val type = object : TypeToken<TravelDataItem>(){}.type
        val item = Gson().fromJson<TravelDataItem>(intent.extras?.getString("Travel"),type)
        val mode = intent.extras?.getString("Mode").toString()
        listOfRoutes = item.routes
        val adapter = DetailAdapter(listOfRoutes,mode)
        binding.detailRecyclerView.layoutManager = LinearLayoutManager(this)
        binding.detailRecyclerView.adapter = adapter

    }

    inner class DetailAdapter(list: List<Route>, mode: String) : RecyclerView.Adapter<DetailAdapter.DetailHolder>(){

        private var listOfItems = list

        private var type = mode

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DetailHolder {
           val view = LayoutInflater.from(parent.context).inflate(R.layout.transport_card,parent,false)
            return DetailHolder(view)
        }
        private fun getIcon(id: Int) : Drawable? {
            return ContextCompat.getDrawable(this@MapActivity,id)
        }

        override fun onBindViewHolder(holder: DetailHolder, position: Int) {
           for (route in listOfItems){

           }
        }

        override fun getItemCount(): Int {
            var itemCount = 0
            if (listOfItems.size == 4){
                val walk = listOfItems[0]
                val busAndMetro = listOfItems[1]
                val busOrMetro = listOfItems[2]
                val walkAgain = listOfItems[3]

                var noOfBuses : Int = 0
                var noOfTrails : Int = 0

                if (walk.medium == "Walk"){
                    itemCount++
                }
                if (busAndMetro.trails != null){
                    itemCount += busAndMetro.trails.size
                    itemCount += 1
                }else if (busAndMetro.busRouteDetails != null){
                    itemCount += 1
                }

                return itemCount
            }else {
                return itemCount
            }
        }

        inner class DetailHolder(view : View) : RecyclerView.ViewHolder(view) {
              var binding1 = TransportCardBinding.bind(view)
        }
    }


}