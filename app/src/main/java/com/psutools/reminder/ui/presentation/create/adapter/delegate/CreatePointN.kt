package com.psutools.reminder.ui.presentation.create.adapter.delegate

import android.text.Editable
import com.psutools.reminder.base.delegates.BaseListItem

data class CreatePointN (
    val point: Editable
): BaseListItem {
    override val id: String = "CreatePointN-$point"
}