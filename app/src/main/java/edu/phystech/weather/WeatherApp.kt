package edu.phystech.weather

import android.app.Application
import android.util.Log
import androidx.room.Room
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class WeatherApp : Application() {

    lateinit var weatherAPI: WeatherAPI
    lateinit var temratureDB: AppDatabase

    override fun onCreate() {
        Log.e("Aboba", "SuperPuperApp Create")
        super.onCreate()

        configureRetrofit()
        createDB()
    }

    private fun configureRetrofit() {
        val HttpLoggingInterceptor = HttpLoggingInterceptor();
        HttpLoggingInterceptor.level = okhttp3.logging.HttpLoggingInterceptor.Level.BODY;

        val okHttpClien = OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor)
            .build()

        val retrofit = Retrofit.Builder()
            .client(okHttpClien)
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