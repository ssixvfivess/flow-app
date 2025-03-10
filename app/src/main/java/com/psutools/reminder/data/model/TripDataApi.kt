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

    @Serializable(with = UUIDSerializer::class)
    @SerialName("id")
    val id: UUID,

    @SerialName("route")
    val route: List<PointData>,

    @SerialName("transportType")
    val transportType: TransportData,

    @SerialName("overtime")
    val overtime: Int
)

object UUIDSerializer : KSerializer<UUID> {
    override val descriptor = PrimitiveSerialDescriptor("UUID", PrimitiveKind.STRING)

    override fun deserialize(decoder: Decoder): UUID {
        return UUID.fromString(decoder.decodeString())
    }

    override fun serialize(encoder: Encoder, value: UUID) {
        encoder.encodeString(value.toString())
    }
}