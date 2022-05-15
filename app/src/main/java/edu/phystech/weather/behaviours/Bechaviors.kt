package edu.phystech.weather

import android.content.Context
import android.content.res.Resources
import android.graphics.Color
import android.util.AttributeSet
import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.compose.ui.unit.Dp
import edu.phystech.weather.behaviours.CommonBechavior
import kotlin.math.abs
import kotlin.properties.Delegates


class AvatarImageBehavior(context: Context?, attrs: AttributeSet?) :
    CommonBechavior(context, attrs) {

    init {}


    override fun calculatePosition(avatar: View, progress: Float, resources: Resources) {
        val startXPosition = resources.getDimension(R.dimen.temperature_start_x)
        val startYPosition = resources.getDimension(R.dimen.temperature_start_y)
        val finalXPosition = resources.getDimension(R.dimen.temperature_final_x)
        val finalYPosition = resources.getDimension(R.dimen.temperature_final_y)

        avatar.y = (finalYPosition - startYPosition) * progress + startYPosition
        avatar.x = (finalXPosition - startXPosition) * progress + startXPosition
        avatar.alpha = abs(progress - 0.5F)
        (avatar as? TextView)?.setTextColor((Color.rgb( progress,  progress, progress)))
    }

}