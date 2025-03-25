package com.psutools.reminder.ui.sample.list.adapter.delegate.trip

import com.psutools.reminder.base.delegates.BaseListItem

data class TripDataListItem(
//    val status: String,
    val route: String
//    val name: String
) : BaseListItem {

    override val id: String = "TripDataListItem-$route"
}
