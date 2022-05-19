package edu.phystech.weather

import com.squareup.moshi.Json

data class CurrentWeatherCity (
    @field:Json(name = "coord") val coord: Coord?,
    @field:Json(name = "weather") val weather: List<Weather>?,
    @field:Json(name = "base") val base: String?,
    @field:Json(name = "main") val main: Main?,
    @field:Json(name = "visibility") val visibility: Long?,
    @field:Json(name = "wind") val wind: Wind?,
    @field:Json(name = "clouds") val clouds: Wind?,
    @field:Json(name = "dt") val dt: Long?,
    @field:Json(name = "sys") val sys: Sys?,
    @field:Json(name = "id") val id: String?,
    @field:Json(name = "name") val name: String?,
    @field:Json(name = "cod") val cod: Long?,
)

data class Coord(
    @field:Json(name = "lan") val lan: Float?,
    @field:Json(name = "lat") val lat: Float?,
)

data class Weather(
    @field:Json(name = "id") val id: String?,
    @field:Json(name = "main") val main: String?,
    @field:Json(name = "description") val description: String?,
    @field:Json(name = "icon") val icon: String?,
)

data class Main(
    @field:Json(name = "temp") val temp: Float?,
    @field:Json(name = "feels_like") val feels_like: Float?,
    @field:Json(name = "pressure") val pressure: Float?,
    @field:Json(name = "humidity") val humidity: Long?,
    @field:Json(name = "temp_min") val temp_min: Float?,
    @field:Json(name = "temp_max") val temp_max: Float?,
)

data class Wind(
    @field:Json(name = "speed") val speed: Float?,
    @field:Json(name = "deg") val deg: Long?,
)

data class Clouds(
    @field:Json(name = "all") val all: Float?,
)

data class Sys(
    @field:Json(name = "type") val type: Float?,
    @field:Json(name = "id") val id: Float?,
    @field:Json(name = "message") val message: Float?,
    @field:Json(name = "country") val country: String?,
    @field:Json(name = "sunrise") val sunrise: Long?,
    @field:Json(name = "sunset") val sunset: Long?,
)


