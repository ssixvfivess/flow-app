package com.psutools.reminder.ui.presentation.details.adapter.delegate

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.psutools.reminder.base.delegates.BaseItemAdapterDelegate
import com.psutools.reminder.base.delegates.BaseListItem
import com.psutools.reminder.databinding.ItemDetailsRouteLineBinding
import com.psutools.reminder.ui.presentation.details.adapter.delegate.ItemDetailsABDelegate.ViewHolder

class ItemDetailsABDelegate : BaseItemAdapterDelegate<ItemDetailsAB, ViewHolder>() {
    override fun isForViewType(item: BaseListItem): Boolean = item is ItemDetailsAB

    override fun onCreateViewHolder(parent: ViewGroup): ViewHolder {
        val viewBinding = ItemDetailsRouteLineBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ViewHolder(viewBinding)
    }

    override fun onBind(item: ItemDetailsAB, holder: ViewHolder, payloads: List<Any>) {
        with(holder.viewBinding) {
            pointA.text = item.nameA
            pointAAddress.text = item.addressA
//            timeOfRoute.text = item.time
            pointB.text = item.nameB
            pointBAddress.text = item.addressB
            timeForPointA.text = item.startsTime
            timeForPointB.text = item.endTime
        }
    }

    class ViewHolder(
        val viewBinding: ItemDetailsRouteLineBinding
    ) : RecyclerView.ViewHolder(viewBinding.root)
}