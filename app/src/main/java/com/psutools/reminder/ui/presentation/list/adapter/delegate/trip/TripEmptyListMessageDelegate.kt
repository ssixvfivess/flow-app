package com.psutools.reminder.ui.presentation.list.adapter.delegate.trip

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.psutools.reminder.base.delegates.BaseItemAdapterDelegate
import com.psutools.reminder.base.delegates.BaseListItem
import com.psutools.reminder.databinding.ItemTripEmptyListMessageBinding
import com.psutools.reminder.ui.presentation.list.adapter.delegate.trip.TripEmptyListMessageDelegate.HeaderViewHolder

class TripEmptyListMessageDelegate : BaseItemAdapterDelegate<TripEmptyListMessage, HeaderViewHolder>() {

    override fun isForViewType(item: BaseListItem): Boolean = item is TripEmptyListMessage

    override fun onCreateViewHolder(parent: ViewGroup): HeaderViewHolder {
        val binding = ItemTripEmptyListMessageBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return HeaderViewHolder(binding)
    }

    override fun onBind(item: TripEmptyListMessage, holder: HeaderViewHolder, payloads: List<Any>) {
        with(holder.binding){
            emptyMessage.text = item.message
        }
    }

    class HeaderViewHolder(val binding: ItemTripEmptyListMessageBinding) :
        RecyclerView.ViewHolder(binding.root)
}