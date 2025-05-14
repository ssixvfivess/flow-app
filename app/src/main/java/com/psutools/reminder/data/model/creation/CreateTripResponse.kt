package com.psutools.reminder.data.model.creation

import com.psutools.reminder.data.model.trip.PagingDataApi
import com.psutools.reminder.data.model.trip.TripDataApi
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
class CreateTripResponse (

    @SerialName("success")
    val success: Boolean,

    @SerialName("data")
    val data: TripDataApi,

    @SerialName("paging")
    val paging: PagingDataApi? = null

)