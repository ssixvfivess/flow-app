package com.psutools.reminder.domain.model

import kotlinx.serialization.Serializable

@Serializable
enum class TransportData {
    CAR,
    TAXI,
    WALK,
    BICYCLE,
    SCOOTER,
    PUBLIC_TRANSPORT;

    fun lowerCase(): String {
        return this.name.lowercase()
    }
}