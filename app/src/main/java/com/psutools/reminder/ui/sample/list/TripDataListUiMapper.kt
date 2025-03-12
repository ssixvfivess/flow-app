package com.psutools.reminder.ui.sample.list

import com.psutools.reminder.base.delegates.BaseListItem
import com.psutools.reminder.domain.model.PointData
import com.psutools.reminder.domain.model.TripData
import com.psutools.reminder.ui.sample.list.adapter.delegate.TripDataListItem
import javax.inject.Inject

class TripDataListUiMapper @Inject constructor() {

    fun createListItems(dataList: List<TripData>): List<BaseListItem> {
        return dataList.map { data ->
            TripDataListItem(getFullRouteAsString(data.route))
        }
    }

    fun getFullRouteAsString(route: List<PointData>): String {


        val nameRoute = StringBuilder()
        for (point in route){
            nameRoute.append(point.name).append(" → ")
        }
        return nameRoute.removeSuffix(" → ").toString()
    }
}
