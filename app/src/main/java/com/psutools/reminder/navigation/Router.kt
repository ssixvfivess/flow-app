package com.psutools.reminder.app.navigation

import android.content.Context
import android.content.Intent
import com.psutools.reminder.ui.presentation.details.sample.SampleDataDetailsActivity
import com.psutools.reminder.ui.sample.details.DetailsActivity
import java.util.UUID
import javax.inject.Inject
import javax.inject.Singleton


interface Router {
    fun createSampleDataDetailsIntent(context: Context, tripId: UUID): Intent
}

class RouterImpl @Inject constructor() : Router {
    override fun createSampleDataDetailsIntent(context: Context, tripId: UUID): Intent {
        return DetailsActivity.createIntent(context).apply {
            putExtra("TRIP_ID", tripId.toString())
        }
    }
}


//interface Router {
//
//    fun createSampleDataDetailsIntent(context: Context): Intent
//}
//
//class RouterImpl @Inject constructor() : Router {
//
//    override fun createSampleDataDetailsIntent(context: Context): Intent {
//        return SampleDataDetailsActivity.createIntent(context)
//    }
//}
