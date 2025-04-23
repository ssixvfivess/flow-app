package com.psutools.reminder.ui.presentation.list.adapter.delegate.trip

import com.psutools.reminder.base.delegates.BaseListItem

data class TripDataListItem(
    val tripId: String,
    val name: String,
    val route: String,
    val arrivalDateTime: String,
    val timeStart: String
) : BaseListItem {

    override val id: String = "TripDataListItem-$tripId"
}
