package com.psutools.reminder.data.model.sample

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SampleDataApi(

    @SerialName("fact")
    val text: String,

    @SerialName("length")
    val length: Int,
)
