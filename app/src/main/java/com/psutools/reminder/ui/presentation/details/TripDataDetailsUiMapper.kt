package com.psutools.reminder.ui.presentation.details

import com.psutools.reminder.base.delegates.BaseListItem
import com.psutools.reminder.domain.model.details.TripDetailsData
import com.psutools.reminder.domain.model.trip.PointData
import com.psutools.reminder.ui.presentation.details.adapter.delegate.ItemDetailsAB
import com.psutools.reminder.ui.presentation.details.adapter.delegate.PointA
import com.psutools.reminder.ui.presentation.details.adapter.delegate.PointB
import com.psutools.reminder.ui.presentation.details.adapter.delegate.PointN
import org.joda.time.DateTime
import org.joda.time.format.DateTimeFormat
import java.util.Locale
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

        var startTime: DateTime
        var endTime: DateTime
        var previousEndTime = DateTime()

        var currentSegment: ItemDetailsAB

        for (i in 0 until trip.route.size - 1) {
            val pointA: PointData = trip.route[i]
            val pointB: PointData = trip.route[i + 1]


            if (i == 0) {
                startTime = trip.departureDateTime
            } else {
                startTime = previousEndTime.plusMinutes(pointA.stopTime)
            }

            endTime = startTime.plusMinutes(trip.routeTimes[i])



            currentSegment = ItemDetailsAB(
                nameA = pointA.name,
                nameB = pointB.name,
                addressA = pointA.address,
                addressB = pointB.address,
                transportType = trip.transportType,
                displayRouteTime = trip.displayRouteTimes[i],
                startTime = getDisplayTime(startTime),
                endTime = getDisplayTime(endTime)
            )

            result.add(currentSegment)

            previousEndTime = endTime
        }

        return result
    }

    private fun getDisplayTime(data: DateTime): String {
        val formatter = DateTimeFormat.forPattern("HH:mm").withLocale(Locale("ru"))
        return formatter.print(data)
    }
}
