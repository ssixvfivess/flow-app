package com.psutools.reminder.ui.presentation.create.adapter.delegate

import android.text.Editable
import com.psutools.reminder.base.delegates.BaseListItem
import org.joda.time.LocalDate

data class CreateDate(
    val date: LocalDate
) : BaseListItem {
    override val id: String = "CreatePointA-$date"
}