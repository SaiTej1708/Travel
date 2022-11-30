package com.tummoc.travel.fragments

import android.animation.ObjectAnimator
import android.content.Intent
import android.content.res.Resources
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.view.animation.Transformation
import android.widget.LinearLayout
import android.widget.LinearLayout.LayoutParams
import androidx.cardview.widget.CardView
import androidx.core.view.setMargins
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import com.tummoc.travel.MapActivity
import com.tummoc.travel.R
import com.tummoc.travel.databinding.FragmentBusBinding
import com.tummoc.travel.databinding.MainCardViewBinding
import com.tummoc.travel.mvvm.MyCardView
import com.tummoc.travel.travel.Route
import com.tummoc.travel.travel.TravelDataItem
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class BusFragment(list: ArrayList<TravelDataItem>) : Fragment() {

    private lateinit var binding: FragmentBusBinding

    private var listOfItems = list

    companion object {
        fun getInstance(list: ArrayList<TravelDataItem>) : Fragment {
            return BusFragment(list)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentBusBinding.inflate(inflater,container,false)
        // Inflate the layout for this fragment
        val view = binding.root

        binding.busRecycler.layoutManager = LinearLayoutManager(requireActivity())
        binding.busRecycler.adapter = MyAdapter(listOfItems)

        return view
    }
    private fun dpToPx(dp: Int): Int {
        return (dp * Resources.getSystem().displayMetrics.density).toInt()
    }
    fun goToMapActivity(travelDataItem: TravelDataItem,filter: String){
        val intent = Intent(requireActivity(), MapActivity::class.java)
        val jsonString = Gson().toJson(travelDataItem)
        intent.putExtra("Mode",filter)
        intent.putExtra("Travel",jsonString)
        startActivity(intent)
    }
    inner class MyAdapter(list: ArrayList<TravelDataItem>) : RecyclerView.Adapter<MyAdapter.MyViewHolder>() {

        private var items = list

        private var myCardView : MyCardView = MyCardView(requireActivity())

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
            val view = LayoutInflater.from(parent.context).inflate(R.layout.main_card_view,parent,false)
            return MyViewHolder(view)
        }

        override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
            val item = items[position]
            val routes : List<Route> = item.routes
            val starting : Route = routes.first()
            val ending : Route = routes.last()

            holder.mainCardViewBinding.source.text = starting.sourceTitle
            holder.mainCardViewBinding.destination.text = ending.sourceTitle
            val busCard = myCardView.getBusCard(item)
            holder.mainCardViewBinding.customLayout.addView(busCard)

            holder.itemView.setOnClickListener {
                CoroutineScope(Dispatchers.Main).launch {
                    val scaleAnimation = AnimationUtils.loadAnimation(requireActivity(),R.anim.scale_card_view)
                    holder.itemView.startAnimation(scaleAnimation)
                    goToMapActivity(item,"Bus")
                }
            }
        }

        override fun getItemCount(): Int = items.size

        inner class MyViewHolder(view: View): RecyclerView.ViewHolder(view){
            val mainCardViewBinding = MainCardViewBinding.bind(view)
        }
    }

}