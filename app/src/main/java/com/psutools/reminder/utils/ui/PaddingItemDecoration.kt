package com.psutools.reminder.utils.ui

import android.graphics.Rect
import android.view.View
import androidx.annotation.Px
import androidx.recyclerview.widget.RecyclerView

class PaddingItemDecoration(
    @Px val left: Int = 0,
    @Px val top: Int = 0,
    @Px val right: Int = 0,
    @Px val bottom: Int = 0,
    private val filter: (RecyclerView.ViewHolder) -> Boolean = { true }
) : RecyclerView.ItemDecoration() {

    constructor(@Px padding: Int) : this(padding, padding, padding, padding)

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        val holder = parent.getChildViewHolder(view)
        if (filter(holder)) {
            outRect.set(left, top, right, bottom)
        }
    }
}
