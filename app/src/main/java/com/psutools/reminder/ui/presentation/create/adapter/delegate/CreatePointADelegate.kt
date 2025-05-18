package com.psutools.reminder.ui.presentation.create.adapter.delegate

import android.text.Editable
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.psutools.reminder.base.delegates.BaseItemAdapterDelegate
import com.psutools.reminder.base.delegates.BaseListItem
import com.psutools.reminder.databinding.ItemWayACreateBinding
import com.psutools.reminder.ui.presentation.create.adapter.delegate.CreatePointADelegate.ViewHolder

class CreatePointADelegate : BaseItemAdapterDelegate<CreatePointA, ViewHolder>() {

    override fun isForViewType(item: BaseListItem): Boolean = item is CreatePointA

    override fun onCreateViewHolder(parent: ViewGroup): ViewHolder {
        val viewBinding = ItemWayACreateBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ViewHolder(viewBinding)
    }

    override fun onBind(
        item: CreatePointA,
        holder: ViewHolder,
        payloads: List<Any>
    ) {
        with(holder.viewBinding) {
            createPointA.hint = item.hint
            createPointA.setText(item.point)
        }
    }

    class ViewHolder(
        val viewBinding: ItemWayACreateBinding
    ) : RecyclerView.ViewHolder(viewBinding.root)
}