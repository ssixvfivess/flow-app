package com.psutools.reminder.ui.presentation.routes

import com.psutools.reminder.R
import com.psutools.reminder.base.ResourceProvider
import com.psutools.reminder.base.delegates.BaseListItem
import com.psutools.reminder.domain.model.trip.PointData
import com.psutools.reminder.domain.model.trip.TripData
import com.psutools.reminder.ui.presentation.list.adapter.delegate.trip.TripDataListItem
import com.psutools.reminder.ui.presentation.list.adapter.delegate.trip.TripEmptyListMessage
import org.joda.time.format.DateTimeFormat
import java.util.Locale
import javax.inject.Inject

class RoutesDataListUiMapper @Inject constructor(
    private val resourceProvider: ResourceProvider
) {

    fun createListItems(dataList: List<TripData>): List<BaseListItem> {
        val items = mutableListOf<BaseListItem>()

        //дата у выбранного дня на календаре


        if (dataList.isEmpty()) {
            items.add(TripEmptyListMessage(message = resourceProvider.getString(R.string.no_trips_this_day)))
        } else {
            for (trip in dataList) {
                items.add(
                    TripDataListItem(
                        tripId = trip.id.toString(),
                        name = trip.name,
                        route = getFullRouteAsString(trip.route),
                        arrivalDateTime = getRouteDate(trip),
                        timeStart = getStartRouteTime(trip)
                    )
                )
            }
        }

        return items
    }

    private fun getFullRouteAsString(route: List<PointData>): String {
        return if (route.size > 2) {
            "${route.first().name} → ... → ${route.last().name}"
        } else {
            route.joinToString(" → ") { it.name }
        }
    }

    private fun getRouteDate(data: TripData): String {
        val formatter = DateTimeFormat.forPattern("EEEE, d MMMM").withLocale(Locale("ru"))
        return formatter.print(data.arrivalDateTime)
    }

    private fun getStartRouteTime(data: TripData): String {
        val formatter = DateTimeFormat.forPattern("HH:mm")
        return formatter.print(data.arrivalDateTime)
    }
}