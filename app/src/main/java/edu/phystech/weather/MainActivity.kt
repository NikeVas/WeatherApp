package edu.phystech.weather

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import edu.phystech.weather.databinding.ActivityMainBinding
import edu.phystech.weather.descriptors.DailyDataDescriptor
import edu.phystech.weather.descriptors.HourlyDataDescriptor
import edu.phystech.weather.descriptors.entities.DailyData
import edu.phystech.weather.fragments.CityWeatherFragment


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val app = application as? App
        var aboba = HourlyDataDescriptor(app!!.weatherAPI, app.hourlyDatabase)

        aboba.data("Moscow") { data ->
            Log.e("aboba", data.hours[0].dt.toString())
        }


        supportFragmentManager
            .beginTransaction()
            .add(R.id.fragment_container, CityWeatherFragment())
            .commit()



//        val superPuperApp = application as? WeatherApp

//        val scope = CoroutineScope(Dispatchers.IO)
//        scope.launch {
//            val json = superPuperApp?.weatherAPI?.getCurrentWeather(
//                "Moscow",
//                "c655b94b8f4b74939315e693439f72b2"
//            )
//            val current_temprature = json?.main?.temp?.minus(273.15)
//
//            if (current_temprature != null) {
//                val db = superPuperApp.temratureDB.userDao()
//
//                db.delete(User("London", current_temprature))
//                db.insertAll(User("London", current_temprature))
//
//                val temprature = db.getAll().get(0).temrature?.toInt()
//
//                runOnUiThread {
//                }
//            }
//        }
    }
}