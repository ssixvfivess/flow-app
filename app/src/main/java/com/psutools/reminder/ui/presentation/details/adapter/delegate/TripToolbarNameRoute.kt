package com.psutools.reminder.ui.presentation.details.adapter.delegate

import com.psutools.reminder.base.delegates.BaseListItem

data class TripToolbarNameRoute (
    val routeName: String
) : BaseListItem {
    override val id: String = "TripToolbarNameRoute-$routeName"
}