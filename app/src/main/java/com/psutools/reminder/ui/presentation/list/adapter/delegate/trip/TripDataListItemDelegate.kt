package com.psutools.reminder.ui.presentation.list.adapter.delegate.trip

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.psutools.reminder.base.delegates.BaseItemAdapterDelegate
import com.psutools.reminder.base.delegates.BaseListItem
import com.psutools.reminder.databinding.ItemTripDataListBinding
import com.psutools.reminder.ui.presentation.list.adapter.delegate.trip.TripDataListItemDelegate.ViewHolder

class TripDataListItemDelegate(
    private val onClickListener: (text: String) -> Unit,
) : BaseItemAdapterDelegate<TripDataListItem, ViewHolder>() {

    override fun isForViewType(item: BaseListItem): Boolean = item is TripDataListItem

    override fun onCreateViewHolder(parent: ViewGroup): ViewHolder {
        val viewBinding = ItemTripDataListBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ViewHolder(viewBinding)
    }

    override fun onBind(item: TripDataListItem, holder: ViewHolder, payloads: List<Any>) {
        with(holder.viewBinding) {

            contentData.text = item.arrivalDateTime
            contentName.text = item.name
            contentRoute.text = item.route
            contentRouteTime.text = item.timeStart



            root.setOnClickListener {
                onClickListener(item.tripId)
            }
        }
    }

    class ViewHolder(val viewBinding: ItemTripDataListBinding) :
        RecyclerView.ViewHolder(viewBinding.root)
}
