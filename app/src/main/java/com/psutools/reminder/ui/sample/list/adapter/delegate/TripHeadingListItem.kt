package com.psutools.reminder.ui.sample.list.adapter.delegate

import com.psutools.reminder.base.delegates.BaseListItem

data class TripHeadingListItem(
    val heading: String
): BaseListItem {

    override val id: String = "TripHeadingListItem-$heading"
}
