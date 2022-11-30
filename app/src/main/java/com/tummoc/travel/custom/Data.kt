package com.tummoc.travel.custom

import android.content.Context
import com.google.gson.Gson
import com.tummoc.travel.travel.TravelDataItem
import java.io.IOException
import java.nio.charset.Charset


object Utils {
    fun getJsonFromAssets(context: Context): String? {
        val jsonString: String = try {
            val `is` = context.assets.open("response.json")
            val size = `is`.available()
            val buffer = ByteArray(size)
            `is`.read(buffer)
            `is`.close()
            String(buffer, Charset.forName("UTF-8"))
        } catch (e: IOException) {
            e.printStackTrace()
            return null
        }
        return jsonString
    }
}