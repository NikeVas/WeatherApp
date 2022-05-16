package edu.phystech.weather.behaviours

import android.content.Context
import android.content.res.Resources
import android.util.AttributeSet
import android.view.View
import androidx.coordinatorlayout.widget.CoordinatorLayout
import edu.phystech.weather.R

class HolderBechavior(context: Context?, attrs: AttributeSet?) :
    CommonBechavior(context, attrs) {

    init {}


    override fun calculatePosition(avatar: View, progress: Float, parent: CoordinatorLayout) {
        avatar.y = parent.resources.getDimension(R.dimen.holder_y)
        avatar.x = parent.resources.getDimension(R.dimen.holder_x)
        avatar.alpha = 1 - 3 * progress
    }
}

class SecondHolderBechavior(context: Context?, attrs: AttributeSet?) :
    CommonBechavior(context, attrs) {

    init {}


    override fun calculatePosition(avatar: View, progress: Float, parent: CoordinatorLayout) {
        avatar.y = parent.resources.getDimension(R.dimen.sholder_y)
        avatar.x = parent.resources.getDimension(R.dimen.sholder_x)
    }
}