package edu.phystech.weather

import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch


class ScrollingActivity : AppCompatActivity() {

    lateinit var current_temprature_view: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_scrolling);

        current_temprature_view = findViewById(R.id.temrature)

        val superPuperApp = application as? WeatherApp

        val scope = CoroutineScope(Dispatchers.IO)
        scope.launch {
            val json = superPuperApp?.weatherAPI?.getCurrentWeather(
                "Moscow",
                "c655b94b8f4b74939315e693439f72b2"
            )
            val current_temprature = json?.main?.temp?.minus(273.15)

            if (current_temprature != null) {
                val db = superPuperApp.temratureDB.userDao()

                db.delete(User("London", current_temprature))
                db.insertAll(User("London", current_temprature))

                val temprature = db.getAll().get(0).temrature?.toInt()

                runOnUiThread {
                    current_temprature_view.text = temprature.toString() + "\u00B0"
                }
            }
        }
    }
}