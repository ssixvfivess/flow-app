package com.psutools.reminder.ui.sample.list.adapter

import android.annotation.SuppressLint
import com.hannesdorfmann.adapterdelegates4.ListDelegationAdapter
import com.psutools.reminder.base.delegates.BaseListItem
import com.psutools.reminder.ui.sample.list.adapter.delegate.TripDataListItemDelegate

class SampleDataListAdapter(
    onClickListener: (text: String) -> Unit,
) : ListDelegationAdapter<List<BaseListItem>>() {

    init {
        delegatesManager.addDelegate(TripDataListItemDelegate(onClickListener))
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun setItems(items: List<BaseListItem>?) {
        this.items = items
        notifyDataSetChanged()
    }
}
