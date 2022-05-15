package edu.phystech.weather.weatherapi

import com.squareup.moshi.Json

data class OneCallData(
    @field:Json(name = "lan") val lan: Float?,
    @field:Json(name = "lat") val lat: Float?,
    @field:Json(name = "timezone") val timezone: String?,
    @field:Json(name = "timezone_offset") val timezone_offset: Long?,
    @field:Json(name = "current") val current: Current?,
    @field:Json(name = "hourly") val hourly: List<Hourly>?,
    @field:Json(name = "daily") val daily: List<Daily>?,
)

data class Current(
    @field:Json(name = "dt") val dt: Long?,
    @field:Json(name = "sunrise") val sunrise: Long?,
    @field:Json(name = "sunset") val sunset: Long?,
    @field:Json(name = "temp") val temp: Float?,
    @field:Json(name = "feels_like") val feels_like: Float?,
    @field:Json(name = "pressure") val pressure: Long?,
    @field:Json(name = "humidity") val humidity: Long?,
    @field:Json(name = "dew_point") val dew_point: Float?,
    @field:Json(name = "uvi") val uvi: Float?,
    @field:Json(name = "clouds") val clouds: Long?,
    @field:Json(name = "visibility") val visibility: Long?,
    @field:Json(name = "wind_speed") val wind_speed: Float?,
    @field:Json(name = "wind_deg") val wind_deg: Long?,
    @field:Json(name = "weather") val weather: List<Weather>?,
)

data class Hourly(
    @field:Json(name = "dt") val dt: Long?,
    @field:Json(name = "temp") val temp: Float?,
    @field:Json(name = "feels_like") val feels_like: Float?,
    @field:Json(name = "pressure") val pressure: Long?,
    @field:Json(name = "humidity") val humidity: Long?,
    @field:Json(name = "dew_point") val dew_point: Float?,
    @field:Json(name = "uvi") val uvi: Float?,
    @field:Json(name = "clouds") val clouds: Long?,
    @field:Json(name = "visibility") val visibility: Long?,
    @field:Json(name = "wind_speed") val wind_speed: Float?,
    @field:Json(name = "wind_deg") val wind_deg: Long?,
    @field:Json(name = "pop") val pop: Float?,
    @field:Json(name = "weather") val weather: List<Weather>?,
)

data class Daily(
    @field:Json(name = "dt") val dt: Long?,
    @field:Json(name = "sunrise") val sunrise: Long?,
    @field:Json(name = "sunset") val sunset: Long?,
    @field:Json(name = "moonrise") val moonrise: Long?,
    @field:Json(name = "moonset") val moonset: Long?,
    @field:Json(name = "moon_phase") val moon_phase: Float?,
    @field:Json(name = "temp") val temp: Temp?,
    @field:Json(name = "feels_like") val feels_like: FeelsLike?,
    @field:Json(name = "pressure") val pressure: Long?,
    @field:Json(name = "humidity") val humidity: Long?,
    @field:Json(name = "dew_point") val dew_point: Float?,
    @field:Json(name = "wind_speed") val wind_speed: Float?,
    @field:Json(name = "wind_deg") val wind_deg: Long?,
    @field:Json(name = "uvi") val uvi: Float?,
    @field:Json(name = "clouds") val clouds: Long?,
    @field:Json(name = "pop") val pop: Float?,
    @field:Json(name = "weather") val weather: List<Weather>?,
)

data class Weather(
    @field:Json(name = "id") val id: Long?,
    @field:Json(name = "main") val main: String?,
    @field:Json(name = "description") val description: String?,
    @field:Json(name = "icon") val icon: String?,
)


data class Temp(
    @field:Json(name = "morn") val morn: Float?,
    @field:Json(name = "day") val day: Float?,
    @field:Json(name = "eve") val eve: Float?,
    @field:Json(name = "night") val night: Float?,
    @field:Json(name = "min") val min: Float?,
    @field:Json(name = "max") val max: Float?,
)

data class FeelsLike(
    @field:Json(name = "morn") val morn: Float?,
    @field:Json(name = "day") val day: Float?,
    @field:Json(name = "eve") val eve: Float?,
    @field:Json(name = "night") val night: Float?,
)

data class Alerts(
    @field:Json(name = "sender_name") val sender_name: String?,
    @field:Json(name = "event") val event: String?,
    @field:Json(name = "start") val start: Long?,
    @field:Json(name = "end") val end: Long?,
    @field:Json(name = "description") val morn: String?,
    @field:Json(name = "tags") val day: List<String>?,
)