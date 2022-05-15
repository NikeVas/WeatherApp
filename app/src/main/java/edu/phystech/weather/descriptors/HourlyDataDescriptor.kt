package edu.phystech.weather.descriptors

import edu.phystech.weather.database.forecast.hourly.HourlyForecastDB
import edu.phystech.weather.weatherapi.WeatherAPI

class HourlyDataDescriptor(private val weatherAPI: WeatherAPI, private val database: HourlyForecastDB) {
}