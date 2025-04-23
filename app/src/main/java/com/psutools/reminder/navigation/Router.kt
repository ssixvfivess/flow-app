package com.psutools.reminder.app.navigation

import android.content.Context
import android.content.Intent
import com.psutools.reminder.ui.sample.details.DetailsActivity
import javax.inject.Inject


interface Router {
    fun createRouteDetailsIntent(context: Context, tripId: String): Intent
}

class RouterImpl @Inject constructor() : Router {
    override fun createRouteDetailsIntent(context: Context, tripId: String): Intent {
        return DetailsActivity.createIntent(context).apply {
            putExtra("tripId", tripId)
        }
    }
}
