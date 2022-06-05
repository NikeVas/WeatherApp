package edu.phystech.weather.utils

import android.location.Geocoder

fun currentCity(geocoder: Geocoder, lat: Double, long: Double) : String {
    val res = geocoder.getFromLocation(lat, long, 1)
    if (res.isEmpty()) {
        return "Moscow"
    }
    return res.get(0).locality.toString()
}