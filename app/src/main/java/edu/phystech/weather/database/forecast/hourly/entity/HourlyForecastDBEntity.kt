package edu.phystech.weather.database.forecast.hourly.entity

import androidx.room.ColumnInfo
import androidx.room.Entity


@Entity(
    tableName = "hourly_forecast",
    primaryKeys = ["city", "dt"]
)
data class HourlyForecastDBEntity (
    @ColumnInfo(name = "city") val city: String,
    @ColumnInfo(name = "dt") val dt: Long,

    @ColumnInfo(name = "temp") val temp: Float,
    @ColumnInfo(name = "feels_like") val feels_like: Float,

    @ColumnInfo(name = "humidity") val humidity: Long,
    @ColumnInfo(name = "wind_speed") val wind_speed: Long,
    @ColumnInfo(name = "wind_deg") val wind_deg: Long,
    @ColumnInfo(name = "uvi") val uvi: Float,
    @ColumnInfo(name = "icon") val icon: String,
)