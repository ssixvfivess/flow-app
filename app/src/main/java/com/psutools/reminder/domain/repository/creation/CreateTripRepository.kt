package com.psutools.reminder.domain.repository.creation

import com.psutools.reminder.domain.model.creation.CreateTripRequest

interface CreateTripRepository {
    suspend fun createTrip(request: CreateTripRequest): Result<Unit>
}
