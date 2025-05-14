package com.psutools.reminder.data.model.creation

import com.psutools.reminder.data.model.trip.PointDataApi
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CreateTripRequestApi (

    @SerialName("route")
    val route: List<PointDataApi>,

    @SerialName("transportType")
    val transportType: List<String>,

    @SerialName("overtime")
    val overtime: Int,

    @SerialName("arrivalDateTime")
    val arrivalDateTime: String,

    @SerialName("name")
    val name: String
)