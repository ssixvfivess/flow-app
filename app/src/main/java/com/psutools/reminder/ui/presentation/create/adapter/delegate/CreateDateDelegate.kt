package com.psutools.reminder.ui.presentation.create.adapter.delegate

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.psutools.reminder.base.delegates.BaseItemAdapterDelegate
import com.psutools.reminder.base.delegates.BaseListItem
import com.psutools.reminder.databinding.CreateDetailsBinding
import com.psutools.reminder.databinding.ItemWayACreateBinding
import com.psutools.reminder.ui.presentation.create.adapter.delegate.CreateDateDelegate.ViewHolder


class CreateDateDelegate : BaseItemAdapterDelegate<CreateDate, ViewHolder>() {

    override fun isForViewType(item: BaseListItem): Boolean = item is CreateDate

    override fun onCreateViewHolder(parent: ViewGroup): CreateDateDelegate.ViewHolder {
        val viewBinding = CreateDetailsBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return CreateDateDelegate.ViewHolder(viewBinding)
    }

    override fun onBind(
        item: CreateDate,
        holder: CreateDateDelegate.ViewHolder,
        payloads: List<Any>
    ) {
        with(holder.viewBinding) {
           // dateCard.text = item.date todo какой тут должен быть тип?
        }
    }

    class ViewHolder(
        val viewBinding: CreateDetailsBinding
    ) : RecyclerView.ViewHolder(viewBinding.root)
}