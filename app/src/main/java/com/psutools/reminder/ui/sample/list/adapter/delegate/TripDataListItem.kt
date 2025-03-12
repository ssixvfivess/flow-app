package com.psutools.reminder.ui.sample.list.adapter.delegate

import com.psutools.reminder.base.delegates.BaseListItem

data class TripDataListItem(
    val text: String,
) : BaseListItem {

    override val id: String = "TripDataListItem-$text"
}
