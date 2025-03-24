package com.psutools.reminder.domain.model.trip

enum class TransportData {
    CAR,
    TAXI,
    WALK,
    BICYCLE,
    SCOOTER,
    PUBLIC_TRANSPORT,
    UNKNOWN;

    companion object {
        fun fromValue(value: String) : TransportData =
            TransportData.entries.find { it.name == value } ?: UNKNOWN
    }
}