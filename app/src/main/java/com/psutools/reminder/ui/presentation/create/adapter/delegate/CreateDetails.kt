package com.psutools.reminder.ui.presentation.create.adapter.delegate

import com.psutools.reminder.base.delegates.BaseListItem

data class CreateDetails (
    val selectedDate: String = "",
    val selectedTime: String = "",
    val selectedReminder: String = ""
) : BaseListItem {
    override val id: String = "CreateDetails-$selectedDate"
}