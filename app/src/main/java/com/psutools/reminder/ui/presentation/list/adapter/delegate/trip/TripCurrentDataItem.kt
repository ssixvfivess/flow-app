package com.psutools.reminder.ui.presentation.list.adapter.delegate.trip

import com.psutools.reminder.base.delegates.BaseListItem

data class TripCurrentDataItem (
    val name: String,
    val route: String,
    val arrivalDateTime: String
) : BaseListItem {

    override val id: String = "TripCurrentDataItem-$route"
}
