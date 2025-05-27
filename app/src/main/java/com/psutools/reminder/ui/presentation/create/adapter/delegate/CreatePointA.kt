package com.psutools.reminder.ui.presentation.create.adapter.delegate

import android.text.Editable
import com.psutools.reminder.base.delegates.BaseListItem

data class CreatePointA (
    val hint: String,
    val point: String
):BaseListItem {
    override val id: String = "CreatePointA-$point"
}