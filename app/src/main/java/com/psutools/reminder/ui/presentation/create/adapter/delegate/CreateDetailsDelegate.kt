package com.psutools.reminder.ui.presentation.create.adapter.delegate

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.psutools.reminder.base.delegates.BaseItemAdapterDelegate
import com.psutools.reminder.base.delegates.BaseListItem
import com.psutools.reminder.databinding.CreateDetailsBinding
import com.psutools.reminder.ui.presentation.create.adapter.delegate.CreateDetailsDelegate.ViewHolder

class CreateDetailsDelegate : BaseItemAdapterDelegate<CreateDetails, ViewHolder>() {

    override fun isForViewType(item: BaseListItem): Boolean = item is CreateDetails

    override fun onCreateViewHolder(parent: ViewGroup): ViewHolder {
        val binding = CreateDetailsBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ViewHolder(binding)
    }

    override fun onBind(item: CreateDetails, holder: ViewHolder, payloads: List<Any>) {
        with(holder.binding) {
            editDate.setText(item.selectedDate)
            editArrivalTime.setText(item.selectedTime)
            editReminder.setText(item.selectedReminder)
            root.setOnClickListener {
                //здесь позже будет открытие барабана
            }
        }
    }

    class ViewHolder(val binding: CreateDetailsBinding) : RecyclerView.ViewHolder(binding.root)
}