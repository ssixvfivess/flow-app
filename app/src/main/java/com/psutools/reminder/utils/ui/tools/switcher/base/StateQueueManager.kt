package com.psutools.reminder.utils.ui.tools.switcher.base

import java.util.LinkedList

abstract class StateQueueManager<State : Enum<State>> {

    var isAnimating: Boolean = false

    protected val stateQueue: LinkedList<ContentStateNode<State>> = LinkedList()

    protected var settings: StateSwitcherSettings<State>? = null

    protected abstract val currentStateNode: ContentStateNode<State>

    protected abstract val nextStateNode: ContentStateNode<State>?

    protected abstract fun prepareNextIteration()

    open fun onPostStateSwitched() {}

    open fun onPostStateStarted() {}

    open fun onPostStateCancelled(mode: StateViewProcessor.CancelAnimationMode) {}

    abstract fun setup(settings: StateSwitcherSettings<State>)

    protected fun launchQueue() {
        if (!isAnimating && hasMoreStates()) {
            val nextState = nextStateNode ?: return
            val settings = requireNotNull(settings) { "State switcher settings must be set" }

            val fromViews = settings.getViewsFromState(currentStateNode.state)
            val toViews = settings.getViewsFromState(nextState.state)

            nextState.processor.processStateTransition(fromViews, toViews, nextState.animationParams, switchStateListenerImpl)
        }
    }

    private fun hasMoreStates(): Boolean {
        return stateQueue.size > 1
    }

    private val switchStateListenerImpl = object : StateViewProcessor.SwitchStateListener {

        override fun onStateSwitched() {
            isAnimating = false
            prepareNextIteration()
            onPostStateSwitched()
            launchQueue()
        }

        override fun onSwitchingCanceled(mode: StateViewProcessor.CancelAnimationMode) {
            isAnimating = false
            onPostStateCancelled(mode)
        }

        override fun onSwitchingStarted() {
            isAnimating = true
            onPostStateStarted()
        }
    }

    protected class ContentStateNode<State : Enum<State>>(
        val state: State,
        val animationParams: SwitchAnimationParams,
        val processor: StateViewProcessor
    )
}
