package edu.phystech.weather.descriptors.entities

import androidx.room.ColumnInfo


data class DailyData(
    val days: List<Day>
)

data class Day(
    val dt: Long,
    val timezone_offset: Long,

    val sunrise: Long,
    val sunset: Long,

    val temp_morn: Float,
    val temp_day: Float,
    val temp_eve: Float,
    val temp_night: Float,
    val temp_min: Float,
    val temp_max: Float,

    val feels_like_morn: Float,
    val feels_like_day: Float,
    val feels_like_eve: Float,
    val feels_like_night: Float,

    val humidity: Long,
    val wind_speed: Float,
    val wind_deg: Long,
    val uvi: Float,
    val icon: String,
)