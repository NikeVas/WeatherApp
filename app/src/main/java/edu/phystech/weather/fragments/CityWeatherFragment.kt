package edu.phystech.weather.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import edu.phystech.weather.App
import edu.phystech.weather.adapter.CurrentWeatherAdapter
import edu.phystech.weather.adapter.DailyForecastRecyclerViewAdapter
import edu.phystech.weather.adapter.WeekForecastAdapter
import edu.phystech.weather.databinding.FragmentCityWeatherBinding
import edu.phystech.weather.utils.*
import java.time.LocalDateTime

class CityWeatherFragment : Fragment() {
    private lateinit var binding : FragmentCityWeatherBinding

    private lateinit var dailyRecyclerView: RecyclerView
    private var dayAdapter: DailyForecastRecyclerViewAdapter = DailyForecastRecyclerViewAdapter()

    private lateinit var weekLinearLayout: LinearLayout
    private lateinit var weekAdapter: WeekForecastAdapter

    private lateinit var hourViewModel : HourlyWeatherViewModel
    private lateinit var dailyViewModel : DailyWeatherViewModel

    private lateinit var currentWeatherAdapter: CurrentWeatherAdapter
    private lateinit var currentWeatherViewModel: CurrentWeatherViewModel

    private lateinit var city: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        city =
            if (arguments != null) {
                requireArguments().getString(CITY_KEY)!!
            } else {
                DEFAULT_CITY
            }
        hourViewModel = HourlyWeatherViewModel((requireActivity().applicationContext as App).hourlyDataDescriptor, city, requireActivity())
        dailyViewModel = DailyWeatherViewModel((requireActivity().applicationContext as App).dailyDataDescriptor, city, requireActivity())
        currentWeatherViewModel = CurrentWeatherViewModel((requireActivity().applicationContext as App).currentDataDescriptor, city, requireActivity())
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentCityWeatherBinding.inflate(inflater, container, false)

        initHourlyForecastRecyclerView()
        initWeekForecastLinerLayout()
        initCurrentWeather()

        Toast.makeText(requireContext(), currentDay(), Toast.LENGTH_SHORT).show()

        return binding.root
    }

    private fun initHourlyForecastRecyclerView() {
        dailyRecyclerView = binding.nestedScroll.dayForecast!!
        weekLinearLayout = binding.nestedScroll.weekForecast!!

        hourViewModel.data.observe(viewLifecycleOwner, Observer {
            dayAdapter.data = it
        })

        val layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        dailyRecyclerView.layoutManager = layoutManager
        dailyRecyclerView.adapter = dayAdapter
        dailyRecyclerView.setNestedScrollingEnabled(false)

    }

    private fun initWeekForecastLinerLayout() {
        val layoutInflater = LayoutInflater.from(requireContext())
        weekAdapter = WeekForecastAdapter(weekLinearLayout, layoutInflater)
        dailyViewModel.data.observe(viewLifecycleOwner, Observer {
            weekAdapter.data = it
            initSunriseSunsetView()
        })
    }

    private fun initSunriseSunsetView() {
        for (day in weekAdapter.data) {
            if (dateToDay(unixToDate(day.dt)) == currentDay()) {
                binding.nestedScroll.sunsetSunrise.sunriseTime.text = unixToHours(day.sunrise)
                binding.nestedScroll.sunsetSunrise.sunsetTime.text = unixToHours(day.sunset)
                binding.nestedScroll.uvWindHumidity.uvIndex.text = day.uvi.toString()
                binding.tempMinMaxT.text = kelvinToCelsius(day.temp_max).toString() + "\u00B0/" + kelvinToCelsius(day.temp_min).toString() + "\u00B0"
                binding.cityName.text = city
                binding.currentDayTime.text = abbreviateDay(currentDayOfWeek()).lowercase() + ", " //TODO (time)
            }
        }
    }

    private fun initCurrentWeather() {
        currentWeatherAdapter = CurrentWeatherAdapter(binding.nestedScroll.uvWindHumidity)
        currentWeatherViewModel.data.observe(viewLifecycleOwner, Observer {
            currentWeatherAdapter.data = it
            binding.currentTemperature.text = kelvinToCelsius(it.temp).toString() + "\u00B0"
            binding.feelsLike.text = "Ощущается как " + kelvinToCelsius(it.feels_like).toString() + "\u00B0"
        })

    }

    override fun onDestroyView() {
        super.onDestroyView()

    }

    companion object {
        fun newInstance(city: String) : CityWeatherFragment {
            val bundle = Bundle()
            bundle.putString(CITY_KEY, city)
            val fragment = CityWeatherFragment()
            fragment.arguments = bundle
            return fragment
        }

        const val CITY_KEY = "CITY_KEY"
        const val DEFAULT_CITY = "Moscow"
    }
}