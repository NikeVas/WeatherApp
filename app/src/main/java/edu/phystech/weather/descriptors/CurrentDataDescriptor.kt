package edu.phystech.weather.descriptors

import android.location.Geocoder
import androidx.room.Update
import edu.phystech.weather.App
import edu.phystech.weather.CurrentWeatherCity
import edu.phystech.weather.descriptors.entities.CurrentData
import edu.phystech.weather.descriptors.entities.DailyData
import edu.phystech.weather.descriptors.entities.Day
import edu.phystech.weather.weatherapi.OneCallData
import edu.phystech.weather.weatherapi.WeatherAPI
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CurrentDataDescriptor(
    private val weatherAPI: WeatherAPI,
    private val geocoder: Geocoder
) {

    private var city_data = hashMapOf<String, CurrentData?>()

    private fun getCoordByCity(city: String): Pair<Float, Float> {
        val res = geocoder.getFromLocationName(city, 1).get(0)

        val lat = res.latitude
        val long = res.longitude// lat lon
        return Pair<Float, Float>(lat.toFloat(), long.toFloat())
    }

    private suspend fun tryRequestData(city : String) : OneCallData? {
        val coord = getCoordByCity(city)
        return try {
            val response = weatherAPI.oneCallApi(coord.first, coord.second, App.WEATHER_TOKEN)
            if (response.code() != 200) {
                null
            } else {
                response.body()
            }
        } catch (ex: Exception) {
            null
        }
    }

    private fun convertServerResponseToCurrentData(response: OneCallData) : CurrentData? {
        val current = response.current!!
        return CurrentData(
            response.timezone!!,
            current.sunrise!!,
            current.sunset!!,

            current.temp!!,
            response.daily!![0].temp!!.min!!,
            response.daily[0].temp!!.max!!,


            current.feels_like!!,
            current.humidity!!,

            current.wind_speed!!,
            current.wind_deg!!,
            current.uvi!!,
            current.weather!![0].icon!!
        )
    }

    fun data(city : String, callback : CurrentDataSetterCallback) {
        val scope = CoroutineScope(Dispatchers.IO)
        scope.launch {
            val data : CurrentData?
            val response = tryRequestData(city)
            if (response != null) {
                data = convertServerResponseToCurrentData(response)
            } else {
                data = null
            }
            city_data[city] = data
            callback(data)
        }
    }

    fun deprecateAll() {
        city_data.clear()
    }
}

typealias CurrentDataSetterCallback = (data: CurrentData?) -> Unit