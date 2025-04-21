package com.psutools.reminder.ui.presentation.details.adapter.delegate

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.psutools.reminder.base.delegates.BaseItemAdapterDelegate
import com.psutools.reminder.base.delegates.BaseListItem
import com.psutools.reminder.databinding.ItemWayNBinding
import com.psutools.reminder.ui.presentation.details.adapter.delegate.PointNDelegate.ViewHolder


class PointNDelegate() : BaseItemAdapterDelegate<PointN, ViewHolder>() {
    override fun isForViewType(item: BaseListItem): Boolean = item is PointN

    override fun onCreateViewHolder(parent: ViewGroup): ViewHolder {
        val viewBinding = ItemWayNBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ViewHolder(viewBinding)
    }

    override fun onBind(item: PointN, holder: ViewHolder, payloads: List<Any>) {
        with(holder.viewBinding) {
            pointN.text = item.point
        }
    }

    class ViewHolder(
        val viewBinding:
        ItemWayNBinding
    ) : RecyclerView.ViewHolder(viewBinding.root)
}