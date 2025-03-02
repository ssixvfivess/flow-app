package com.psutools.reminder.domain.usecase

import com.psutools.reminder.domain.model.SampleData
import com.psutools.reminder.domain.repository.SampleDataRepository
import javax.inject.Inject

interface GetSampleDataListUseCase {

    suspend operator fun invoke(maxLength: Int): List<SampleData>
}

class GetSampleDataListUseCaseImpl @Inject constructor(
    private val repository: SampleDataRepository,
) : GetSampleDataListUseCase {

    override suspend fun invoke(maxLength: Int): List<SampleData> {
        return repository.getDataList(maxLength)
    }
}
