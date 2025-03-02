package com.psutools.reminder.utils.ui.tools.switcher

import android.view.View
import com.psutools.reminder.utils.ui.tools.switcher.base.SimpleStateQueueManager
import com.psutools.reminder.utils.ui.tools.switcher.base.StateSwitcher
import com.psutools.reminder.utils.ui.tools.switcher.base.StateViewProcessor
import com.psutools.reminder.utils.ui.tools.switcher.base.SwitchAnimationParams
import com.psutools.reminder.utils.ui.tools.switcher.base.processor.SetStateViewProcessor
import com.psutools.reminder.utils.ui.tools.switcher.base.processor.SetWithoutDuplicatesStateViewProcessor
import com.psutools.reminder.utils.ui.tools.switcher.base.processor.SwitchStateViewProcessor
import com.psutools.reminder.utils.ui.tools.switcher.base.processor.SwitchWithoutDuplicatesStateViewProcessor

/**
 * An alternative content switcher to ContentLoadingController. Uses state queue for managing states
 * @author m.a.kovalev
 */
class ContentStateSwitcher<T : Enum<T>> private constructor(
    private val defaultAnimationParams: SwitchAnimationParams
) : StateSwitcher<T, SimpleStateQueueManager<T>>() {

    private constructor(animationParams: SwitchAnimationParams, settings: ContentStateSwitcherSettings<T>) : this(animationParams) {
        setup(settings)
    }

    override val stateQueueManager: SimpleStateQueueManager<T> = SimpleStateQueueManager()

    val currentState: T
        get() = stateQueueManager.currentState

    /**
     * Removes all previous states and immediately sets a new [state] to the queue. [A -> B, C, D] -> [ NEW ]
     * if [ignoreSameViews] = true, state manager doesn't touch equal views while setting new state.
     */
    fun setState(
        state: T,
        animationParams: SwitchAnimationParams = SwitchAnimationParams.NoAnimation,
        ignoreSameViews: Boolean = false,
        onAnimationEnd: () -> Unit = {},
    ) {
        val processor =
            if (ignoreSameViews) SetWithoutDuplicatesStateViewProcessor(onAnimationEnd = onAnimationEnd)
            else SetStateViewProcessor(onAnimationEnd = onAnimationEnd)
        stateQueueManager.setState(state, animationParams, processor)
    }

    /**
     * Adds a new [state] to the end of queue. [A -> B, C, D] -> [A -> B, C, D, NEW]
     * if [ignoreSameViews] = true, state manager doesn't touch equal views while switching state.
     */
    fun addStateToQueue(
        state: T,
        animationParams: SwitchAnimationParams = defaultAnimationParams,
        ignoreSameViews: Boolean = false,
        onAnimationEnd: () -> Unit = {},
    ) {
        val processor =
            if (ignoreSameViews) SwitchWithoutDuplicatesStateViewProcessor(onAnimationEnd = onAnimationEnd)
            else SwitchStateViewProcessor(onAnimationEnd = onAnimationEnd)
        stateQueueManager.addStateToQueue(state, animationParams, processor)
    }

    /**
     * Preferable method.
     * Removes all further states and softly puts the [state] next to current one. [A -> B, C, D] -> [A -> B, NEW]
     * if [ignoreSameViews] = true, state manager doesn't touch equal views while switching state.
     */
    fun switchState(
        state: T,
        animationParams: SwitchAnimationParams = defaultAnimationParams,
        ignoreSameViews: Boolean = false,
        onAnimationEnd: () -> Unit = {},
    ) {
        val processor =
            if (ignoreSameViews) SwitchWithoutDuplicatesStateViewProcessor(onAnimationEnd = onAnimationEnd)
            else SwitchStateViewProcessor(onAnimationEnd = onAnimationEnd)
        stateQueueManager.switchState(state, animationParams, processor)
    }

    /**
     * Cancels current state switching process. Returns to Previous or Next state depending on [cancelMode]
     */
    fun cancelSwitching(
        cancelMode: StateViewProcessor.CancelAnimationMode
    ) {
        stateQueueManager.cancelAnimation(cancelMode)
    }

    class Builder<T : Enum<T>>(private val initialState: T, private val mapper: (T) -> List<View>) {

        private var defaultAnimationParams: SwitchAnimationParams = SwitchAnimationParams.Default

        fun withDefaultAnimationParams(animationParams: SwitchAnimationParams) {
            this.defaultAnimationParams = animationParams
        }

        fun build(): ContentStateSwitcher<T> {
            val settings = ContentStateSwitcherSettings(initialState, mapper)
            return ContentStateSwitcher(
                defaultAnimationParams,
                settings
            )
        }
    }
}

/**
 * Creates an instance of ContentStateSwitcher
 * @param initialState initial state for switcher
 * @param mapper matches state to list of views, that must be shown
 * @param builder is used for setting up additional state switcher options
 */
inline fun <reified T : Enum<T>> createContentStateSwitcher(
    initialState: T,
    noinline mapper: (T) -> List<View>,
    builder: ContentStateSwitcher.Builder<T>.() -> Unit = {}
): ContentStateSwitcher<T> {
    return ContentStateSwitcher.Builder(initialState, mapper).apply(builder).build()
}
