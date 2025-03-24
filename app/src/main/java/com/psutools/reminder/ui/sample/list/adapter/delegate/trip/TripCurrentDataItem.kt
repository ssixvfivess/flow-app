package com.psutools.reminder.ui.sample.list.adapter.delegate.trip

import com.psutools.reminder.base.delegates.BaseListItem

class TripCurrentDataItem (
    val route: String
) : BaseListItem {

    override val id: String = "TripCurrentDataItem-$route"
}
