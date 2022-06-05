package edu.phystech.weather.screen.cityweather.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import edu.phystech.weather.databinding.RecycleViewItemDayForecastBinding
import edu.phystech.weather.descriptors.entities.Hour
import edu.phystech.weather.utils.*

class DailyForecastRecyclerViewAdapter : RecyclerView.Adapter<DailyForecastRecyclerViewAdapter.TimeTemperatureViewHolder>() {

    var data: List<Hour> = emptyList()
        set(value) {
            for (i in (0..value.size - 1)) {
                if (value[i].dt >= currentTime()) {
                    if (i + NUMBER_OF_DISPLAYING_HOURS < value.size) {
                        field = value.subList(i, i + NUMBER_OF_DISPLAYING_HOURS)
                    } else {
                        field = value
                    }
                    break
                }
            }
            //field = value.subList(0, 24)
            notifyDataSetChanged()
        }

    class TimeTemperatureViewHolder (
        val binding : RecycleViewItemDayForecastBinding
            ): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TimeTemperatureViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = RecycleViewItemDayForecastBinding.inflate(inflater, parent, false)
        return TimeTemperatureViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TimeTemperatureViewHolder, position: Int) {
        val item = data[position]
        with(holder.binding) {
            temperature.text = kelvinToCelsius(item.temp).toString() + "\u00B0"
            time.text = unixToDate(item.dt - currentTimeZoneOffset() + item.timezone_offset).subSequence(11, 16)
            hourlyWeatherIcon.setImageResource(map(item.icon))
        }
    }

    override fun getItemCount(): Int {
        return data.size
    }

    companion object {
        const val NUMBER_OF_DISPLAYING_HOURS = 24
    }
}