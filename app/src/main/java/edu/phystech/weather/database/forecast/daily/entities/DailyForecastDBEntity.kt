package edu.phystech.weather.database.forecast.daily.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json
import edu.phystech.weather.weatherapi.FeelsLike
import edu.phystech.weather.weatherapi.Temp
import edu.phystech.weather.weatherapi.Weather


@Entity(
    tableName = "daily_forecast",
    primaryKeys = ["city", "dt"]
)
data class DailyForecastDBEntity(
    @ColumnInfo(name = "city") val city: String,
    @ColumnInfo(name = "dt") val dt: Long,
    @ColumnInfo(name = "sunrise") val sunrise: Long,
    @ColumnInfo(name = "sunset") val sunset: Long,

    @ColumnInfo(name = "temp_morn") val temp_morn: Float,
    @ColumnInfo(name = "temp_day") val temp_day: Float,
    @ColumnInfo(name = "temp_eve") val temp_eve: Float,
    @ColumnInfo(name = "temp_night") val temp_night: Float,
    @ColumnInfo(name = "temp_min") val temp_min: Float,
    @ColumnInfo(name = "temp_max") val temp_max: Float,

    @ColumnInfo(name = "feels_like_morn") val feels_like_morn: Float,
    @ColumnInfo(name = "feels_like_day") val feels_like_day: Float,
    @ColumnInfo(name = "feels_like_eve") val feels_like_eve: Float,
    @ColumnInfo(name = "feels_like_night") val feels_like_night: Float,

    @ColumnInfo(name = "humidity") val humidity: Long,
    @ColumnInfo(name = "wind_speed") val wind_speed: Float,
    @ColumnInfo(name = "wind_deg") val wind_deg: Long,
    @ColumnInfo(name = "uvi") val uvi: Float,
    @ColumnInfo(name = "icon") val icon: String,
)
