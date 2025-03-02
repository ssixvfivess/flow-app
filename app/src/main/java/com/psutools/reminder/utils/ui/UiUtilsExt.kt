package com.psutools.reminder.utils.ui

import android.content.Context
import android.content.res.Resources
import android.graphics.drawable.Drawable
import androidx.annotation.ColorRes
import androidx.annotation.Dimension
import androidx.annotation.DrawableRes
import androidx.annotation.Px
import androidx.core.content.ContextCompat
import kotlin.math.roundToInt

@Dimension(unit = Dimension.DP)
annotation class Dp

@Px
fun Context.dpToPx(@Dp dp: Float): Int {
    return dpToPx(dp, resources)
}

/**
 * This method converts dp unit to equivalent pixels, depending on device density.
 */
@Px
fun dpToPx(@Dp dp: Float, resources: Resources): Int {
    return (dp * resources.displayMetrics.density).roundToInt()
}

fun Context.getDrawableCompat(@DrawableRes drawable: Int): Drawable =
    requireNotNull(ContextCompat.getDrawable(this, drawable))

fun Context.getColorCompat(@ColorRes color: Int) =
    ContextCompat.getColor(this, color)
