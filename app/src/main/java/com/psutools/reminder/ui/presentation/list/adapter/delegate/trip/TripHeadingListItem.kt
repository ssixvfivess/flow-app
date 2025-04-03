package com.psutools.reminder.ui.presentation.list.adapter.delegate.trip

import com.psutools.reminder.base.delegates.BaseListItem

data class TripHeadingListItem(
    val heading: String,
    val notificationIcon: Boolean
): BaseListItem {

    override val id: String = "TripHeadingListItem-$heading"
}
