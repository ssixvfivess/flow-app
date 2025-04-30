package com.psutools.reminder.domain.repository.details

import com.psutools.reminder.domain.model.details.TripDetailsData

interface TripDetailsRepository {

    suspend fun getTripData(tripId: String): TripDetailsData

    suspend fun deleteTrip(tripId: String): Result<Unit>
}