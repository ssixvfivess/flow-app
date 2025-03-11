package com.psutools.reminder.domain.usecase.sample

import com.psutools.reminder.domain.model.sample.SampleData
import com.psutools.reminder.domain.repository.sample.SampleDataRepository
import javax.inject.Inject

interface GetSampleDataUseCase {

    suspend operator fun invoke(maxLength: Int): SampleData
}

class GetSampleDataUseCaseImpl @Inject constructor(
    private val repository: SampleDataRepository,
) : GetSampleDataUseCase {

    override suspend fun invoke(maxLength: Int): SampleData {
        return repository.getData(maxLength)
    }
}
