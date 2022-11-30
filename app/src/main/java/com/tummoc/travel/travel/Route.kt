package com.tummoc.travel.travel

import com.google.gson.annotations.SerializedName

data class Route(
    @SerializedName("busRouteDetails")
    val busRouteDetails: BusRouteDetails? = BusRouteDetails(),
    @SerializedName("destinationLat")
    val destinationLat: Double,
    @SerializedName("destinationLong")
    val destinationLong: Double,
    @SerializedName("destinationTime")
    val destinationTime: List<String>?,
    @SerializedName("destinationTitle")
    val destinationTitle: String,
    @SerializedName("distance")
    val distance: Double,
    @SerializedName("duration")
    val duration: String,
    @SerializedName("fare")
    val fare: Double,
    @SerializedName("medium")
    val medium: String,
    @SerializedName("rideEstimation")
    val rideEstimation: Any?,
    @SerializedName("routeName")
    val routeName: String?,
    @SerializedName("sourceLat")
    val sourceLat: Double,
    @SerializedName("sourceLong")
    val sourceLong: Double,
    @SerializedName("sourceTime")
    val sourceTime: List<String>,
    @SerializedName("sourceTitle")
    val sourceTitle: String,
    @SerializedName("trails")
    val trails: List<Trail>?
)