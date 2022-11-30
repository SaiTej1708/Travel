package com.tummoc.travel.mvvm

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.tummoc.travel.travel.BusRouteDetails
import com.tummoc.travel.travel.Route
import com.tummoc.travel.travel.Trail
import com.tummoc.travel.travel.TravelDataItem
import java.lang.reflect.Type

@Entity(tableName = "travel_table")
data class TravelItem (

        @PrimaryKey(autoGenerate = true)
        val id: Int = 0 ,

        var item: TravelDataItem

        )

class Converters {

        @TypeConverter
        fun fromTravelItem(travelDataItems: TravelDataItem) : String {
           return Gson().toJson(travelDataItems)
        }

        @TypeConverter
        fun stringToTravelItem(travelString : String) : TravelDataItem {
           val type : Type = object : TypeToken<TravelDataItem>(){}.type
           return Gson().fromJson(travelString,type)
        }
        @TypeConverter
        fun fromListRoutes(listOfRoutes : List<Route>) : String {
            return Gson().toJson(listOfRoutes)
        }

        @TypeConverter
        fun stringToListOfRoutes(listOfRoutes : String) : List<Route> {
            val type : Type = object : TypeToken<List<Route>>(){}.type
            return Gson().fromJson(listOfRoutes,type)
        }
        @TypeConverter
        fun fromListOfTrails(listOfTrails: List<Trail>) : String {
            return Gson().toJson(listOfTrails)
        }

        @TypeConverter
        fun stringToListOfTrails(listOfTrails : String) : List<Trail> {
            val type : Type = object : TypeToken<List<Trail>>(){}.type
            return Gson().fromJson(listOfTrails,type)
        }

        @TypeConverter
        fun fromBusRoute(busRouteDetails: BusRouteDetails) : String {
            return Gson().toJson(busRouteDetails)
        }
        @TypeConverter
        fun stringToBusRoute(busRoute : String) : BusRouteDetails {
            val type : Type = object : TypeToken<BusRouteDetails>(){}.type
            return Gson().fromJson(busRoute,type)
        }
        @TypeConverter
        fun fromTrail(trail: Trail) : String {
            return Gson().toJson(trail)
        }
        @TypeConverter
        fun stringToTrail(trail : String) : Trail {
            val type : Type = object : TypeToken<Trail>(){}.type
            return Gson().fromJson(trail,type)
        }
        @TypeConverter
        fun fromListOfStrings(list: List<String>) : String {
            return Gson().toJson(list)
        }
        @TypeConverter
        fun stringToList(string : String) : List<String>{
            val type : Type = object : TypeToken<List<String>>(){}.type
            return Gson().fromJson(string,type)
        }


}