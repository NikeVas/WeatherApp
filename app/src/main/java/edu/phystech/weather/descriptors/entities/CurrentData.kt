package edu.phystech.weather.descriptors.entities

data class CurrentData (
    val timezone: String,
    val sunrise: Long,
    val sunset: Long,

    val temp: Float,
    val temp_min: Float,
    val temp_max: Float,
    val feels_like: Float,

    val humidity: Long,
    val wind_speed: Float,
    val wind_deg: Long,
    val uvi: Float,
    val icon: String,
)