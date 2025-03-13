package com.psutools.reminder.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class PointData (
    val name: String,
    val latitude: Float,
    val longitude: Float,
    val stopTime: Int
)
