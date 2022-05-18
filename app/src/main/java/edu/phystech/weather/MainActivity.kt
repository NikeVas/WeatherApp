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
import edu.phystech.weather.utils.currentCity
import edu.phystech.weather.utils.currentTime
import edu.phystech.weather.utils.unixToDate
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

        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)
        askLocationPermission()

    }

    private fun changeToDark() {
        setTheme(R.style.Theme_Dark)
    }

    private fun askLocationPermission() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
            == PackageManager.PERMISSION_GRANTED) {
            fusedLocationClient.lastLocation.addOnSuccessListener {
                val geocoder = Geocoder(this, Locale.getDefault())
                Log.e("mainactivity", currentCity(geocoder, it.latitude, it.longitude))
                startFragment(currentCity(geocoder, it.latitude, it.longitude))
                //startFragment("Алматы")
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
                    val geocoder = Geocoder(this, Locale.getDefault())
                    startFragment(currentCity(geocoder, it.latitude, it.longitude))
                }
            }
        } else {
            // TODO
        }
    }

    private fun startFragment(city: String) {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragment_container, CityWeatherFragment.newInstance(city))
            .commit()
    }
}