package com.psutools.reminder.data.model.trip

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class TripDataApi(

    @SerialName("id")
    val id: String,

    @SerialName("userId")
    val userId: String,

    @SerialName("status")
    val status: String,

    @SerialName("name")
    val name: String,

    @SerialName("route")
    val route: List<PointDataApi>,

    @SerialName("transportType")
    val transportType: List<String>,

    @SerialName("overtime")
    val overtime: Int,

    @SerialName("arrivalDateTime")
    val arrivalDateTime: String,

    @SerialName("departureDateTime")
    val departureDateTime: String,

    @SerialName("routeTimes")
    val routeTimes: List<Int>,

    @SerialName("displayRouteTimes")
    val displayRouteTimes: List<String>
)