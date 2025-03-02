package com.psutools.reminder.utils.ui.tools.switcher.base.processor

import android.animation.ValueAnimator
import android.view.View
import androidx.core.animation.doOnCancel
import androidx.core.animation.doOnEnd
import com.psutools.reminder.utils.ui.tools.switcher.base.StateViewProcessor
import com.psutools.reminder.utils.ui.tools.switcher.base.SwitchAnimationParams

class SetWithoutDuplicatesStateViewProcessor(
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

                    val toViewsAlpha = interpolator.calculateSwitchInValue(ratio)
                    toViewsOuter.setAlpha(toViewsAlpha)
                }
                doOnCancel {
                    isCancelled = true
                }
                doOnEnd {
                    if (isCancelled) {
                        when (cancelMode) {
                            CancelAnimationMode.FORWARD -> setTerminateState(toViewsOuter)
                            CancelAnimationMode.BACKWARD -> setTerminateState(fromViewsOuter)
                        }
                        isCancelled = false
                        switchStateListener?.onSwitchingCanceled(cancelMode)
                    } else {
                        setTerminateState(toViewsOuter)
                        switchStateListener?.onStateSwitched()
                        onAnimationEnd()
                    }
                }
            }
            currentStateAnimator?.start()
            switchStateListener?.onSwitchingStarted()
        } else {
            switchStateListener?.onSwitchingStarted()
            setTerminateState(toViewsOuter)
            switchStateListener?.onStateSwitched()
            onAnimationEnd()
        }
    }

    private fun setTerminateState(views: List<View>) {
        views.setupParams(alpha = FULL_ALPHA, visibility = View.VISIBLE)
    }

    private fun prepareViewsForSwitch(fromViews: List<View>, toViews: List<View>) {
        fromViews.setupParams(alpha = ZERO_ALPHA, visibility = View.INVISIBLE)
        toViews.setupParams(alpha = ZERO_ALPHA, visibility = View.VISIBLE)
    }
}
