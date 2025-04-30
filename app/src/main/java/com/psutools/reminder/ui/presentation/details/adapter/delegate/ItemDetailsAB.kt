package com.psutools.reminder.ui.presentation.details.adapter.delegate

import com.psutools.reminder.base.delegates.BaseListItem
import com.psutools.reminder.domain.model.trip.TransportData

data class ItemDetailsAB(
    val nameA: String,
    val nameB: String,
    val addressA: String,
    val addressB: String,
    val transportType: List<TransportData>,
    val displayRouteTime: String,
    val startTime: String,
    val endTime: String
) : BaseListItem {
    override val id: String = "name-$nameA"
}