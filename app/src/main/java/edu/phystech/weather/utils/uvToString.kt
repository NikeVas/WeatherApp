package edu.phystech.weather.utils

fun uvToString(uv: Float) : String {
    if (uv < 2.5F) {
        return "Низкий"
    } else if (uv < 7.5F) {
        return "Средний"
    } else {
        return "Высокий"
    }
}