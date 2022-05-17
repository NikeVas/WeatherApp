package edu.phystech.weather.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.content.res.TypedArrayUtils.getString
import androidx.core.content.res.TypedArrayUtils.getText
import edu.phystech.weather.R
import edu.phystech.weather.descriptors.entities.Day
import edu.phystech.weather.descriptors.entities.Hour
import edu.phystech.weather.utils.*

class WeekForecastAdapter(
    val linearLayout: LinearLayout,
    val layoutInflater: LayoutInflater
) {

    var data: List<Day> = emptyList()
        set(value) {
            field = value
            onCreateLayout()
        }

    private var items = mutableListOf<View>()

    fun onCreateLayout() {
        if (items.isNotEmpty()) {
            linearLayout.removeAllViews()
            items.clear()
        }
        Log.e("weekForecastFirst", unixToDate(data[0].dt))
        Log.e("weekForecastFirst", data[0].dt.toString())
        for (day in data.subList(0, 7)) {

            val item = layoutInflater.inflate(R.layout.week_list_item, null)
            item.findViewById<TextView>(R.id.day).text = unixToDayOfWeek(day.dt)
            item.findViewById<TextView>(R.id.daily_humidity).text = day.humidity.toString() + "%"
            item.findViewById<ImageView>(R.id.daily_icon).setImageResource(map(day.icon))
            item.findViewById<TextView>(R.id.max_min_temp).text =
                kelvinToCelsius(day.temp_max).toString() + "/" + kelvinToCelsius(day.temp_min).toString() + "\u00B0C"
            items.add(item)
            linearLayout.addView(item)
            if (unixToDayOfWeek(day.dt) == unixToDayOfWeek(currentTime())) {
                item.findViewById<TextView>(R.id.day).text = "Сегодня"
            }
        }
    }
}