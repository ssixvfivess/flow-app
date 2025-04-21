package com.psutools.reminder.data.model.details

import com.psutools.reminder.data.model.trip.PagingDataApi
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
class GetTripDetailsResponseApi (

    @SerialName("success")
    val success: Boolean,

    @SerialName("data")
    val data: TripDetailsDataApi,

    @SerialName("paging")
    val paging: PagingDataApi
)