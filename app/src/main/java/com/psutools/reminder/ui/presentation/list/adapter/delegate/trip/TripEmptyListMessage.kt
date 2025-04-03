package com.psutools.reminder.ui.presentation.list.adapter.delegate.trip

import com.psutools.reminder.base.delegates.BaseListItem

data class TripEmptyListMessage(
    val message: String
): BaseListItem {

    override val id: String = "TripEmptyListMessage-$message"
}
