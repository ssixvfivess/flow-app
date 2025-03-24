package com.psutools.reminder.domain.repository.trip

import com.psutools.reminder.domain.model.trip.TripData


interface TripDataRepository {

    suspend fun getTripDataList(): List<TripData>
}