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
        fun fromValueTransport(value: String): TransportData {
            return try {
                valueOf(value.uppercase())
            } catch (e: IllegalArgumentException) {
                UNKNOWN
            }
        }

        fun fromValuesTransport(values: List<String>): List<TransportData> {
            return values.map { fromValueTransport(it) }
        }
    }
}