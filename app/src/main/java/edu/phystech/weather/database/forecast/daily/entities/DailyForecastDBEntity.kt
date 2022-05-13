package edu.phystech.weather.database.forecast.daily.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(
    tableName = "daily_forecast",
    primaryKeys = ["city", "day"]
)
data class DailyForecastDBEntity(
    @ColumnInfo(name = "city") val city: String,
    @ColumnInfo(name = "day") val day: String,
    @ColumnInfo(name = "avg") val avg: String,
    @ColumnInfo(name = "humidity") val humidity: Int,
    @ColumnInfo(name = "temp_max") val tempMax: Int,
    @ColumnInfo(name = "temp_min") val tempMin: Int,

)