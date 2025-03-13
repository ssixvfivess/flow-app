package com.psutools.reminder.domain.repository

import com.psutools.reminder.domain.model.TripData


interface TripDataRepository {

    suspend fun getTripDataList(): List<TripData>
}