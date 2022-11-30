package com.tummoc.travel.mvvm

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.tummoc.travel.travel.TravelDataItem

@Database(entities = [TravelItem::class,TravelDataItem::class], version = 1)
@TypeConverters(Converters::class)
abstract class TravelDatabase : RoomDatabase()  {

   abstract val travelDao : TravelDao

   companion object {
      @Volatile
      private var INSTANCE : TravelDatabase? = null

      fun getInstance(context: Context) : TravelDatabase {
         synchronized(this){
            var instance = INSTANCE
            if (instance == null){
               instance =  Room.databaseBuilder(
                  context.applicationContext,
                  TravelDatabase::class.java,
                  "travel_database"
               ).build()
            }
            return instance
         }
      }
   }
}