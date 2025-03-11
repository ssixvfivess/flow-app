package com.psutools.reminder.domain.model

import com.psutools.reminder.data.model.PointDataApi


data class TripData(
    val id: String,
    val route: PointDataApi,
    val transportType: String,
    val overtime: Int
)