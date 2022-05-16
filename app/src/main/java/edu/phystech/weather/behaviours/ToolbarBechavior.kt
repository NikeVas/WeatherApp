package edu.phystech.weather.behaviours

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.View
import androidx.appcompat.widget.Toolbar
import androidx.coordinatorlayout.widget.CoordinatorLayout
import edu.phystech.weather.R

class ToolbarBechavior(context: Context?, attrs: AttributeSet?) :
    CommonBechavior(context, attrs) {

    init {}

    override fun calculatePosition(avatar: View, progress: Float, parent: CoordinatorLayout) {
        val toolbar = avatar as Toolbar
        Log.e("aboba", "Toolbar")
//        toolbar.setTitleTextAppearance()
    }
}