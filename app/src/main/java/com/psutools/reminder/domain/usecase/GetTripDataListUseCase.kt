package com.psutools.reminder.domain.usecase

import com.psutools.reminder.domain.model.TripData
import com.psutools.reminder.domain.repository.TripDataRepository
import javax.inject.Inject

interface GetTripDataListUseCase {

    suspend operator fun invoke(): List<TripData>
}

class GetTripDataListUseCaseImpl @Inject constructor(
    private val repository: TripDataRepository,
) : GetTripDataListUseCase {

    override suspend fun invoke(): List<TripData> {
        return repository.getTripDataList()
    }
}