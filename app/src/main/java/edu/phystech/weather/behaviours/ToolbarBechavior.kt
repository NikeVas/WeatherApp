package edu.phystech.weather.behaviours

import android.content.Context
import android.graphics.Color
import android.util.AttributeSet
import android.util.Log
import android.view.View
import androidx.appcompat.widget.Toolbar
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.core.graphics.drawable.toDrawable
import androidx.core.view.get
import edu.phystech.weather.App
import edu.phystech.weather.R
import java.lang.Float.min

class ToolbarBechavior(context: Context?, attrs: AttributeSet?) :
    CommonBechavior(context, attrs) {

    init {}

    override fun calculatePosition(avatar: View, progress: Float, parent: CoordinatorLayout) {
        (avatar as? Toolbar)?.findViewById<View>(R.id.tool_city)?.alpha = 2F * progress
        parent.background.alpha = ((1 - progress) * 255).toInt()
        if (progress < 0.5) {
            (parent.context.applicationContext as App).setStartStyles()
        } else {
            (parent.context.applicationContext as App).setFinalStyles()
        }
//        parent.background = (Color.BLACK * progress).toInt().toDrawable()
//        Log.e("abaoba", "Toolbar")
//        avatar
    }
}