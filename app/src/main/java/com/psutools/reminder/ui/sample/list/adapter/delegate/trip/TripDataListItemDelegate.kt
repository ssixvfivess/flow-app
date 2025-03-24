package com.psutools.reminder.ui.sample.list.adapter.delegate.trip

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.psutools.reminder.base.delegates.BaseItemAdapterDelegate
import com.psutools.reminder.base.delegates.BaseListItem
import com.psutools.reminder.databinding.ItemTripDataListBinding
import com.psutools.reminder.ui.sample.list.adapter.delegate.trip.TripDataListItemDelegate.ViewHolder

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

            contentData.text = "Пятница, 7 марта" //ПРИМЕР для нормального отображения
            contentName.text = "В вуз" //ПРИМЕР для нормального отображения
            contentRoute.text = item.route

            contentRoute.setOnClickListener {
                onClickListener(item.route)
            }
        }
    }

    class ViewHolder(val viewBinding: ItemTripDataListBinding) : RecyclerView.ViewHolder(viewBinding.root)
}
