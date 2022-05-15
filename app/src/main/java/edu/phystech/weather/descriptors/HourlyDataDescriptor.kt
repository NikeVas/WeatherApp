package edu.phystech.weather.descriptors

import edu.phystech.weather.App
import edu.phystech.weather.database.forecast.hourly.HourlyForecastDB
import edu.phystech.weather.database.forecast.hourly.entity.HourlyForecastDBEntity
import edu.phystech.weather.descriptors.entities.DailyData
import edu.phystech.weather.descriptors.entities.Day
import edu.phystech.weather.descriptors.entities.Hour
import edu.phystech.weather.descriptors.entities.HourlyData
import edu.phystech.weather.weatherapi.OneCallData
import edu.phystech.weather.weatherapi.WeatherAPI
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HourlyDataDescriptor(
    private val weatherAPI: WeatherAPI,
    private val database: HourlyForecastDB
) {
    private var city_data = hashMapOf<String, HourlyData>()

    private fun getCityByCoord(lat: Float, lan: Float): String {
        return "Moscow"
    }

    private fun getCoordByCity(city: String): Pair<Float, Float> {  // lat lon
        return Pair<Float, Float>(55.9041F, 55.5606F)
    }

    private suspend fun updateDB(city: String, response: OneCallData) {
        val timezoneOffset = response.timezone_offset!!

        database.hourlyForecastDao().deleteCity(city)

        for (hour in response.hourly!!) {
            database.hourlyForecastDao().insert(
                HourlyForecastDBEntity(
                    city,
                    hour.dt!! + timezoneOffset,
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

    private suspend fun LoadFromDB(city: String): List<HourlyForecastDBEntity> {
        return database.hourlyForecastDao().getCityWeather(city)
    }

    private fun convertDBResponseToDailyData(db_data: List<HourlyForecastDBEntity>): HourlyData {
        val hours = mutableListOf<Hour>()
        for (hour in db_data) {
            hours.add(
                Hour(
                    hour.dt,
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
            val data: HourlyData
            if (city_data.containsKey(city)) {
                data = city_data[city]!!
            } else {
                val response = tryRequestData(city)
                if (response == null) {
                    data = convertDBResponseToDailyData(LoadFromDB(city))
                } else {
                    updateDB(city, response)
                    data = convertServerResponseToDailyData(response)
                }
            }
            city_data[city] = data
            callback(data)
        }
    }

    fun deprecateAll() {
        city_data.clear()
    }
}

typealias HourlyDataSetterCallback = (data: HourlyData) -> Unit