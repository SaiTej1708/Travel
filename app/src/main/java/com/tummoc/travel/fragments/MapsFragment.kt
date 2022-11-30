package com.tummoc.travel.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.gms.maps.model.PolylineOptions
import com.google.android.libraries.places.api.Places
import com.tummoc.travel.R


class MapsFragment(listOfLocations: ArrayList<LatLng>) : Fragment() {

    private var locations : ArrayList<LatLng>

    init {
        this.locations = listOfLocations
    }

    companion object{
        fun getInstance(listOfLocations: ArrayList<LatLng>) : Fragment {
            return MapsFragment(listOfLocations)
        }
    }


    private val callback = OnMapReadyCallback { map ->

        val polylineOptions = PolylineOptions().addAll(locations)
        map.addPolyline(polylineOptions)
        map.addMarker(MarkerOptions().position(locations.first()))
        map.addMarker(MarkerOptions().position(locations.last()))
        map.animateCamera(CameraUpdateFactory.newLatLngZoom(locations.first(),10f))
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        if (!Places.isInitialized()){
            activity?.applicationContext?.let { Places.initialize(it,"AIzaSyC1RfNtzGQuDZFk5TuctpsXKZABXB8-IQs") }
        }

        return inflater.inflate(R.layout.fragment_maps, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment?
        mapFragment?.getMapAsync(callback)
    }

    private fun getDirectionURL(origin:LatLng, dest:LatLng, secret: String) : String{
        return "https://maps.googleapis.com/maps/api/directions/json?origin=${origin.latitude},${origin.longitude}" +
                "&destination=${dest.latitude},${dest.longitude}"+
                "&waypoints=optimize:true"+
                "&key=AIzaSyC1RfNtzGQuDZFk5TuctpsXKZABXB8-IQs"
    }

}


