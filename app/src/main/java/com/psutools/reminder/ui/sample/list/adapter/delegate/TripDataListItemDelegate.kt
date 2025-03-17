package com.psutools.reminder.ui.sample.list.adapter.delegate

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.psutools.reminder.base.delegates.BaseItemAdapterDelegate
import com.psutools.reminder.base.delegates.BaseListItem
import com.psutools.reminder.databinding.ItemTripDataListBinding
import com.psutools.reminder.ui.sample.list.adapter.delegate.TripDataListItemDelegate.ViewHolder

class TripDataListItemDelegate(
    private val onClickListener: (text: String) -> Unit,
) : BaseItemAdapterDelegate<TripDataListItem, ViewHolder>() {

    override fun isForViewType(item: BaseListItem): Boolean = item is TripDataListItem

    override fun onCreateViewHolder(parent: ViewGroup): ViewHolder {
        val viewBinding = ItemTripDataListBinding.inflate(LayoutInflater.from(parent.context),
            parent, false)
        return ViewHolder(viewBinding)
    }

    override fun onBind(item: TripDataListItem, holder: ViewHolder, payloads: List<Any>) {
        with(holder.viewBinding) {

            contentA.text = item.firstRoute
            contentB.text = item.lastRoute

            contentA.setOnClickListener {
                onClickListener(item.firstRoute)
            }

            contentB.setOnClickListener {
                onClickListener(item.lastRoute)
            }
        }
    }

    class ViewHolder(val viewBinding: ItemTripDataListBinding) : RecyclerView.ViewHolder(viewBinding.root)
}
