package edu.phystech.weather.screen.cityweather.adapter

import android.content.Context
import edu.phystech.weather.R
import edu.phystech.weather.databinding.FragmentCityWeatherBinding
import edu.phystech.weather.descriptors.entities.CurrentData
import edu.phystech.weather.utils.kelvinToCelsius
import edu.phystech.weather.utils.map
import edu.phystech.weather.utils.mapBackground

class CurrentWeatherAdapter(
    private val binding: FragmentCityWeatherBinding,
    private val context: Context
) {

    var data: CurrentData? = null
    set(value) {
        field = value
        onCreateLayout()
    }

    private fun onCreateLayout() {
        if (data != null ) {
            with(binding.nestedScroll.uvWindHumidity) {
                humidityValue.text = data!!.humidity.toString() + "%"
                windRate.text = data!!.wind_speed.toInt().toString() + " км/ч"
            }
            with(binding) {
                currentTemperature.text = kelvinToCelsius(data!!.temp).toString() + "\u00B0"
                feelsLike.text = context.getString(R.string.feels_like) + " " + kelvinToCelsius(data!!.feels_like).toString() + "\u00B0"
                currentTime.timeZone = data!!.timezone
                root.setBackgroundResource(mapBackground(data!!.icon))
                currentWeatherIcon.setImageResource(map(data!!.icon))
            }
        }
    }
}