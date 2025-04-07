package com.psutools.reminder.app.navigation

import android.content.Context
import android.content.Intent
import com.psutools.reminder.ui.presentation.details.sample.SampleDataDetailsActivity
import javax.inject.Inject

interface Router {

    fun createSampleDataDetailsIntent(context: Context): Intent
}

class RouterImpl @Inject constructor() : Router {

    override fun createSampleDataDetailsIntent(context: Context): Intent {
        return SampleDataDetailsActivity.createIntent(context)
    }
}
