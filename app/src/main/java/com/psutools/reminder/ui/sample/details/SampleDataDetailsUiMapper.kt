package com.psutools.reminder.ui.sample.details

import com.psutools.reminder.domain.model.SampleData
import javax.inject.Inject

class SampleDataDetailsUiMapper @Inject constructor() {

    fun createDetails(data: SampleData): String {
        return data.text
    }
}
