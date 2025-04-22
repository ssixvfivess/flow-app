package com.psutools.reminder.data.model.trip

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PointDataApi (

    @SerialName("name")
    val name: String,

    @SerialName("latitude")
    val latitude: Float,

    @SerialName("longitude")
    val longitude: Float,

    @SerialName("stopTime")
    val stopTime: Int,

    @SerialName("address")
    val address: String
)