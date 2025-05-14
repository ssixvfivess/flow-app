package com.psutools.reminder.domain.usecase.creation

import com.psutools.reminder.domain.model.creation.CreateTripRequest
import com.psutools.reminder.domain.repository.creation.CreateTripRepository
import javax.inject.Inject

interface CreateTripUseCase {
    suspend operator fun invoke(request: CreateTripRequest): Result<Unit>
}

class CreateTripUseCaseImpl @Inject constructor(
    private val repository: CreateTripRepository
) : CreateTripUseCase {
    override suspend operator fun invoke(request: CreateTripRequest): Result<Unit>{
        return repository.createTrip(request)
    }
}