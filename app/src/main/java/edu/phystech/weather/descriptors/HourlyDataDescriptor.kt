package edu.phystech.weather.descriptors

import android.location.Geocoder
import android.util.Log
import edu.phystech.weather.App
import edu.phystech.weather.database.forecast.hourly.HourlyForecastDB
import edu.phystech.weather.database.forecast.hourly.entity.HourlyForecastDBEntity
import edu.phystech.weather.descriptors.entities.Hour
import edu.phystech.weather.descriptors.entities.HourlyData
import edu.phystech.weather.weatherapi.OneCallData
import edu.phystech.weather.weatherapi.WeatherAPI
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock

class HourlyDataDescriptor(
    private val weatherAPI: WeatherAPI,
    private val database: HourlyForecastDB,
    private val geocoder: Geocoder
) {
    private var cityData = hashMapOf<String, HourlyData>()
    private val mutex = Mutex()

    private fun getCoordByCity(city: String): Pair<Float, Float> {
        val res = geocoder.getFromLocationName(city, 1).get(0)

        val lat = res.latitude
        val long = res.longitude// lat lon
        return Pair<Float, Float>(lat.toFloat(), long.toFloat())
    }

    private suspend fun updateDB(city: String, response: OneCallData) {
        val timezoneOffset = response.timezone_offset!!

        Log.d("aboba", "Try delete data")
        database.hourlyForecastDao().deleteCity(city)
        Log.d("aboba", "Deleted data")


        for (hour in response.hourly!!) {
            database.hourlyForecastDao().insert(
                HourlyForecastDBEntity(
                    city,
                    hour.dt!!,
                    response.timezone_offset!!,
                    hour.temp!!,
                    hour.feels_like!!,
                    hour.humidity!!,
                    hour.wind_speed!!,
                    hour.wind_deg!!,
                    hour.uvi!!,
                    hour.weather!![0].icon!!
                )
            )
        }
    }


    private suspend fun tryRequestData(city: String): OneCallData? {
        return try {
            val coord = getCoordByCity(city)
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

    private suspend fun loadFromDB(city: String): List<HourlyForecastDBEntity> {
        return database.hourlyForecastDao().getCityWeather(city)
    }

    private fun convertDBResponseToDailyData(db_data: List<HourlyForecastDBEntity>): HourlyData {
        val hours = mutableListOf<Hour>()
        for (hour in db_data) {
            hours.add(
                Hour(
                    hour.dt,
                    hour.timezone_offset,
                    hour.temp,
                    hour.feels_like,
                    hour.humidity,
                    hour.wind_speed,
                    hour.wind_deg,
                    hour.uvi,
                    hour.icon
                )
            )
        }
        return HourlyData(hours)
    }

    private fun convertServerResponseToDailyData(response: OneCallData): HourlyData {
        val hours = mutableListOf<Hour>()
        for (hour in response.hourly!!) {
            hours.add(
                Hour(
                    hour.dt!!,
                    response.timezone_offset!!,
                    hour.temp!!,
                    hour.feels_like!!,
                    hour.humidity!!,
                    hour.wind_speed!!,
                    hour.wind_deg!!,
                    hour.uvi!!,
                    hour.weather!![0].icon!!
                )
            )
        }
        return HourlyData(hours)
    }


    fun data(city: String, callback: HourlyDataSetterCallback) {
        val scope = CoroutineScope(Dispatchers.IO)
        scope.launch {
            mutex.withLock {
                val data: HourlyData
                if (cityData.containsKey(city)) {
                    data = cityData[city]!!
                } else {
                    val response = tryRequestData(city)
                    if (response == null) {
                        data = convertDBResponseToDailyData(loadFromDB(city))
                    } else {
                        updateDB(city, response)
                        data = convertServerResponseToDailyData(response)
                    }
                }
                cityData[city] = data
                callback(data)
            }
        }
    }

    fun deprecateAll() {
        cityData.clear()
    }
}

typealias HourlyDataSetterCallback = (data: HourlyData) -> Unit