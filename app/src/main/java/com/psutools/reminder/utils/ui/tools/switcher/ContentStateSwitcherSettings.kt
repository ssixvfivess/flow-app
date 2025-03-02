package com.psutools.reminder.utils.ui.tools.switcher

import android.view.View
import com.psutools.reminder.utils.ui.tools.switcher.base.StateSwitcherSettings

class ContentStateSwitcherSettings<State : Enum<State>>(
    override val initState: State,
    private val mapper: (State) -> List<View>,
) : StateSwitcherSettings<State> {

    override fun setup() {
        val values = requireNotNull(initState.javaClass.enumConstants) { "enumConstants is null" }
        values.forEach {
            getViewsFromState(it).hideViews()
        }
        getViewsFromState(initState).showViews()
    }

    override fun getViewsFromState(state: State): List<View> {
        return mapper(state)
    }

    private fun List<View>.hideViews() {
        forEach {
            it.alpha = ZERO_ALPHA
            it.visibility = View.INVISIBLE
        }
    }

    private fun List<View>.showViews() {
        forEach {
            it.alpha = FULL_ALPHA
            it.visibility = View.VISIBLE
        }
    }

    private companion object {
        const val FULL_ALPHA = 1F
        const val ZERO_ALPHA = 0F
    }
}
