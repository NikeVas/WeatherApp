package edu.phystech.weather

import android.app.Application
import android.location.Geocoder
import androidx.room.Room
import edu.phystech.weather.database.forecast.daily.DailyForecastDB
import edu.phystech.weather.database.forecast.hourly.HourlyForecastDB
import edu.phystech.weather.descriptors.CurrentDataDescriptor
import edu.phystech.weather.descriptors.DailyDataDescriptor
import edu.phystech.weather.descriptors.HourlyDataDescriptor
import edu.phystech.weather.weatherapi.WeatherAPI
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.*

class App : Application() {
    val weatherAPI: WeatherAPI = configureRetrofit()

    lateinit var dailyDatabase: DailyForecastDB
    lateinit var hourlyDatabase: HourlyForecastDB
    lateinit var geocoder  : Geocoder

    override fun onCreate() {
        super.onCreate()
        dailyDatabase = Room.databaseBuilder(
            this,
            DailyForecastDB::class.java, "weather.daily"
        ).build()

        hourlyDatabase = Room.databaseBuilder(
            this,
            HourlyForecastDB::class.java, "weather.hourly"
        ).build()

        geocoder = Geocoder(this, Locale.getDefault())

        hourlyDataDescriptor = HourlyDataDescriptor(weatherAPI, hourlyDatabase, geocoder)
        dailyDataDescriptor = DailyDataDescriptor(weatherAPI, dailyDatabase, geocoder)
        currentDataDescriptor = CurrentDataDescriptor(weatherAPI, geocoder)
    }

    


    lateinit var hourlyDataDescriptor: HourlyDataDescriptor
    lateinit var dailyDataDescriptor: DailyDataDescriptor
    lateinit var currentDataDescriptor: CurrentDataDescriptor


    private fun configureRetrofit() : WeatherAPI {
        val httpLoggingInterceptor = HttpLoggingInterceptor();
        httpLoggingInterceptor.level = okhttp3.logging.HttpLoggingInterceptor.Level.BODY;

        val okHttpClient = OkHttpClient.Builder()
            .addInterceptor(httpLoggingInterceptor)
            .build()

        val retrofit = Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl(BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()

        return retrofit.create(WeatherAPI::class.java)
    }

    fun setStartStyles() {
    }

    fun setFinalStyles() {

    }

    companion object {
        const val BASE_URL = "https://api.openweathermap.org/data/2.5/"
        const val WEATHER_TOKEN = "c655b94b8f4b74939315e693439f72b2"
    }
}