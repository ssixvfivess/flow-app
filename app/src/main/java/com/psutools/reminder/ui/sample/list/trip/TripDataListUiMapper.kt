package com.psutools.reminder.ui.sample.list.trip

import com.psutools.reminder.base.delegates.BaseListItem
import com.psutools.reminder.domain.model.trip.PointData
import com.psutools.reminder.domain.model.trip.TripData
import com.psutools.reminder.ui.sample.list.adapter.delegate.trip.TripCurrentDataItem
import com.psutools.reminder.ui.sample.list.adapter.delegate.trip.TripDataListItem
import com.psutools.reminder.ui.sample.list.adapter.delegate.trip.TripHeadingListItem
import javax.inject.Inject

class TripDataListUiMapper @Inject constructor() {

    fun createListItems(dataList: List<TripData>): List<BaseListItem> {
        val items = mutableListOf<BaseListItem>()

        items.add(TripHeadingListItem("Активные", notificationIcon = true))


        val activeTripData = dataList.first()
        items.add(
            TripCurrentDataItem(route = getFullRouteAsString(activeTripData.route))
            )


        items.add(TripHeadingListItem("На этой неделе", notificationIcon = false))


        items.addAll(dataList.map { data ->
            TripDataListItem(
                route = getFullRouteAsString(data.route)
            )
        })

        return items
    }

    private fun getFullRouteAsString(route: List<PointData>): String {
        return if (route.size > 2) {
            "${route.first().name} → ... → ${route.last().name}"
        } else {
            route.joinToString(" → ") { it.name }
        }
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