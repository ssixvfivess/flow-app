package com.psutools.reminder.data.mapper.sample

import com.psutools.reminder.data.model.sample.SampleDataApi
import com.psutools.reminder.data.model.sample.SampleDataListApi
import com.psutools.reminder.domain.model.sample.SampleData
import javax.inject.Inject

class SampleDataMapper @Inject constructor() {

    fun toDomain(apiModel: SampleDataApi): SampleData {
        return SampleData(
            text = apiModel.text,
            length = apiModel.length,
        )
    }

    fun toDomain(apiModel: SampleDataListApi): List<SampleData> {
        return apiModel.data.map { toDomain(it) }
    }
}
