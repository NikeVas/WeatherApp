package edu.phystech.weather.descriptors.entities

data class CurrentData (
    val sunrise: Long,
    val sunset: Long,

    val temp: Float,
    val feels_like: Float,

    val humidity: Long,
    val wind_speed: Float,
    val wind_deg: Long,
    val icon: String,
)