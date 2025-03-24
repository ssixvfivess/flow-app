package com.psutools.reminder.data.model.trip

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class TripDataApi(

    @SerialName("id")
    val id: String,

    @SerialName("route")
    val route: List<PointDataApi>,

    @SerialName("transportType")
    val transportType: String,

    @SerialName("overtime")
    val overtime: Int
)