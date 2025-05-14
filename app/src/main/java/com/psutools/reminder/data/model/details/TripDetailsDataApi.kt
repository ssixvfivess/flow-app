package com.psutools.reminder.data.model.details

import com.psutools.reminder.data.model.trip.PointDataApi
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


//📌Дата-модели, сожержащие сырые данные данные из внешних источников (api/db)
@Serializable
data class TripDetailsDataApi(

    @SerialName("id")
    val id: String,

    //📌например тут id - строка, но по факту в domain уже будет uuid
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