package com.psutools.reminder.domain.model.trip

import java.util.UUID


data class TripData(
    val id: UUID,
    val route: List<PointData>,
    val transportType: TransportData,
    val overtime: Int
)
