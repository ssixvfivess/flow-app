package com.psutools.reminder.ui.presentation.details.adapter

import android.annotation.SuppressLint
import com.hannesdorfmann.adapterdelegates4.ListDelegationAdapter
import com.psutools.reminder.base.delegates.BaseListItem
import com.psutools.reminder.ui.presentation.details.adapter.delegate.ItemDetailsABDelegate
import com.psutools.reminder.ui.presentation.details.adapter.delegate.PointADelegate
import com.psutools.reminder.ui.presentation.details.adapter.delegate.PointBDelegate
import com.psutools.reminder.ui.presentation.details.adapter.delegate.PointNDelegate

class TripDataDetailsAdapter : ListDelegationAdapter<List<BaseListItem>>() {

    init {
        delegatesManager.addDelegate(PointADelegate())
        delegatesManager.addDelegate(PointNDelegate())
        delegatesManager.addDelegate(PointBDelegate())
        delegatesManager.addDelegate(ItemDetailsABDelegate())
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun setItems(items: List<BaseListItem>?) {
        this.items = items ?: emptyList()
        notifyDataSetChanged()
    }
}