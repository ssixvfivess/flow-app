package com.psutools.reminder.ui.sample.list.adapter

import android.annotation.SuppressLint
import androidx.appcompat.widget.SearchView.OnCloseListener
import com.hannesdorfmann.adapterdelegates4.ListDelegationAdapter
import com.psutools.reminder.base.delegates.BaseListItem
import com.psutools.reminder.ui.fragments.NotificationFragment
import com.psutools.reminder.ui.sample.list.adapter.delegate.trip.TripDataListItemDelegate
import com.psutools.reminder.ui.sample.list.adapter.delegate.trip.TripHeadingListItemDelegate

class TripDataListAdapter(
    onClickListener: (text: String) -> Unit,
) : ListDelegationAdapter<List<BaseListItem>>() {

    init {
        delegatesManager.addDelegate(TripDataListItemDelegate(onClickListener))
        delegatesManager.addDelegate(TripHeadingListItemDelegate())
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun setItems(items: List<BaseListItem>?) {
        this.items = items
        notifyDataSetChanged()
    }
}
