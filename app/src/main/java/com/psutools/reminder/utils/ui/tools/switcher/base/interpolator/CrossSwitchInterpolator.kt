package com.psutools.reminder.utils.ui.tools.switcher.base.interpolator

import com.psutools.reminder.utils.ui.tools.switcher.base.StateViewProcessor

/**
 * The interpolator which represents cross-changing strategy.
 * ex. [fromViewsValue, toViewsValue]:
 *     [1.0, 0.0] -> [0.5, 0.5] -> [0.0, 1.0]
 */
class CrossSwitchInterpolator : StateViewProcessor.SwitchInterpolator {

    override fun calculateSwitchInValue(ratio: Float): Float {
        return ratio
    }

    override fun calculateSwitchOutValue(ratio: Float): Float {
        return (1.0F - ratio)
    }
}
