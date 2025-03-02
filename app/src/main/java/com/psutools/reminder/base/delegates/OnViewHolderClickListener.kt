package com.psutools.reminder.base.delegates

import android.view.View
import androidx.recyclerview.widget.RecyclerView

fun interface OnViewHolderClickListener<VH : RecyclerView.ViewHolder> {

    fun onViewHolderClick(view: View, holder: VH)
}
