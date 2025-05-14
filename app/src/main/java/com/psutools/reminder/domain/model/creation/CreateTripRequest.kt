package com.psutools.reminder.domain.model.creation

import com.psutools.reminder.domain.model.trip.PointData
import com.psutools.reminder.domain.model.trip.TransportData
import org.joda.time.DateTime

data class CreateTripRequest (
    val route: List<PointData>,
    val transportType: List<TransportData>,
    val overtime: Int,
    val arrivalDateTime: DateTime,
    val name: String
)