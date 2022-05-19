package edu.phystech.weather.behaviours

import android.content.Context
import android.graphics.Color
import android.util.AttributeSet
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.core.graphics.drawable.toDrawable
import androidx.core.view.get
import androidx.core.view.iterator
import androidx.recyclerview.widget.RecyclerView
import edu.phystech.weather.App
import edu.phystech.weather.R
import org.w3c.dom.Text
import java.lang.Float.min

class ToolbarBechavior(context: Context?, attrs: AttributeSet?) :
    CommonBechavior(context, attrs) {

    init {}

    override fun calculatePosition(avatar: View, progress: Float, parent: CoordinatorLayout) {
        (avatar as? Toolbar)?.findViewById<View>(R.id.tool_city)?.alpha = progress

        parent.background.alpha = ((1 - progress) * 255).toInt()

        if (progress < 0.5) {
            parent.findViewById<View>(R.id.day_forecast).setBackgroundResource(R.drawable.background_rectangle)
            for (elem in parent.findViewById<RecyclerView>(R.id.day_forecast)) {
                elem.findViewById<TextView>(R.id.time).setTextColor(Color.WHITE)
                elem.findViewById<TextView>(R.id.temperature).setTextColor(Color.WHITE)
            }
            parent.findViewById<View>(R.id.week_forecast).setBackgroundResource(R.drawable.background_rectangle)
            for (elem in parent.findViewById<LinearLayout>(R.id.week_forecast)) {
                elem.findViewById<TextView>(R.id.day).setTextColor(Color.WHITE)
                elem.findViewById<TextView>(R.id.max_min_temp).setTextColor(Color.WHITE)
                elem.findViewById<TextView>(R.id.daily_humidity).setTextColor(Color.WHITE)
            }
            parent.findViewById<View>(R.id.sunset_sunrise).setBackgroundResource(R.drawable.background_rectangle)
            parent.findViewById<View>(R.id.sunset_sunrise).findViewById<TextView>(R.id.sunrise_title).setTextColor(Color.WHITE)
            parent.findViewById<View>(R.id.sunset_sunrise).findViewById<TextView>(R.id.sunset_title).setTextColor(Color.WHITE)
            parent.findViewById<View>(R.id.sunset_sunrise).findViewById<TextView>(R.id.sunrise_time).setTextColor(Color.WHITE)
            parent.findViewById<View>(R.id.sunset_sunrise).findViewById<TextView>(R.id.sunset_time).setTextColor(Color.WHITE)

            parent.findViewById<View>(R.id.uv_wind_humidity).setBackgroundResource(R.drawable.background_rectangle)

            parent.findViewById<View>(R.id.uv_wind_humidity).findViewById<TextView>(R.id.uv_text).setTextColor(Color.WHITE)
            parent.findViewById<View>(R.id.uv_wind_humidity).findViewById<TextView>(R.id.uv_index).setTextColor(Color.WHITE)
            parent.findViewById<View>(R.id.uv_wind_humidity).findViewById<TextView>(R.id.wind_text).setTextColor(Color.WHITE)
            parent.findViewById<View>(R.id.uv_wind_humidity).findViewById<TextView>(R.id.wind_rate).setTextColor(Color.WHITE)
            parent.findViewById<View>(R.id.uv_wind_humidity).findViewById<TextView>(R.id.humidity_text).setTextColor(Color.WHITE)
            parent.findViewById<View>(R.id.uv_wind_humidity).findViewById<TextView>(R.id.humidity_value).setTextColor(Color.WHITE)
        } else {
            parent.findViewById<View>(R.id.day_forecast).setBackgroundResource(R.drawable.background_rectangle_white)
            for (elem in parent.findViewById<RecyclerView>(R.id.day_forecast)) {
                elem.findViewById<TextView>(R.id.time).setTextColor(Color.GRAY)
                elem.findViewById<TextView>(R.id.temperature).setTextColor(Color.BLACK)
            }
            parent.findViewById<View>(R.id.week_forecast).setBackgroundResource(R.drawable.background_rectangle_white)
            for (elem in parent.findViewById<LinearLayout>(R.id.week_forecast)) {
                elem.findViewById<TextView>(R.id.day).setTextColor(Color.BLACK)
                elem.findViewById<TextView>(R.id.max_min_temp).setTextColor(Color.BLACK)
                elem.findViewById<TextView>(R.id.daily_humidity).setTextColor(Color.BLACK)
            }
            parent.findViewById<View>(R.id.sunset_sunrise).setBackgroundResource(R.drawable.background_rectangle_white)

            parent.findViewById<View>(R.id.sunset_sunrise).findViewById<TextView>(R.id.sunrise_title).setTextColor(Color.BLACK)
            parent.findViewById<View>(R.id.sunset_sunrise).findViewById<TextView>(R.id.sunset_title).setTextColor(Color.BLACK)
            parent.findViewById<View>(R.id.sunset_sunrise).findViewById<TextView>(R.id.sunrise_time).setTextColor(Color.BLACK)
            parent.findViewById<View>(R.id.sunset_sunrise).findViewById<TextView>(R.id.sunset_time).setTextColor(Color.BLACK)


            parent.findViewById<View>(R.id.uv_wind_humidity).setBackgroundResource(R.drawable.background_rectangle_white)

            parent.findViewById<View>(R.id.uv_wind_humidity).findViewById<TextView>(R.id.uv_text).setTextColor(Color.BLACK)
            parent.findViewById<View>(R.id.uv_wind_humidity).findViewById<TextView>(R.id.uv_index).setTextColor(Color.GRAY)
            parent.findViewById<View>(R.id.uv_wind_humidity).findViewById<TextView>(R.id.wind_text).setTextColor(Color.BLACK)
            parent.findViewById<View>(R.id.uv_wind_humidity).findViewById<TextView>(R.id.wind_rate).setTextColor(Color.GRAY)
            parent.findViewById<View>(R.id.uv_wind_humidity).findViewById<TextView>(R.id.humidity_text).setTextColor(Color.BLACK)
            parent.findViewById<View>(R.id.uv_wind_humidity).findViewById<TextView>(R.id.humidity_value).setTextColor(Color.GRAY)

        }
//        parent.background = (Color.BLACK * progress).toInt().toDrawable()
//        Log.e("abaoba", "Toolbar")
//        avatar
    }
}