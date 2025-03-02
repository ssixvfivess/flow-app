package com.psutools.reminder.ui.sample.list.adapter.delegate

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.psutools.reminder.base.delegates.BaseItemAdapterDelegate
import com.psutools.reminder.base.delegates.BaseListItem
import com.psutools.reminder.databinding.ItemSampleDataListBinding
import com.psutools.reminder.ui.sample.list.adapter.delegate.SampleDataListItemDelegate.ViewHolder

class SampleDataListItemDelegate(
    private val onClickListener: (text: String) -> Unit,
) : BaseItemAdapterDelegate<SampleDataListItem, ViewHolder>() {

    override fun isForViewType(item: BaseListItem): Boolean = item is SampleDataListItem

    override fun onCreateViewHolder(parent: ViewGroup): ViewHolder {
        val viewBinding = ItemSampleDataListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(viewBinding)
    }

    override fun onBind(item: SampleDataListItem, holder: ViewHolder, payloads: List<Any>) {
        with(holder.viewBinding) {
            content.text = item.text
            content.setOnClickListener {
                onClickListener(item.text)
            }
        }
    }

    class ViewHolder(val viewBinding: ItemSampleDataListBinding) : RecyclerView.ViewHolder(viewBinding.root)
}
