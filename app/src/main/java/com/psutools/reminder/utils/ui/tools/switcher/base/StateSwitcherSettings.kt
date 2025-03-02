package com.psutools.reminder.utils.ui.tools.switcher.base

import android.view.View

interface StateSwitcherSettings<State : Enum<State>> {

    val initState: State

    fun setup()

    fun getViewsFromState(state: State): List<View>
}
