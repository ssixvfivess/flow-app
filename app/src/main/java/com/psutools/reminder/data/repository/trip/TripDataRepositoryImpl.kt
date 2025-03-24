package com.psutools.reminder.data.repository.trip

import com.psutools.reminder.data.mapper.trip.TripDataMapper
import com.psutools.reminder.data.service.trip.TripDataService
import com.psutools.reminder.domain.model.trip.TripData
import com.psutools.reminder.domain.repository.trip.TripDataRepository
import javax.inject.Inject

class TripDataRepositoryImpl @Inject constructor(
    private val mapper: TripDataMapper,
    private val service: TripDataService
) : TripDataRepository {

    override suspend fun getTripDataList(): List<TripData> {
        return mapper.toDomain(service.getTripsList().data)
    }
}
