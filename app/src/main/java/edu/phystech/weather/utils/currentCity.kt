package edu.phystech.weather.utils

import android.location.Geocoder

fun currentCity(geocoder: Geocoder, lat: Double, long: Double) : String {
    return geocoder.getFromLocation(lat, long, 1).get(0).locality.toString()
}