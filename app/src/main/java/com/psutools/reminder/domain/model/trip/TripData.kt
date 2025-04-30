package com.psutools.reminder.domain.model.trip

import org.joda.time.DateTime
import java.util.UUID

data class TripData(
    val id: UUID,
    val userId: UUID,
    val status: TripStatusData,
    val name: String,
    val route: List<PointData>,
    val transportType: List<TransportData>,
    val overtime: Int,
    val arrivalDateTime: DateTime,
    val departureDateTime: DateTime,
    val routeTimes: List<Int>,
    val displayRouteTimes: List<String>
)
