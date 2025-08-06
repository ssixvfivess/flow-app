package com.psutools.reminder.ui.presentation.list.adapter.delegate.trip

import android.graphics.drawable.Drawable
import com.psutools.reminder.base.delegates.BaseListItem

data class TripEmptyListMessage(
    val message: String?,
    val pic: Drawable?
): BaseListItem {

    override val id: String = "TripEmptyListMessage-$message"
}
