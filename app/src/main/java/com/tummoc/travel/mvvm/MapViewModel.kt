package com.tummoc.travel.mvvm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel


class MapViewModel : ViewModel() {

    private val source = MutableLiveData<String>()
    val sourceText : LiveData<String>
    get() = source

    private val destination = MutableLiveData<String>()
    val destinationText : LiveData<String>
        get() = destination

    init {
        source.value = "Select Source"
        destination.value = "Select Destination"
    }

}