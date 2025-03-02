package com.psutools.reminder.ui.sample.list.adapter.delegate

import com.psutools.reminder.base.delegates.BaseListItem

data class SampleDataListItem(
    val text: String,
) : BaseListItem {

    override val id: String = "SampleDataListItem-$text"
}
