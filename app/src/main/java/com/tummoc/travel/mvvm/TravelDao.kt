package com.tummoc.travel.mvvm

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.tummoc.travel.travel.TravelDataItem

@Dao
interface TravelDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(travelDataItem: TravelItem)

    @Query("SELECT * FROM travel_table")
    fun getAllItems() : LiveData<TravelItem>

}