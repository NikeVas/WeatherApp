package edu.phystech.weather.adapter

import android.util.Log
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
                Log.e("aboba", unixToDate(value[i].dt).toString())
                Log.e("current", unixToDate(currentTime()))
                if (value[i].dt >= currentTime()) {
                    field = value.subList(i, i + 24)
                    break
                }
            }
            //field = value.subList(0, 24)
            Log.e("recview", value.size.toString())
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
        Log.e("fragment", data.size.toString())
        with(holder.binding) {
            temperature.text = kelvinToCelsius(item.temp).toString() + "\u00B0"
            Log.e("offset", item.timezone_offset.toString())
            Log.e("offset", currentTimeZoneOffset().toString())
            time.text = unixToDate(item.dt - currentTimeZoneOffset() + item.timezone_offset).subSequence(11, 16)
            hourlyWeatherIcon.setImageResource(map(item.icon))
        }
    }

    override fun getItemCount(): Int {
        return data.size
    }
}