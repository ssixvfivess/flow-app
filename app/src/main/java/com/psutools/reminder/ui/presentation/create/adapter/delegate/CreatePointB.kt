package com.psutools.reminder.ui.presentation.create.adapter.delegate

import android.text.Editable
import com.psutools.reminder.base.delegates.BaseListItem

data class CreatePointB (
    val point: Editable
): BaseListItem {
    override val id: String = "CreatePointB-$point"
}