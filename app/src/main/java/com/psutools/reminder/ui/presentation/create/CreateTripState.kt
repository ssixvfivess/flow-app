package com.psutools.reminder.ui.presentation.create

data class CreateTripState(
    val routeAHint: String,
    val pointA: String = "",
    val routeBHint: String,
    val pointB: String = "",

    val selectedDate: String = "",
    val selectedTime: String = "",
    val selectedReminder: String = "",
)