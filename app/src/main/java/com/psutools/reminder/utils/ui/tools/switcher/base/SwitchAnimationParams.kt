package com.psutools.reminder.utils.ui.tools.switcher.base

import com.psutools.reminder.utils.ui.tools.switcher.base.interpolator.ConsecutiveSwitchInterpolator

sealed class SwitchAnimationParams(
    open val delay: Long,
    open val duration: Long,
    open val interpolator: StateViewProcessor.SwitchInterpolator
) {

    data object Default : SwitchAnimationParams(
        delay = DEFAULT_ANIMATION_DELAY,
        duration = DEFAULT_ANIMATION_DURATION,
        interpolator = ConsecutiveSwitchInterpolator()
    )

    data object NoAnimation : SwitchAnimationParams(
        delay = NO_ANIMATION_DELAY,
        duration = NO_ANIMATION_DURATION,
        interpolator = ConsecutiveSwitchInterpolator()
    )

    data class Custom(
        override val delay: Long = DEFAULT_ANIMATION_DELAY,
        override val duration: Long = DEFAULT_ANIMATION_DURATION,
        override val interpolator: StateViewProcessor.SwitchInterpolator = ConsecutiveSwitchInterpolator()
    ) : SwitchAnimationParams(delay, duration, interpolator)

    private companion object {
        const val DEFAULT_ANIMATION_DURATION = 500L
        const val DEFAULT_ANIMATION_DELAY = 0L

        const val NO_ANIMATION_DURATION = 0L
        const val NO_ANIMATION_DELAY = 0L
    }
}
