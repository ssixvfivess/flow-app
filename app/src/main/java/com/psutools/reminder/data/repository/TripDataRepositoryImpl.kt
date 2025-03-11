package com.psutools.reminder.data.repository

import com.psutools.reminder.data.mapper.TripDataMapper
import com.psutools.reminder.data.service.TripDataService
import com.psutools.reminder.domain.model.TripData
import com.psutools.reminder.domain.repository.TripDataRepository
import javax.inject.Inject

class TripDataRepositoryImpl @Inject constructor(
    private val mapper: TripDataMapper,
    private val service: TripDataService
) : TripDataRepository {

    override suspend fun getTripDataList(): List<TripData> {
        return service.getAllTrips().map { mapper.toDomain(it) }
    }
}
