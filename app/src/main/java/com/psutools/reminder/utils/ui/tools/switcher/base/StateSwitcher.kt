package com.psutools.reminder.utils.ui.tools.switcher.base

abstract class StateSwitcher<
        State : Enum<State>,
        QueueManager : StateQueueManager<State>
        > {
    abstract val stateQueueManager: QueueManager

    protected open fun onPostSetup(settings: StateSwitcherSettings<State>) {}

    fun setup(settings: StateSwitcherSettings<State>) {
        stateQueueManager.setup(settings)
        onPostSetup(settings)
    }
}
