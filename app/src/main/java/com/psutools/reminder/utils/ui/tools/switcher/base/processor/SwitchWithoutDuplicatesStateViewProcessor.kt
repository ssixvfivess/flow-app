package com.psutools.reminder.utils.ui.tools.switcher.base.processor

import android.animation.ValueAnimator
import android.view.View
import androidx.core.animation.doOnCancel
import androidx.core.animation.doOnEnd
import com.psutools.reminder.utils.ui.tools.switcher.base.StateViewProcessor
import com.psutools.reminder.utils.ui.tools.switcher.base.SwitchAnimationParams

/**
 * Switches two lists of views, but doesn't touch the views included in both lists
 * @author m.a.kovalev
 */
class SwitchWithoutDuplicatesStateViewProcessor(
    private val onAnimationEnd: () -> Unit = {},
) : StateViewProcessor() {

    private var currentStateAnimator: ValueAnimator? = null
    private var isCancelled: Boolean = false
    private var cancelMode: CancelAnimationMode = CancelAnimationMode.BACKWARD

    override fun cancelAnimation(mode: CancelAnimationMode) {
        cancelMode = mode
        currentStateAnimator?.cancel()
    }

    override fun processStateTransition(
        fromViews: List<View>,
        toViews: List<View>,
        animationParams: SwitchAnimationParams,
        switchStateListener: SwitchStateListener?,
    ) {
        val fromViewsOuter = fromViews.subtract(toViews.toSet()).toList()
        val toViewsOuter = toViews.subtract(fromViews.toSet()).toList()
        prepareViewsForSwitch(fromViewsOuter, toViewsOuter)

        if (animationParams != SwitchAnimationParams.NoAnimation) {
            currentStateAnimator = ValueAnimator.ofFloat(ZERO_ALPHA, FULL_ALPHA).apply {
                startDelay = animationParams.delay
                duration = animationParams.duration
                val interpolator = animationParams.interpolator

                addUpdateListener { value ->
                    val ratio = value.animatedValue as Float

                    val fromViewsAlpha = interpolator.calculateSwitchOutValue(ratio)
                    val toViewsAlpha = interpolator.calculateSwitchInValue(ratio)

                    fromViewsOuter.setAlpha(fromViewsAlpha)
                    toViewsOuter.setAlpha(toViewsAlpha)
                }
                doOnCancel {
                    isCancelled = true
                }
                doOnEnd {
                    if (isCancelled) {
                        when (cancelMode) {
                            CancelAnimationMode.FORWARD -> setTerminateState(fromViewsOuter, toViewsOuter)
                            CancelAnimationMode.BACKWARD -> setTerminateState(toViewsOuter, fromViewsOuter)
                        }
                        isCancelled = false
                        switchStateListener?.onSwitchingCanceled(cancelMode)
                    } else {
                        setTerminateState(fromViewsOuter, toViewsOuter)
                        switchStateListener?.onStateSwitched()
                        onAnimationEnd()
                    }
                }
            }
            currentStateAnimator?.start()
            switchStateListener?.onSwitchingStarted()
        } else {
            switchStateListener?.onSwitchingStarted()
            setTerminateState(fromViewsOuter, toViewsOuter)
            switchStateListener?.onStateSwitched()
            onAnimationEnd()
        }
    }

    private fun setTerminateState(fromViews: List<View>, toViews: List<View>) {
        fromViews.setupParams(alpha = ZERO_ALPHA, visibility = View.INVISIBLE)
        toViews.setupParams(alpha = FULL_ALPHA, visibility = View.VISIBLE)
    }

    private fun prepareViewsForSwitch(fromViews: List<View>, toViews: List<View>) {
        fromViews.setupParams(alpha = FULL_ALPHA, visibility = View.VISIBLE)
        toViews.setupParams(alpha = ZERO_ALPHA, visibility = View.VISIBLE)
    }
}
