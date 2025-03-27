package com.psutools.reminder.domain.model.trip

enum class TripStatusData {
    ACTIVE,
    UPCOMING,
    PAST,
    UNKNOWN;

    companion object {
        fun fromValueStatus(value: String) : TripStatusData =
            TripStatusData.entries.find { it.name == value.uppercase() } ?: UNKNOWN
    }
}