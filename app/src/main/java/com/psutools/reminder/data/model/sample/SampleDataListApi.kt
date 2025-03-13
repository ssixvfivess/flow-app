package com.psutools.reminder.data.model.sample

import kotlinx.serialization.Contextual
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SampleDataListApi(

    @SerialName("data")
    val data: @Contextual List<SampleDataApi>
)
