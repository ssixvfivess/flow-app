package com.psutools.reminder.domain.repository

import com.psutools.reminder.domain.model.sample.SampleData

interface SampleDataRepository {

    suspend fun getData(maxLength: Int): SampleData

    suspend fun getDataList(maxLength: Int): List<SampleData>
}
