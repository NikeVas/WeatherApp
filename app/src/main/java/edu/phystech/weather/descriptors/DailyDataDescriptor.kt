package edu.phystech.weather.descriptors

import android.location.Geocoder
import edu.phystech.weather.App
import edu.phystech.weather.database.forecast.daily.DailyForecastDB
import edu.phystech.weather.database.forecast.daily.entities.DailyForecastDBEntity
import edu.phystech.weather.descriptors.entities.DailyData
import edu.phystech.weather.descriptors.entities.Day
import edu.phystech.weather.weatherapi.OneCallData
import edu.phystech.weather.weatherapi.WeatherAPI
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock

class DailyDataDescriptor(
    private val weatherAPI: WeatherAPI,
    private val database: DailyForecastDB,
    private val geocoder: Geocoder
) {


    private var city_data = hashMapOf<String, DailyData>()
    private var mutex = Mutex()


    private fun getCoordByCity(city : String): Pair<Float, Float> {  // lat lon
        val res = geocoder.getFromLocationName(city, 1).get(0)

        val lat = res.latitude
        val long = res.longitude// lat lon
        return Pair<Float, Float>(lat.toFloat(), long.toFloat())
    }

    private suspend fun updateDB(city : String, response : OneCallData) {
        val timezoneOffset = response.timezone_offset!!

        database.dailyForecastDao().deleteCity(city)

        for (day in response.daily!!) {
            database.dailyForecastDao().insert(
                DailyForecastDBEntity(
                    city,
                    day.dt!! ,
                    response.timezone_offset!!,
                    day.sunrise!!,
                    day.sunset!!,
                    day.temp!!.morn!!,
                    day.temp.day!!,
                    day.temp.eve!!,
                    day.temp.night!!,
                    day.temp.min!!,
                    day.temp.max!!,
                    day.feels_like!!.morn!!,
                    day.feels_like.day!!,
                    day.feels_like.eve!!,
                    day.feels_like.night!!,
                    day.humidity!!,
                    day.wind_speed!!,
                    day.wind_deg!!,
                    day.uvi!!,
                    day.weather!![0].icon!!
                )
            )
        }
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

    private suspend fun loadFromDB(city: String): List<DailyForecastDBEntity> {
        return database.dailyForecastDao().getCityWeather(city)
    }

    private fun convertDBResponseToDailyData(db_data : List<DailyForecastDBEntity>) : DailyData {
        var days = mutableListOf<Day>()
        for (day in db_data) {
            days.add(Day(
                day.dt,
                day.timezone_offset,
                day.sunrise,
                day.sunset,
                day.temp_morn,
                day.temp_day,
                day.temp_eve,
                day.temp_night,
                day.temp_min,
                day.temp_max,
                day.feels_like_morn,
                day.feels_like_day,
                day.feels_like_eve,
                day.feels_like_night,
                day.humidity,
                day.wind_speed,
                day.wind_deg,
                day.uvi,
                day.icon
            ))
        }
        return DailyData(days)
    }

    private fun convertServerResponseToDailyData(response: OneCallData) : DailyData {
        val timezoneOffset = response.timezone_offset!!

        val days = mutableListOf<Day>()

        for (day in response.daily!!) {
            days.add(
                Day(
                    day.dt!!,
                    response.timezone_offset!!,
                    day.sunrise!!,
                    day.sunset!!,
                    day.temp!!.morn!!,
                    day.temp.day!!,
                    day.temp.eve!!,
                    day.temp.night!!,
                    day.temp.min!!,
                    day.temp.max!!,
                    day.feels_like!!.morn!!,
                    day.feels_like.day!!,
                    day.feels_like.eve!!,
                    day.feels_like.night!!,
                    day.humidity!!,
                    day.wind_speed!!,
                    day.wind_deg!!,
                    day.uvi!!,
                    day.weather!![0].icon!!
                )
            )
        }

        return DailyData(days)
    }


    fun data(city : String, callback : DailyDataSetterCallback ) {
        val scope = CoroutineScope(Dispatchers.IO)
        scope.launch {
            mutex.withLock {
                val data : DailyData
                if (city_data.containsKey(city)) {
                    data = city_data[city]!!
                } else {
                    val response = tryRequestData(city)
                    if (response == null) {
                        data = convertDBResponseToDailyData(loadFromDB(city))
                    } else {
                        updateDB(city, response)
                        data = convertServerResponseToDailyData(response)
                    }
                }
                city_data[city] = data
                callback(data)
            }
        }
    }

    fun deprecateAll() {
        city_data.clear()
    }
}

typealias DailyDataSetterCallback = (data: DailyData) -> Unit