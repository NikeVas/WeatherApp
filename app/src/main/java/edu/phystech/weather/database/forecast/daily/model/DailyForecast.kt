package edu.phystech.weather.database.forecast.daily.model


data class DailyForecastDBEntity(
    val id: Int,
    val city: String,
    val day: String,
    val main: String,
    val humidity: Int,
)
