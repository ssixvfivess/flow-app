package com.psutools.reminder.ui.presentation.details

import com.psutools.reminder.base.ResourceProvider
import com.psutools.reminder.base.delegates.BaseListItem
import com.psutools.reminder.domain.model.details.TripDetailsData
import com.psutools.reminder.ui.presentation.details.adapter.delegate.PointA
import com.psutools.reminder.ui.presentation.details.adapter.delegate.PointB
import com.psutools.reminder.ui.presentation.details.adapter.delegate.PointN
import com.psutools.reminder.ui.presentation.details.adapter.delegate.TripToolbarNameRoute
import javax.inject.Inject

class TripDataDetailsUiMapper @Inject constructor(
    private val resourceProvider: ResourceProvider
) {

    fun createListItem(data: TripDetailsData): List<BaseListItem> {
        val item = mutableListOf<BaseListItem>()

        item.add(
            TripToolbarNameRoute(
                routeName = data.name
            )
        )

        item.add(
            PointA(
                point = data.route.first().name
            )
        )

        data.route.drop(1).dropLast(1).forEach { point ->
            item.add(
                PointN(
                    point = point.name
                )
            )
        }

        item.add(
            PointB(
                point = data.route.last().name
            )
        )

        return item
    }
}