package com.psutools.reminder.data.model.trip

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GetTripsListResponseApi(

    @SerialName("success")
    val success: Boolean,

    @SerialName("data")
    val data: List<TripDataApi>,

    @SerialName("paging")
    val paging: PagingDataApi
)
