package edu.phystech.weather.adapter

import android.view.LayoutInflater
import android.widget.LinearLayout
import edu.phystech.weather.databinding.UvWindHumidityBinding
import edu.phystech.weather.descriptors.entities.CurrentData
import edu.phystech.weather.descriptors.entities.Day

class CurrentWeatherAdapter(
    val binding: UvWindHumidityBinding
) {

    var data: CurrentData? = null
    set(value) {
        field = value
        onCreateLayout()
    }

    private fun onCreateLayout() {
        if (data != null ) {
            binding.humidityValue.text = data!!.humidity.toString() + "%"
            binding.windRate.text = data!!.wind_speed.toString() + "км/ч"
        }
    }
}