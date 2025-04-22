package com.psutools.reminder.app.navigation

import android.content.Context
import android.content.Intent
import com.psutools.reminder.ui.presentation.details.sample.SampleDataDetailsActivity
import com.psutools.reminder.ui.sample.details.DetailsActivity
import java.util.UUID
import javax.inject.Inject
import javax.inject.Singleton


interface Router {
    fun createDataDetailsIntent(context: Context, tripId: String): Intent
}

class RouterImpl @Inject constructor() : Router {
    override fun createDataDetailsIntent(context: Context, tripId: String): Intent {
        return DetailsActivity.createIntent(context).apply {
            putExtra("TRIP_ID", tripId)
        }
    }
}
