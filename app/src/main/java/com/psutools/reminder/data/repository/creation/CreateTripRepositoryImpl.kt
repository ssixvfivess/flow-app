package com.psutools.reminder.data.repository.creation

import com.psutools.reminder.data.mapper.creation.CreateTripMapper
import com.psutools.reminder.data.service.trip.TripDataService
import com.psutools.reminder.domain.model.creation.CreateTripRequest
import com.psutools.reminder.domain.repository.creation.CreateTripRepository
import javax.inject.Inject

class CreateTripRepositoryImpl @Inject constructor(
    private val mapper: CreateTripMapper,
    private val service: TripDataService
) : CreateTripRepository {

    override suspend fun createTrip(request: CreateTripRequest): Result<Unit>{
        return try {
            val apiRequest = mapper.toApi(request) //маппим только запрос (домен -> API)
            val response = service.createTrip(apiRequest)

            if (response.success) {
                Result.success(Unit)
            } else {
                Result.failure(Exception("Failed to create trip"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}