package com.psutools.reminder.ui.presentation.details.adapter

import android.annotation.SuppressLint
import com.hannesdorfmann.adapterdelegates4.ListDelegationAdapter
import com.psutools.reminder.base.delegates.BaseListItem
import com.psutools.reminder.ui.presentation.details.adapter.delegate.PointADelegate
import com.psutools.reminder.ui.presentation.details.adapter.delegate.PointBDelegate
import com.psutools.reminder.ui.presentation.details.adapter.delegate.PointNDelegate
import com.psutools.reminder.ui.presentation.details.adapter.delegate.TripToolbarNameRouteDelegate

class TripDataDetailsAdapter(
    onClickListener: (text: String) -> Unit,
) : ListDelegationAdapter<List<BaseListItem>>() {

    init {
        delegatesManager.addDelegate(TripToolbarNameRouteDelegate())
        delegatesManager.addDelegate(PointADelegate(onClickListener))
        delegatesManager.addDelegate(PointNDelegate(onClickListener))
        delegatesManager.addDelegate(PointBDelegate(onClickListener))
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun setItems(items: List<BaseListItem>?) {
        this.items = items
        notifyDataSetChanged()
    }

}