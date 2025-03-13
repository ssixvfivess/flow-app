package com.psutools.reminder.data.repository.sample

import com.psutools.reminder.data.mapper.sample.SampleDataMapper
import com.psutools.reminder.data.service.sample.SampleDataService
import com.psutools.reminder.domain.model.sample.SampleData
import com.psutools.reminder.domain.repository.sample.SampleDataRepository
import javax.inject.Inject

class SampleDataRepositoryImpl @Inject constructor(
    private val mapper: SampleDataMapper,
    private val service: SampleDataService
) : SampleDataRepository {

    override suspend fun getData(maxLength: Int): SampleData {
        return mapper.toDomain(service.getFact(maxLength))
    }

    override suspend fun getDataList(maxLength: Int): List<SampleData> {
        return mapper.toDomain(service.getFactsList(maxLength))
    }
}
