package com.psutools.reminder.domain.model

import com.psutools.reminder.data.model.PointDataApi
import java.util.UUID


data class TripData(
    val id: UUID,
    val route: List<PointData>,
    val transportType: TransportData,
    val overtime: Int
)
