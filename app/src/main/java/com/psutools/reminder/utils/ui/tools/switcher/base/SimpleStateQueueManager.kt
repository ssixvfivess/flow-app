package com.psutools.reminder.utils.ui.tools.switcher.base

import com.psutools.reminder.utils.ui.tools.switcher.base.processor.SetStateViewProcessor
import java.util.LinkedList

class SimpleStateQueueManager<State : Enum<State>> : StateQueueManager<State>() {

    override val currentStateNode: ContentStateNode<State>
        get() = stateQueue.firstOrNull() ?: error("Unexpected nullable current state received. Did you call setup before setting state?")

    override val nextStateNode: ContentStateNode<State>?
        get() = stateQueue.getOrNull(NEXT_STATE_ID)

    val currentState: State
        get() = currentStateNode.state

    override fun setup(settings: StateSwitcherSettings<State>) {
        this.settings = settings
        cancelAnimation(StateViewProcessor.CancelAnimationMode.BACKWARD)
        stateQueue.clear()
        stateQueue.add(
            ContentStateNode(
                state = settings.initState,
                animationParams = SwitchAnimationParams.NoAnimation,
                processor = SetStateViewProcessor()
            )
        )
        settings.setup()
    }

    override fun prepareNextIteration() {
        stateQueue.removeAt(0)
    }

    fun setState(
        state: State,
        animationParams: SwitchAnimationParams,
        processor: StateViewProcessor
    ) {
        cancelAnimation(StateViewProcessor.CancelAnimationMode.BACKWARD)
        stateQueue.dropExceptElement(currentStateNode)
        stateQueue.add(ContentStateNode(state, animationParams, processor))
        launchQueue()
    }

    fun addStateToQueue(
        state: State,
        animationParams: SwitchAnimationParams,
        processor: StateViewProcessor,
    ) {
        if (currentState == state) return

        stateQueue.add(ContentStateNode(state, animationParams, processor))
        launchQueue()
    }

    fun switchState(
        state: State,
        animationParams: SwitchAnimationParams,
        processor: StateViewProcessor
    ) {
        if ((nextStateNode ?: currentStateNode).state == state) return

        stateQueue.dropExceptElements(listOf(currentStateNode, nextStateNode))
        stateQueue.add(ContentStateNode(state, animationParams, processor))
        launchQueue()
    }

    fun cancelAnimation(mode: StateViewProcessor.CancelAnimationMode) {
        if (isAnimating) {
            nextStateNode?.processor?.cancelAnimation(mode)
        }
    }

    override fun onPostStateCancelled(mode: StateViewProcessor.CancelAnimationMode) {
        when (mode) {
            StateViewProcessor.CancelAnimationMode.FORWARD -> stateQueue.dropExceptElement(requireNotNull(nextStateNode) { "nextStateNode is null" })
            StateViewProcessor.CancelAnimationMode.BACKWARD -> stateQueue.dropExceptElement(currentStateNode)
        }
    }

    private fun <T> LinkedList<T>.dropExceptElement(element: T) {
        this.clear()
        this.add(element)
    }

    private fun <T> LinkedList<T>.dropExceptElements(elements: List<T?>) {
        this.clear()
        this.addAll(elements.filterNotNull())
    }

    private companion object {
        const val NEXT_STATE_ID = 1
    }
}
