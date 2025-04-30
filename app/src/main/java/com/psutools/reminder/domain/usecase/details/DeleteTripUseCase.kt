package com.psutools.reminder.domain.usecase.details

import com.psutools.reminder.domain.repository.details.TripDetailsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

interface DeleteTripUseCase {
    suspend operator fun invoke(tripId: String): Result<Unit>
}

class DeleteTripUseCaseImpl @Inject constructor(
    private val repository: TripDetailsRepository,
) : DeleteTripUseCase {

    override suspend operator fun invoke(tripId: String): Result<Unit> = withContext(Dispatchers.IO) {
        repository.deleteTrip(tripId)
    }
}