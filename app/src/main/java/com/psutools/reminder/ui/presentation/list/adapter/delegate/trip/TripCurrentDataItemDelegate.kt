package com.psutools.reminder.ui.presentation.list.adapter.delegate.trip

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.psutools.reminder.base.delegates.BaseItemAdapterDelegate
import com.psutools.reminder.base.delegates.BaseListItem
import com.psutools.reminder.databinding.ItemCurrentTripBinding
import com.psutools.reminder.ui.presentation.list.adapter.delegate.trip.TripCurrentDataItemDelegate.ViewHolder

class TripCurrentDataItemDelegate(
    private val onClickListener: (text: String) -> Unit,
) : BaseItemAdapterDelegate<TripCurrentDataItem, ViewHolder>() {

    override fun isForViewType(item: BaseListItem): Boolean = item is TripCurrentDataItem

    override fun onCreateViewHolder(parent: ViewGroup): ViewHolder {
        val viewBinding = ItemCurrentTripBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ViewHolder(viewBinding)
    }

    override fun onBind(item: TripCurrentDataItem, holder: ViewHolder, payloads: List<Any>) {
        with(holder.viewBinding) {
                contentData.text = item.arrivalDateTime
                contentName.text = item.name
                contentRoute.text = item.route

                root.setOnClickListener {
                    onClickListener(item.tripId)
                }
            }
        }

    class ViewHolder(val viewBinding: ItemCurrentTripBinding) : RecyclerView.ViewHolder(viewBinding.root)
}
