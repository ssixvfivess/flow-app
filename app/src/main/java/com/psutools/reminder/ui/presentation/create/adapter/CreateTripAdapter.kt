package com.psutools.reminder.ui.presentation.create.adapter

import android.annotation.SuppressLint
import com.hannesdorfmann.adapterdelegates4.ListDelegationAdapter
import com.psutools.reminder.base.delegates.BaseListItem
import com.psutools.reminder.ui.presentation.create.adapter.delegate.CreateDetailsDelegate
import com.psutools.reminder.ui.presentation.create.adapter.delegate.CreatePointADelegate
import com.psutools.reminder.ui.presentation.create.adapter.delegate.CreatePointBDelegate

class CreateTripAdapter(
    private val onTimeSelected: (String) -> Unit,
    private val onDateSelected: (String) -> Unit,
    private val onReminderSelected: (String) -> Unit
) : ListDelegationAdapter<List<BaseListItem>>() {

    init {
        delegatesManager.addDelegate(CreatePointADelegate())
        delegatesManager.addDelegate(CreatePointBDelegate())
        delegatesManager.addDelegate(CreateDetailsDelegate(onTimeSelected, onDateSelected, onReminderSelected))
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun setItems(items: List<BaseListItem>?) {
        this.items = items ?: emptyList()
        notifyDataSetChanged()
    }
}