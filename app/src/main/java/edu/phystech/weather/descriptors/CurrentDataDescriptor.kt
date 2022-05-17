package edu.phystech.weather.descriptors

import edu.phystech.weather.App
import edu.phystech.weather.CurrentWeatherCity
import edu.phystech.weather.descriptors.entities.CurrentData
import edu.phystech.weather.weatherapi.WeatherAPI
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CurrentDataDescriptor(
    private val weatherAPI: WeatherAPI
) {

    private var cityData = hashMapOf<String, CurrentData?>()

    private suspend fun tryRequestData(city : String) : CurrentWeatherCity? {
        return try {
            val response = weatherAPI.currentWeather(city, App.WEATHER_TOKEN)
            if (response.code() != 200) {
                null
            } else {
                response.body()
            }
        } catch (ex: Exception) {
            null
        }
    }

    private fun convertServerResponseToCurrentData(response: CurrentWeatherCity) : CurrentData? {
        return CurrentData(
            response.sys!!.sunrise!!,
            response.sys.sunset!!,

            response.main!!.temp!!,
            response.main.feels_like!!,
            response.main.humidity!!,

            response.wind!!.speed!!,
            response.wind.deg!!,

            response.weather!![0].icon!!
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
            cityData[city] = data
            callback(data)
        }
    }

    fun deprecateAll() {
        cityData.clear()
    }
}

typealias CurrentDataSetterCallback = (data: CurrentData?) -> Unit