package edu.phystech.weather

import android.Manifest
import android.content.pm.PackageManager
import android.location.Geocoder
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import edu.phystech.weather.databinding.ActivityMainBinding
import edu.phystech.weather.descriptors.DailyDataDescriptor
import edu.phystech.weather.descriptors.HourlyDataDescriptor
import edu.phystech.weather.descriptors.entities.DailyData
import edu.phystech.weather.fragments.CityWeatherFragment
import java.util.*


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var fusedLocationClient: FusedLocationProviderClient

    private val requestLocationPermissionLauncher = registerForActivityResult(
        ActivityResultContracts.RequestPermission(),
        ::onGotLocationPermissionResult
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val app = application as? App
        var aboba = HourlyDataDescriptor(app!!.weatherAPI, app.hourlyDatabase)

        aboba.data("Moscow") { data ->
            Log.e("aboba", data.hours[0].dt.toString())
        }

        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)
        askLocationPermission()

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

    private fun askLocationPermission() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
            == PackageManager.PERMISSION_GRANTED) {
            val geocoder = Geocoder(this, Locale.getDefault())
            fusedLocationClient.lastLocation.addOnSuccessListener {

                val loc = geocoder.getFromLocation(it.latitude, it.longitude, 1);
                Toast.makeText(this, loc.get(0).locality.toString(), Toast.LENGTH_SHORT).show()
            }
        } else {
            requestLocationPermissionLauncher.launch(Manifest.permission.ACCESS_FINE_LOCATION)
        }
    }

    private fun onGotLocationPermissionResult(granted: Boolean) {
        if (granted) {
            fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
                fusedLocationClient.lastLocation.addOnSuccessListener {
                    val loc = it.latitude
                    Toast.makeText(this, loc.toString(), Toast.LENGTH_SHORT).show()
                }
            }
            // TODO start fragment
        } else {
            // TODO
        }
    }
}