package edu.phystech.weather.weatherapi

import android.app.Application
import android.util.Log
import androidx.room.Room
import edu.phystech.weather.AppDatabase
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class WeatherApp : Application() {

    lateinit var weatherAPI: WeatherAPI
    lateinit var temratureDB: AppDatabase

    override fun onCreate() {
        super.onCreate()

        configureRetrofit()
        createDB()
    }

    private fun configureRetrofit() {
        val httpLoggingInterceptor = HttpLoggingInterceptor();
        httpLoggingInterceptor.level = okhttp3.logging.HttpLoggingInterceptor.Level.BODY;

        val okHttpClient = OkHttpClient.Builder()
            .addInterceptor(httpLoggingInterceptor)
            .build()

        val retrofit = Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl("https://api.openweathermap.org/data/2.5/")
            .addConverterFactory(MoshiConverterFactory.create())
            .build()

        weatherAPI = retrofit.create(WeatherAPI::class.java)
    }

    private fun createDB() {
        temratureDB =
            Room.databaseBuilder(
                applicationContext,
                AppDatabase::class.java, "database-name"
            ).build()
    }
}