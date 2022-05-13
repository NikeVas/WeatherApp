package edu.phystech.weather.weatherapi

import edu.phystech.weather.CurrentWeatherCity
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherAPI {
    @GET("weather")
    suspend fun getCurrentWeather(
        @Query("q") city: String,
        @Query("appid") appid : String
    ): CurrentWeatherCity
}