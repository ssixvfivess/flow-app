package com.psutools.reminder.ui.sample.list.adapter.delegate.trip

import com.psutools.reminder.base.delegates.BaseListItem

class TripCurrentDataItem (
//    val status: String,
    val route: String,
    val arrivalDateTime: String
//    val name: String
) : BaseListItem {

    override val id: String = "TripCurrentDataItem-$route"
}
