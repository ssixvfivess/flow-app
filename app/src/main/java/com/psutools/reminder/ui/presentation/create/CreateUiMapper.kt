package com.psutools.reminder.ui.presentation.create

import androidx.room.util.copy
import com.psutools.reminder.base.delegates.BaseListItem
import com.psutools.reminder.ui.presentation.create.adapter.delegate.CreateDetails
import javax.inject.Inject

class CreateUiMapper @Inject constructor() {

    fun mapTime(items: List<BaseListItem>, newTime: String): List<BaseListItem> {
        return items.map { item ->
            when (item) {
                is CreateDetails -> item.copy(selectedTime = newTime)
                else -> item
            }
        }
    }

    fun mapDate(items: List<BaseListItem>, newDate: String): List<BaseListItem> {
        return items.map { item ->
            when (item) {
                is CreateDetails -> item.copy(selectedDate = newDate)
                else -> item
            }
        }
    }

    fun mapReminder(items: List<BaseListItem>, newReminder: String): List<BaseListItem> {
        return items.map { item ->
            when (item) {
                is CreateDetails -> item.copy(selectedReminder = newReminder)
                else -> item
            }
        }
    }
}