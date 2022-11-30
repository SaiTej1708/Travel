package com.tummoc.travel.fragments

import android.content.Intent
import android.content.res.Resources
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.tummoc.travel.R
import com.tummoc.travel.databinding.FragmentMetroBinding
import com.tummoc.travel.databinding.MainCardViewBinding
import com.tummoc.travel.mvvm.MyCardView
import com.tummoc.travel.travel.Route
import com.tummoc.travel.travel.TravelDataItem


class MetroFragment(list: ArrayList<TravelDataItem>) : Fragment() {

    private lateinit var binding: FragmentMetroBinding

    private var listOfItems = list

    companion object {
        fun getInstance(list: ArrayList<TravelDataItem>): Fragment {
            return MetroFragment(list)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentMetroBinding.inflate(inflater,container,false)
        val view = binding.root

        binding.metroRecycler.layoutManager = LinearLayoutManager(requireActivity())
        binding.metroRecycler.adapter = MyAdapter(listOfItems)

        return view
    }
    private fun dpToPx(dp: Int): Int {
        return (dp * Resources.getSystem().displayMetrics.density).toInt()
    }
    fun goToMapActivity(lat: Double,long: Double){
        val intentUri: Uri = Uri.parse("geo:$lat, $long")
        val intent = Intent(Intent.ACTION_VIEW, intentUri)
        intent.setPackage("com.google.android.apps.maps");

    }
    fun launchMap(lat1: Double,long1: Double,lat2: Double,long2: Double){
        val uri = "http://maps.google.com/maps?f=d&hl=en&saddr=$lat1,$long1&daddr=$lat2,$long2"
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(uri))
        startActivity(Intent.createChooser(intent, "Select an application"))
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
            val metroCard = myCardView.getMetroCard(item)
            holder.mainCardViewBinding.customLayout.addView(metroCard)

            holder.itemView.setOnClickListener {
               launchMap(starting.sourceLat,starting.sourceLong,ending.sourceLat,ending.sourceLong)
            }
        }

        override fun getItemCount(): Int = items.size

        inner class MyViewHolder(view: View): RecyclerView.ViewHolder(view){
            val mainCardViewBinding = MainCardViewBinding.bind(view)
        }
    }
}