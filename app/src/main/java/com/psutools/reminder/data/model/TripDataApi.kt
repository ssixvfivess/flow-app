package com.psutools.reminder.data.model

import com.psutools.reminder.domain.model.PointData
import com.psutools.reminder.domain.model.TransportData
import kotlinx.serialization.KSerializer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import java.util.UUID

@Serializable
data class TripDataApi(

    @SerialName("id")
    val id: String,

    @SerialName("route")
    val route: List<PointDataApi>,

    @SerialName("transportType")
    val transportType: String,

    @SerialName("overtime")
    val overtime: Int
)