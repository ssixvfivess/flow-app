package com.psutools.reminder.ui.sample.list.adapter.delegate

import com.psutools.reminder.base.delegates.BaseListItem

data class TripDataListItem(
    val firstRoute: String,
//    val lastRoute: String,
) : BaseListItem {

    override val id: String = "TripDataListItem-$firstRoute"
}
