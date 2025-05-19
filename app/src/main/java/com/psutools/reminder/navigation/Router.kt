package com.psutools.reminder.app.navigation

import android.content.Context
import android.content.Intent
import com.psutools.reminder.ui.sample.details.DetailsActivity
import javax.inject.Inject

//📌Создает Intent для перехода между Activity/Fragment
interface Router {
    fun createRouteDetailsIntent(context: Context, tripId: String): Intent
}

class RouterImpl @Inject constructor() : Router {
    //📌tripId: String — параметр для передачи на следующий экран
    override fun createRouteDetailsIntent(context: Context, tripId: String): Intent {
        return DetailsActivity.createIntent(context).apply {
            //📌Скрывает детали реализации навигации (ключи для putExtra)
            putExtra(TRIP_ID, tripId)
        }
    }

    private companion object {
        const val TRIP_ID = "tripId"
    }
}
