package edu.phystech.weather.behaviours

import android.content.Context
import android.content.res.Resources
import android.graphics.Color
import android.util.AttributeSet
import android.util.Log
import android.view.View
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.core.graphics.drawable.toDrawable
import edu.phystech.weather.R

class HolderBechavior(context: Context?, attrs: AttributeSet?) :
    CommonBechavior(context, attrs) {

    init {}


    override fun calculatePosition(avatar: View, progress: Float, parent: CoordinatorLayout) {
        Log.e("aboao", "Background")
        avatar.background = (Color.BLACK * progress).toInt().toDrawable()
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