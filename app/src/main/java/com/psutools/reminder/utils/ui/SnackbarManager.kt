package com.psutools.reminder.utils.ui

import android.view.View
import android.widget.TextView
import androidx.annotation.ColorRes
import com.google.android.material.snackbar.BaseTransientBottomBar.ANIMATION_MODE_SLIDE
import com.google.android.material.snackbar.Snackbar
import com.psutools.reminder.R

object SnackbarManager {

    fun createSnackbar(
        view: View,
        message: String,
        duration: Int = 5000,
        @ColorRes backgroundTint: Int = R.color.background_base,
        @ColorRes textColor: Int = R.color.primary_text
    ) {
        val context = view.context
        val resources = context.resources
        Snackbar.make(view, message, duration)
            .setBackgroundTint(resources.getColor(backgroundTint, context.theme))
            .setTextColor(resources.getColor(textColor, context.theme))
            .setAnimationMode(ANIMATION_MODE_SLIDE)
            .setTextViewParams { setTextAppearance(R.style.Custom_Snackbar_Text) }
            .show()
    }

    /**
     * Customises text view inside snackbar by applying proper customization [block]
     */
    private fun Snackbar.setTextViewParams(block: TextView.() -> Unit): Snackbar {
        this.view.findViewById<TextView>(com.google.android.material.R.id.snackbar_text)?.apply(block)
        return this
    }
}
