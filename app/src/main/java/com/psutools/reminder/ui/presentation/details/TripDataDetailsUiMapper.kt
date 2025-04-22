package com.psutools.reminder.ui.presentation.details

import com.psutools.reminder.base.delegates.BaseListItem
import com.psutools.reminder.domain.model.details.TripDetailsData
import com.psutools.reminder.ui.presentation.details.adapter.delegate.PointA
import com.psutools.reminder.ui.presentation.details.adapter.delegate.PointB
import com.psutools.reminder.ui.presentation.details.adapter.delegate.PointN
import javax.inject.Inject

class TripDataDetailsUiMapper @Inject constructor() {

    fun createListItem(data: TripDetailsData): List<BaseListItem> {

        val points = data.route.mapIndexedNotNull { index, route ->
            val lastIndex = data.route.size-1
            when(index) {
                0 -> PointA(point = route.name)
                in 1 ..< lastIndex -> PointN(point = route.name)
                lastIndex -> PointB(point = route.name)
                else -> null
            }
        }
        return /*listOf(TripToolbarNameRoute(routeName = data.name)) + */points
    }
}