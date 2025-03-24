package com.psutools.reminder.data.model.trip

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PagingDataApi(

    @SerialName("sorted")
    val sorted: Boolean,

    @SerialName("pageNumber")
    val pageNumber: Int,

    @SerialName("numberOfElements")
    val numberOfElements: Int,

    @SerialName("totalElement")
    val totalElement: Int,

    @SerialName("last")
    val last: Boolean
)