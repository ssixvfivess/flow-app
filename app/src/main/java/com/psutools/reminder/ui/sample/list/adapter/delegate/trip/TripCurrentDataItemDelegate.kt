package com.psutools.reminder.ui.sample.list.adapter.delegate.trip

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.psutools.reminder.base.delegates.BaseItemAdapterDelegate
import com.psutools.reminder.base.delegates.BaseListItem
import com.psutools.reminder.databinding.ItemCurrentTripBinding
import com.psutools.reminder.ui.sample.list.adapter.delegate.trip.TripCurrentDataItemDelegate.ViewHolder

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

            contentData.text = "Пятница, 28 марта"
            contentName.text = "В ПГНИУ"
            contentRoute.text = item.route

            contentRoute.setOnClickListener {
                onClickListener(item.route)
            }
        }
    }

    class ViewHolder(val viewBinding: ItemCurrentTripBinding) : RecyclerView.ViewHolder(viewBinding.root)
}
