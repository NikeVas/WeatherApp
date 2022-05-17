package edu.phystech.weather.behaviours

import android.content.Context
import android.graphics.Color
import android.util.AttributeSet
import android.util.Log
import android.view.View
import androidx.appcompat.widget.Toolbar
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.core.view.get
import edu.phystech.weather.R
import java.lang.Float.min

class ToolbarBechavior(context: Context?, attrs: AttributeSet?) :
    CommonBechavior(context, attrs) {

    init {}

    override fun calculatePosition(avatar: View, progress: Float, parent: CoordinatorLayout) {
        (avatar as? Toolbar)?.findViewById<View>(R.id.tool_city)?.alpha = 2F * progress
//        Log.e("abaoba", "Toolbar")
//        avatar
    }
}