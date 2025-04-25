package com.psutools.reminder.ui.presentation.details

import com.psutools.reminder.base.delegates.BaseListItem
import com.psutools.reminder.domain.model.details.TripDetailsData
import com.psutools.reminder.ui.presentation.details.adapter.delegate.ItemDetailsAB
import com.psutools.reminder.ui.presentation.details.adapter.delegate.PointA
import com.psutools.reminder.ui.presentation.details.adapter.delegate.PointB
import com.psutools.reminder.ui.presentation.details.adapter.delegate.PointN
import javax.inject.Inject

class TripDataDetailsUiMapper @Inject constructor() {

    fun createListItem(trip: TripDetailsData): List<BaseListItem> {
        val result = mutableListOf<BaseListItem>()

        trip.route.forEachIndexed { index, route ->
            val lastIndex = trip.route.size - 1
            when (index) {
                0 -> result.add(PointA(point = route.name))
                in 1..<lastIndex -> result.add(PointN(point = route.name))
                lastIndex -> result.add(PointB(point = route.name))
            }
        }

        for (i in 0 until trip.route.size - 1) {
            val pointA = trip.route[i]
            val pointB = trip.route[i + 1]



            result.add(
                ItemDetailsAB(
                    nameA = pointA.name,
                    nameB = pointB.name,
                    addressA = pointA.address,
                    addressB = pointB.address,
                    startsTime = trip.departureDateTime.toString(),
                    endTime = pointB.stopTime.toString()
                )
            )
        }

        return result
    }
}