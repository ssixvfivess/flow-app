package com.psutools.reminder.ui.presentation.details.adapter.delegate

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.psutools.reminder.base.delegates.BaseItemAdapterDelegate
import com.psutools.reminder.base.delegates.BaseListItem
import com.psutools.reminder.databinding.ItemWayABinding
import com.psutools.reminder.ui.presentation.details.adapter.delegate.PointADelegate.ViewHolder


class PointADelegate: BaseItemAdapterDelegate<PointA, ViewHolder>() {
    override fun isForViewType(item: BaseListItem): Boolean = item is PointA

    override fun onCreateViewHolder(parent: ViewGroup): ViewHolder {
        val viewBinding = ItemWayABinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ViewHolder(viewBinding)
    }

    override fun onBind(item: PointA, holder: ViewHolder, payloads: List<Any>) {
        with(holder.viewBinding) {
            pointA.text = item.point
        }
    }

    class ViewHolder(
        val viewBinding: ItemWayABinding
    ) : RecyclerView.ViewHolder(viewBinding.root)
}