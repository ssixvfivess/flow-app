package com.psutools.reminder.utils.ui

import android.content.res.ColorStateList
import android.view.View
import android.widget.TextView
import androidx.annotation.ColorRes

fun TextView.setTextColorRes(@ColorRes color: Int) =
    setTextColor(context.getColorCompat(color))

fun View.setBackgroundColorTint(@ColorRes color: Int) {
    backgroundTintList = ColorStateList.valueOf(context.getColorCompat(color))
}
