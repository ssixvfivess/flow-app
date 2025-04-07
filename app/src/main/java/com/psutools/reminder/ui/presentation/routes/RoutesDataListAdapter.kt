package com.psutools.reminder.ui.presentation.routes

import android.annotation.SuppressLint
import com.hannesdorfmann.adapterdelegates4.ListDelegationAdapter
import com.psutools.reminder.base.delegates.BaseListItem
import com.psutools.reminder.ui.presentation.list.adapter.delegate.trip.TripDataListItemDelegate
import com.psutools.reminder.ui.presentation.list.adapter.delegate.trip.TripEmptyListMessageDelegate


//переиспользование делегатов из главного экрана

class RoutesDataListAdapter(
    onClickListener: (text: String) -> Unit,
) : ListDelegationAdapter<List<BaseListItem>>() {

    init {
        delegatesManager.addDelegate(TripDataListItemDelegate(onClickListener))
        delegatesManager.addDelegate(TripEmptyListMessageDelegate())
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun setItems(items: List<BaseListItem>?) {
        this.items = items
        notifyDataSetChanged()
    }
}