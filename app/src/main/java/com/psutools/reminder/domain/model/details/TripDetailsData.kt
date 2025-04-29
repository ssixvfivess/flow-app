package com.psutools.reminder.domain.model.details

import com.psutools.reminder.domain.model.trip.PointData
import com.psutools.reminder.domain.model.trip.TransportData
import com.psutools.reminder.domain.model.trip.TripStatusData
import org.joda.time.DateTime
import java.util.UUID

data class TripDetailsData(
    val id: UUID,
    val userId: UUID,
    val status: TripStatusData,
    val name: String,
    val route: List<PointData>,
    val transportType: List<TransportData>,
    val overtime: Int,
    val departureDateTime: DateTime,
    val arrivalDateTime: DateTime,
    val routeTimes: List<Int>,
    val displayRouteTimes: List<String>
)
