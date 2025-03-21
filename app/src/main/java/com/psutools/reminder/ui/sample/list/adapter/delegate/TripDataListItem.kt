package com.psutools.reminder.ui.sample.list.adapter.delegate

import com.psutools.reminder.base.delegates.BaseListItem

data class TripDataListItem(
    val route: String,
) : BaseListItem {

    override val id: String = "TripDataListItem-$route"
}
