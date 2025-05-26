package com.psutools.reminder.ui.presentation.create

import com.psutools.reminder.base.delegates.BaseListItem
import com.psutools.reminder.ui.presentation.create.adapter.delegate.CreateDetails
import com.psutools.reminder.ui.presentation.create.adapter.delegate.CreatePointA
import com.psutools.reminder.ui.presentation.create.adapter.delegate.CreatePointB

data class CreateTripState(
    val items: List<BaseListItem> = emptyList(),
    val isFormValid: Boolean = false
) {
    companion object {
        fun initial() = CreateTripState(
            items = listOf(
                CreatePointA(hint = "Выберите точку A", point = ""),
                CreatePointB(hint = "Выберите точку B", point = ""),
                CreateDetails(selectedDate = "", selectedTime = "", selectedReminder = "")
            ),
            isFormValid = false
        )
    }
}