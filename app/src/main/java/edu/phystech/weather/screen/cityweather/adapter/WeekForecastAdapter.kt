package edu.phystech.weather.screen.cityweather.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import edu.phystech.weather.R
import edu.phystech.weather.databinding.FragmentCityWeatherBinding
import edu.phystech.weather.descriptors.entities.Day
import edu.phystech.weather.utils.*

class WeekForecastAdapter(
    private val binding: FragmentCityWeatherBinding,
    private val layoutInflater: LayoutInflater
) {

    var data: List<Day> = emptyList()
        set(value) {
            field = value
            onCreateLayout()
        }

    private var items = mutableListOf<View>()

    fun onCreateLayout() {
        if (items.isNotEmpty()) {
            binding.nestedScroll.weekForecast.removeAllViews()
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
                kelvinToCelsius(day.temp_max).toString() + "\u00B0" + "/" + kelvinToCelsius(day.temp_min).toString() + "\u00B0"

            if (dateToDay(unixToDate(day.dt)) == currentDay()) {
                item.findViewById<TextView>(R.id.day).text = "Сегодня"
                binding.nestedScroll.sunsetSunrise.sunriseTime.text = unixToHours(day.sunrise)
                binding.nestedScroll.sunsetSunrise.sunsetTime.text = unixToHours(day.sunset)
                binding.nestedScroll.uvWindHumidity.uvIndex.text = uvToString(day.uvi)
                binding.tempMinMaxT.text = toTempMinMaxFormat(day.temp_max, day.temp_min)
                binding.currentDay.text = abbreviateDay(currentDayOfWeek()).lowercase() + ", "
            }

            items.add(item)
            binding.nestedScroll.weekForecast.addView(item)
        }
    }
}