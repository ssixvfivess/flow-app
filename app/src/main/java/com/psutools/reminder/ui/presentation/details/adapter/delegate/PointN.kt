package com.psutools.reminder.ui.presentation.details.adapter.delegate

import com.psutools.reminder.base.delegates.BaseListItem

data class PointN(
    val point: String
) : BaseListItem {
    override val id: String = "PointN-$point"
}