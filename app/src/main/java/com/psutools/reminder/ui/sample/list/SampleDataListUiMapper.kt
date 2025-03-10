package com.psutools.reminder.ui.sample.list

import com.psutools.reminder.base.delegates.BaseListItem
import com.psutools.reminder.domain.model.sample.SampleData
import com.psutools.reminder.ui.sample.list.adapter.delegate.SampleDataListItem
import javax.inject.Inject

class SampleDataListUiMapper @Inject constructor() {

    fun createListItems(dataList: List<SampleData>): List<BaseListItem> {
        return dataList.map { data ->
            SampleDataListItem(data.text)
        }
    }
}
