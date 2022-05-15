package edu.phystech.weather

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.core.view.ViewCompat
import com.google.android.material.appbar.AppBarLayout
import kotlin.properties.Delegates


class AvatarImageBehavior(context: Context?, attrs: AttributeSet?) :
    CoordinatorLayout.Behavior<View?>(context, attrs) {



    var finalXPosition by Delegates.notNull<Float>()
    var finalYPosition by Delegates.notNull<Float>()
    var startXPosition by Delegates.notNull<Float>()
    var startYPosition by Delegates.notNull<Float>()


    init {
        finalXPosition = 100F
        finalYPosition = 100F
        startXPosition = 100F
        startYPosition = 500F
    }

    override fun onDependentViewChanged(
        parent: CoordinatorLayout,
        avatar: View,
        dependency: View
    ): Boolean {
//        val toolbar_height = parent.findViewById<Toolbar>(R.id.toolbar).height
//        val height = dependency.height
//        val current = dependency.bottom.toFloat()
//
//        var progress = (current - toolbar_height) / (height - toolbar_height)
//        if (progress < 0) {
//            progress = 0F
//        }
//        progress = 1 - progress
//
//        calculatePosition(avatar, progress)
//
        return true
    }


    private fun calculatePosition(avatar: View, progress : Float) {
        avatar.setY((finalYPosition - startYPosition) * progress + startYPosition)
        avatar.setX((finalXPosition - startXPosition) * progress + startXPosition)
        Log.e("aboba", avatar.x.toString() + " " +  avatar.y.toString())
    }

}