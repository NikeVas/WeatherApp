package edu.phystech.weather.behaviours

import android.content.Context
import android.content.res.Resources
import android.util.AttributeSet
import android.util.Log
import android.view.View
import androidx.coordinatorlayout.widget.CoordinatorLayout
import edu.phystech.weather.R

abstract class CommonBechavior(context: Context?, attrs: AttributeSet?) :
    CoordinatorLayout.Behavior<View?>(context, attrs) {

    override fun onDependentViewChanged(
        parent: CoordinatorLayout,
        avatar: View,
        dependency: View
    ): Boolean {

        val toolbar_layout = parent.findViewById<View>(R.id.toolbar_layout)
        val app_bar = parent.findViewById<View>(R.id.app_bar)

        val max_pos = app_bar.height;
        val min_pos = toolbar_layout.height;
        val current_pos = app_bar.bottom;

        val progress = 1 - (current_pos.toFloat() - min_pos) / (max_pos - min_pos)

        calculatePosition(avatar, progress, parent.resources)

        return true
    }

    abstract fun calculatePosition(avatar: View, progress: Float, resources: Resources);
}