package com.psutools.reminder.domain.model.trip

import org.joda.time.DateTime
import java.util.UUID


data class TripData(
    val id: UUID,
    val userId: UUID,
    val status: TripStatusData,
    val route: List<PointData>,
    val transportType: TransportData,
    val overtime: Int,
    val arrivalDateTime: DateTime,
    val departureDateTime: DateTime
)
