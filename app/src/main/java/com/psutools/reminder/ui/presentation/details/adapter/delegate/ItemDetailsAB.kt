package com.psutools.reminder.ui.presentation.details.adapter.delegate

import com.psutools.reminder.base.delegates.BaseListItem

data class ItemDetailsAB (
    val nameA: String,
    val nameB: String,
    val addressA: String,
    val addressB: String,
//    val time: String,
    val startsTime: String,
    val endTime: String
) : BaseListItem {
    override val id: String = "name-$nameA"
}