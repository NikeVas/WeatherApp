package edu.phystech.weather.descriptors.entities

import androidx.room.ColumnInfo

data class HourlyData (
    val hours : List<Hour>
)

data class Hour (
    val dt: Long,

    val temp: Float,
    val feels_like: Float,

    val humidity: Long,
    val wind_speed: Float,
    val wind_deg: Long,
    val uvi: Float,
    val icon: String,
)