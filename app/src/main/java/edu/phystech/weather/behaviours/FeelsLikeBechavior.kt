package edu.phystech.weather.behaviours

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.coordinatorlayout.widget.CoordinatorLayout
import edu.phystech.weather.R

class FeelsLikeBechavior(context: Context?, attrs: AttributeSet?) :
    CommonBechavior(context, attrs) {

    init {}

    override fun calculatePosition(avatar: View, progress: Float, parent: CoordinatorLayout) {

        val finalXPosition = parent.resources.getDimension(R.dimen.feels_like_final_x)
        val finalYPosition = parent.resources.getDimension(R.dimen.feels_like_final_y)

        val startXPosition = (parent.resources.getDimension(R.dimen.min_max_start_x) +
                parent.findViewById<View>(R.id.temp_min_max_t).width * 1.2).toInt()
        val startYPosition =  parent.resources.getDimension(R.dimen.min_max_start_y)

        avatar.y = (finalYPosition - startYPosition) * progress + startYPosition
        avatar.x = (finalXPosition - startXPosition) * progress + startXPosition
        avatar.alpha = 1 - 2F * progress
    }

}