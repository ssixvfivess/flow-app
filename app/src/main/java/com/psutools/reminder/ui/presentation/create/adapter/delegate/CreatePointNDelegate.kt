package com.psutools.reminder.ui.presentation.create.adapter.delegate

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.psutools.reminder.base.delegates.BaseItemAdapterDelegate
import com.psutools.reminder.base.delegates.BaseListItem
import com.psutools.reminder.databinding.ItemWayNBinding
import com.psutools.reminder.databinding.ItemWayNCreateBinding
import com.psutools.reminder.ui.presentation.create.adapter.delegate.CreatePointNDelegate.ViewHolder

class CreatePointNDelegate : BaseItemAdapterDelegate<CreatePointN, ViewHolder>() {

    override fun isForViewType(item: BaseListItem): Boolean = item is CreatePointN

    override fun onCreateViewHolder(parent: ViewGroup): CreatePointNDelegate.ViewHolder {
        val viewBinding = ItemWayNCreateBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return CreatePointNDelegate.ViewHolder(viewBinding)
    }

    override fun onBind(
        item: CreatePointN,
        holder: CreatePointNDelegate.ViewHolder,
        payloads: List<Any>
    ) {
        with(holder.viewBinding) {
            pointN.text = item.point
        }
    }

    class ViewHolder(
        val viewBinding: ItemWayNCreateBinding
    ) : RecyclerView.ViewHolder(viewBinding.root)
}