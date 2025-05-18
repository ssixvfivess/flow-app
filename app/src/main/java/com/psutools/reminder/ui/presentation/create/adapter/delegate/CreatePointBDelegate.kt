package com.psutools.reminder.ui.presentation.create.adapter.delegate

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.psutools.reminder.base.delegates.BaseItemAdapterDelegate
import com.psutools.reminder.base.delegates.BaseListItem
import com.psutools.reminder.databinding.ItemWayBBinding
import com.psutools.reminder.databinding.ItemWayBCreateBinding
import com.psutools.reminder.ui.presentation.create.adapter.delegate.CreatePointBDelegate.ViewHolder


class CreatePointBDelegate : BaseItemAdapterDelegate<CreatePointB, ViewHolder>() {

    override fun isForViewType(item: BaseListItem): Boolean = item is CreatePointB

    override fun onCreateViewHolder(parent: ViewGroup): ViewHolder {
        val viewBinding = ItemWayBCreateBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ViewHolder(viewBinding)
    }

    override fun onBind(
        item: CreatePointB,
        holder: ViewHolder,
        payloads: List<Any>
    ) {
        with(holder.viewBinding) {
            pointB.text = item.point
        }
    }

    class ViewHolder(
        val viewBinding: ItemWayBCreateBinding
    ) : RecyclerView.ViewHolder(viewBinding.root)
}