package edu.phystech.weather.models

data class DailyForecast(
    val day: String,
    val humidity: Int,
    val dayTemperature: Int,
    val nightTemperature: Int
)
