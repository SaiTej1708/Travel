package com.tummoc.travel.mvvm

import androidx.lifecycle.LiveData
import com.tummoc.travel.travel.TravelDataItem

class TravelRepository(private val travelDao: TravelDao) {

    suspend fun insert(travelDataItem: TravelItem){
        travelDao.insert(travelDataItem)
    }

    suspend fun getAllItems() : LiveData<TravelItem>{
        return travelDao.getAllItems()
    }

}