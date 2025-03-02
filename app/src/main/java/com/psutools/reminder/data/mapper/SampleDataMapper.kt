package com.psutools.reminder.data.mapper

import com.psutools.reminder.data.model.SampleDataApi
import com.psutools.reminder.data.model.SampleDataListApi
import com.psutools.reminder.domain.model.SampleData
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
