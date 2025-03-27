package com.psutools.reminder.ui.sample.list.adapter.delegate.trip

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.psutools.reminder.base.delegates.BaseItemAdapterDelegate
import com.psutools.reminder.base.delegates.BaseListItem
import com.psutools.reminder.databinding.ItemTripHeadingListBinding
import com.psutools.reminder.ui.sample.list.adapter.delegate.trip.TripHeadingListItemDelegate.HeaderViewHolder

class TripHeadingListItemDelegate : BaseItemAdapterDelegate<TripHeadingListItem, HeaderViewHolder>() {

    override fun isForViewType(item: BaseListItem): Boolean = item is TripHeadingListItem

    override fun onCreateViewHolder(parent: ViewGroup): HeaderViewHolder {
        val binding = ItemTripHeadingListBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return HeaderViewHolder(binding)
    }

    override fun onBind(item: TripHeadingListItem, holder: HeaderViewHolder, payloads: List<Any>) {
        with(holder.binding){
            headingView.text = item.heading

            if(item.notificationIcon){
                notificationImage.visibility = View.VISIBLE
            } else {
                notificationImage.visibility = View.GONE
            }

            notificationImage.setOnClickListener{
                TODO()
            }

        }
    }

    class HeaderViewHolder(val binding: ItemTripHeadingListBinding) :
        RecyclerView.ViewHolder(binding.root)
}
