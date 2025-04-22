package com.psutools.reminder.domain.usecase.details

import com.psutools.reminder.domain.model.details.TripDetailsData
import com.psutools.reminder.domain.model.trip.TripData
import com.psutools.reminder.domain.repository.details.TripDetailsRepository
import javax.inject.Inject

interface GetTripDetailsUseCase {

    suspend operator fun invoke(userId: String): TripDetailsData
}

class GetTripDetailsUseCaseImpl @Inject constructor(
    private val repository: TripDetailsRepository,
) : GetTripDetailsUseCase {

    override suspend fun invoke(userId: String): TripDetailsData {
        return repository.getTripData(userId)
    }
}