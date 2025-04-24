package com.psutools.reminder.data.model.trip

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PagingDataApi(

    @SerialName("sorted")
    val sorted: Boolean,

    @SerialName("pageNumber")
    val pageNumber: Int,

    @SerialName("pageSize")
    val pageSize: Int,

    @SerialName("totalElements")
    val totalElements: Int,

    @SerialName("last")
    val last: Boolean
)