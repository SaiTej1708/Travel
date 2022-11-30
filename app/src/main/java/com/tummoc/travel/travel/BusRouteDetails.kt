package com.tummoc.travel.travel


import com.google.gson.annotations.SerializedName

data class BusRouteDetails(
    @SerializedName("routeDescription")
    val routeDescription: String,
    @SerializedName("routeId")
    val routeId: Any? = "RouteId Not Found",
    @SerializedName("routeName")
    val routeName: String?,
    @SerializedName("routeNumber")
    val routeNumber: String
) {
    constructor() : this("Not Found","RouteId Not Found","Not Found","Not Found")
}