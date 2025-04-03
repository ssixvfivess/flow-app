package com.psutools.reminder.ui.presentation.list.adapter

import android.annotation.SuppressLint
import com.hannesdorfmann.adapterdelegates4.ListDelegationAdapter
import com.psutools.reminder.base.delegates.BaseListItem
import com.psutools.reminder.ui.presentation.list.adapter.delegate.trip.TripCurrentDataItemDelegate
import com.psutools.reminder.ui.presentation.list.adapter.delegate.trip.TripDataListItemDelegate
import com.psutools.reminder.ui.presentation.list.adapter.delegate.trip.TripEmptyListMessageDelegate
import com.psutools.reminder.ui.presentation.list.adapter.delegate.trip.TripHeadingListItemDelegate

class TripDataListAdapter(
    onClickListener: (text: String) -> Unit,
) : ListDelegationAdapter<List<BaseListItem>>() {

    init {
        delegatesManager.addDelegate(TripDataListItemDelegate(onClickListener))
        delegatesManager.addDelegate(TripHeadingListItemDelegate())
        delegatesManager.addDelegate(TripCurrentDataItemDelegate(onClickListener))
        delegatesManager.addDelegate(TripEmptyListMessageDelegate())
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun setItems(items: List<BaseListItem>?) {
        this.items = items
        notifyDataSetChanged()
    }
}
