package com.psutools.reminder.app.navigation

import android.content.Context
import android.content.Intent
import com.psutools.reminder.ui.fragments.HomeFragment
import com.psutools.reminder.ui.sample.details.sample.SampleDataDetailsActivity
import javax.inject.Inject

interface Router {

    fun createSampleDataDetailsIntent(context: Context): Intent

    fun createSampleDataListIntent(context: Context): Intent
}

class RouterImpl @Inject constructor() : Router {

    override fun createSampleDataDetailsIntent(context: Context): Intent {
        return SampleDataDetailsActivity.createIntent(context)
    }

    override fun createSampleDataListIntent(context: Context): Intent {
        return HomeFragment.createIntent(context)
    }
}
