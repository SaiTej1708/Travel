package com.tummoc.travel.travel

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "travel_data_table")
data class TravelDataItem(

    @PrimaryKey(autoGenerate = true)
    val id : Int,
    @SerializedName("routeTitle")
    val routeTitle: String,
    @SerializedName("routes")
    val routes: List<Route>,
    @SerializedName("totalDistance")
    val totalDistance: Double,
    @SerializedName("totalDuration")
    val totalDuration: String,
    @SerializedName("totalFare")
    val totalFare: Double
)