package edu.phystech.weather.behaviours

import android.content.Context
import android.util.AttributeSet
import android.view.View
import androidx.coordinatorlayout.widget.CoordinatorLayout
import edu.phystech.weather.R

class ImageBechavior(context: Context?, attrs: AttributeSet?) :
    CommonBechavior(context, attrs) {

    init {}


    override fun calculatePosition(avatar: View, progress: Float, parent: CoordinatorLayout) {
        val startXPosition = parent.resources.getDimension(R.dimen.image_start_x)
        val startYPosition = parent.resources.getDimension(R.dimen.image_start_y)
        val finalXPosition = parent.resources.getDimension(R.dimen.image_final_x)
        val finalYPosition = parent.resources.getDimension(R.dimen.image_final_y)

        avatar.y = (finalYPosition - startYPosition) * progress + startYPosition
        avatar.x = (finalXPosition - startXPosition) * progress + startXPosition
    }
}