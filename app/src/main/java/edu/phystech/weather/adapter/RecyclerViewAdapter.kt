package edu.phystech.weather.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import edu.phystech.weather.databinding.RecycleViewItemDayForecastBinding
import edu.phystech.weather.models.TimeTemperature

class RecyclerViewAdapter : RecyclerView.Adapter<RecyclerViewAdapter.TimeTemperatureViewHolder>() {

    var data: List<TimeTemperature> = emptyList()
        set(value) {
            field = value
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
            temperature.text = item.temperature.toString()
            time.text = item.time
        }
    }

    override fun getItemCount(): Int {
        return data.size
    }
}