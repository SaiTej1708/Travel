package com.tummoc.travel.travel


import com.google.gson.annotations.SerializedName

data class Trail(
    @SerializedName("distance")
    val distance: Double,
    @SerializedName("fareStage")
    val fareStage: String,
    @SerializedName("latitude")
    val latitude: Double,
    @SerializedName("longitude")
    val longitude: Double,
    @SerializedName("name")
    val name: String,
    @SerializedName("seq")
    val seq: Int,
    @SerializedName("time")
    val time: Any?
)