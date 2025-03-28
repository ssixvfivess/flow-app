package com.psutools.reminder.ui.sample.list.trip

import com.psutools.reminder.R
import com.psutools.reminder.base.ResourceProvider
import com.psutools.reminder.base.delegates.BaseListItem
import com.psutools.reminder.domain.model.trip.PointData
import com.psutools.reminder.domain.model.trip.TripData
import com.psutools.reminder.domain.model.trip.TripStatusData
import com.psutools.reminder.ui.sample.list.adapter.delegate.trip.TripCurrentDataItem
import com.psutools.reminder.ui.sample.list.adapter.delegate.trip.TripDataListItem
import com.psutools.reminder.ui.sample.list.adapter.delegate.trip.TripEmptyListMessage
import com.psutools.reminder.ui.sample.list.adapter.delegate.trip.TripHeadingListItem
import org.joda.time.format.DateTimeFormat
import java.util.Locale
import javax.inject.Inject

class TripDataListUiMapper @Inject constructor(
    private val resourceProvider: ResourceProvider
) {

    fun createListItems(dataList: List<TripData>): List<BaseListItem> {
        val items = mutableListOf<BaseListItem>()

        items.add(
            TripHeadingListItem(
                heading = resourceProvider.getString(R.string.active_heading),
                notificationIcon = true
            )
        )


        val activeTrips = dataList.filter { it.status == TripStatusData.ACTIVE }
        if (activeTrips.isEmpty()) {
            items.add(
                TripEmptyListMessage(
                    message = resourceProvider.getString(R.string.no_active_trips)
                )
            )
        } else {
            for (activeTrip in activeTrips) {
                items.add(
                    TripCurrentDataItem(
                        name = activeTrip.name,
                        route = getFullRouteAsString(activeTrip.route),
                        arrivalDateTime = resourceProvider.getString(R.string.now_text_widget)
                    )
                )
            }
        }

        items.add(
            TripHeadingListItem(
                heading = resourceProvider.getString(R.string.this_week_heading),
                notificationIcon = false
            )
        )

        val upcomingTrips = dataList.subtract(activeTrips)
        if (upcomingTrips.isEmpty()) {
            items.add(TripEmptyListMessage(message = resourceProvider.getString(R.string.no_trips_this_week)))
        } else {
            for (upcomingTrip in upcomingTrips) {
                items.add(
                    TripDataListItem(
                        name = upcomingTrip.name,
                        route = getFullRouteAsString(upcomingTrip.route),
                        arrivalDateTime = getRouteDate(upcomingTrip),
                        timeStart = getStartRouteTime(upcomingTrip)
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


//ЧЕРНОВИКИ//

//private fun getFirstRouteString(route: List<PointData>): String {
//    return route.first().name
//}
//
//private fun getLastRouteString(route: List<PointData>): String {
//    return route.last().name
//}

//items.addAll(dataList.map { data ->
//    TripDataListItem(
//        route = getFirstRouteString(data.route),
//        lastRoute = getLastRouteString(data.route)
//    )
//})


//    private fun getFullRouteAsString(route: List<PointData>): String {
//        val nameRoute = StringBuilder()
//        for (point in route){
//            nameRoute.append(point.name).append(" → ")
//        }
//
//        return nameRoute.removeSuffix(" → ").toString()
//    }
//}