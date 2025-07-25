package com.psutools.reminder.ui.presentation.details.sample

import com.psutools.reminder.domain.model.sample.SampleData
import javax.inject.Inject

class SampleDataDetailsUiMapper @Inject constructor() {

    fun createDetails(data: SampleData): String {
        return data.text
    }
}
