package com.psutools.reminder.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
enum class TransportDataApi {
    @SerialName("CAR")
    CAR,

    @SerialName("TAXI")
    TAXI,

    @SerialName("WALK")
    WALK,

    @SerialName("BICYCLE")
    BICYCLE,

    @SerialName("SCOOTER")
    SCOOTER,

    @SerialName("PUBLIC_TRANSPORT")
    PUBLIC_TRANSPORT
}