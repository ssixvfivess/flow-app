package com.psutools.reminder.ui.presentation.details.adapter.delegate

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.psutools.reminder.base.delegates.BaseItemAdapterDelegate
import com.psutools.reminder.base.delegates.BaseListItem
import com.psutools.reminder.databinding.ItemWayABinding
import com.psutools.reminder.databinding.ItemWayBBinding
import com.psutools.reminder.ui.presentation.details.adapter.delegate.PointBDelegate.ViewHolder


class PointBDelegate: BaseItemAdapterDelegate<PointB, ViewHolder>() {
    override fun isForViewType(item: BaseListItem): Boolean = item is PointB

    override fun onCreateViewHolder(parent: ViewGroup): ViewHolder {
        val viewBinding = ItemWayBBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ViewHolder(viewBinding)
    }

    override fun onBind(item: PointB, holder: ViewHolder, payloads: List<Any>) {
        with(holder.viewBinding) {
            pointB.text = item.point
        }
    }

    class ViewHolder(
        val viewBinding: ItemWayBBinding
    ) : RecyclerView.ViewHolder(viewBinding.root)
}