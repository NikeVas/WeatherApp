package edu.phystech.weather.behaviours

import android.content.Context
import android.content.res.Resources
import android.graphics.Color
import android.util.AttributeSet
import android.util.Log
import android.view.View
import android.widget.TextView
import edu.phystech.weather.R
import kotlin.math.abs


class FeelsLikeBechavior(context: Context?, attrs: AttributeSet?) :
    CommonBechavior(context, attrs) {

    init {}


    override fun calculatePosition(avatar: View, progress: Float, resources: Resources) {
        val startXPosition = resources.getDimension(R.dimen.feels_like_start_x)
        val startYPosition = resources.getDimension(R.dimen.feels_like_start_y)
        val finalXPosition = resources.getDimension(R.dimen.feels_like_final_x)
        val finalYPosition = resources.getDimension(R.dimen.feels_like_final_y)

        Log.e("aboba", startXPosition.toString() + " " + startYPosition.toString() + " " + finalXPosition.toString() + " " + finalYPosition.toString())
        Log.e("aboba", avatar.x.toString() + " " + avatar.y.toString())
        avatar.y = (finalYPosition - startYPosition) * progress + startYPosition
        avatar.x = (finalXPosition - startXPosition) * progress + startXPosition
        avatar.alpha = abs(progress - 0.5F)
//        (avatar as? TextView)?.setTextColor((Color.rgb( progress,  progress, progress)))
        (avatar as? TextView)?.setTextColor((Color.BLACK * progress).toInt())
    }

}