package edu.phystech.weather.utils

fun kelvinToCelsius(temp: Float) : Int {
    return (temp - 273.15F).toInt()
}