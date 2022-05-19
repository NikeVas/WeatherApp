package edu.phystech.weather.utils

fun toTempMinMaxFormat(temp_max: Float, temp_min: Float) : String {
    return kelvinToCelsius(temp_max).toString() + "\u00B0/" + kelvinToCelsius(temp_min).toString() + "\u00B0"
}