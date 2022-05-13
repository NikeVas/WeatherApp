package edu.phystech.weather.behaviours

import android.content.Context
import android.util.AttributeSet
import android.view.View
import androidx.appcompat.widget.Toolbar
import androidx.coordinatorlayout.widget.CoordinatorLayout
import edu.phystech.weather.R

class BackgroundOpacityBehaviour(context: Context?, attrs: AttributeSet?) :
    CoordinatorLayout.Behavior<CoordinatorLayout?> (context, attrs) {

    override fun onDependentViewChanged(
        parent: CoordinatorLayout,
        child: CoordinatorLayout,
        dependency: View
    ): Boolean {
        val toolbarHeight = parent.findViewById<Toolbar>(R.id.toolbar).height
        val height = dependency.height
        val current = dependency.bottom.toFloat()

        var progress = (current - toolbarHeight) / (height - toolbarHeight)
        if (progress < 0) {
            progress = 0F
        }
        progress = 1 - progress

        val coordinator = parent.findViewById<CoordinatorLayout>(R.id.coordinator)
        coordinator.alpha = progress
        return true
    }
}