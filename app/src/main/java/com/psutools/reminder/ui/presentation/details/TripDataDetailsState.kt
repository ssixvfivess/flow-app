package com.psutools.reminder.ui.presentation.details

import com.psutools.reminder.base.delegates.BaseListItem

data class TripDataDetailsState (
    val items: List<BaseListItem>,
    val routeName: String
)

