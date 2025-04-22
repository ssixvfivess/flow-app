package com.psutools.reminder.data.repository.details

import com.psutools.reminder.data.mapper.details.TripDetailsMapper
import com.psutools.reminder.data.service.trip.TripDataService
import com.psutools.reminder.domain.model.details.TripDetailsData
import com.psutools.reminder.domain.repository.details.TripDetailsRepository
import javax.inject.Inject

class TripDetailsRepositoryImpl @Inject constructor(
    private val mapper: TripDetailsMapper,
    private val service: TripDataService
) : TripDetailsRepository {

    override suspend fun getTripData(userId: String): TripDetailsData {
        val test = service.getTrip(/*userId*/)
        return mapper.toDomain(test.data)
    }
}