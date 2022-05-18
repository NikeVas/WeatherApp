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
import edu.phystech.weather.MainActivity
import edu.phystech.weather.R
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
        setHasOptionsMenu(true)
        binding = FragmentCityWeatherBinding.inflate(inflater, container, false)

        hide()
        initHourlyForecastRecyclerView()
        initWeekForecastLinerLayout()
        initCurrentWeather()

        return binding.root
    }

    private fun hide() {
        binding.nestedScroll.root.visibility = View.GONE
        binding.progressBar.visibility = View.VISIBLE
        binding.appBar.visibility = View.GONE
        binding.cityName.visibility = View.GONE
        binding.toolbar.visibility = View.GONE
    }

    private fun showFragment() {
        binding.nestedScroll.root.visibility = View.VISIBLE
        binding.progressBar.visibility = View.GONE
        binding.appBar.visibility = View.VISIBLE
        binding.cityName.visibility = View.VISIBLE
        binding.toolbar.visibility = View.VISIBLE
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.toolbar.inflateMenu(R.menu.menu_scrolling)
    }

    private fun initHourlyForecastRecyclerView() {
        dailyRecyclerView = binding.nestedScroll.dayForecast!!
        weekLinearLayout = binding.nestedScroll.weekForecast!!

        hourViewModel.data.observe(viewLifecycleOwner, Observer {
            dayAdapter.data = it
            showFragment()
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
                binding.tempMinMaxT.text = toTempMinMaxFormat(day.temp_max, day.temp_min)
                binding.cityName.text = city
                binding.currentDay.text = abbreviateDay(currentDayOfWeek()).lowercase() + ", "
            }
        }
    }

    private fun initCurrentWeather() {
        currentWeatherAdapter = CurrentWeatherAdapter(binding.nestedScroll.uvWindHumidity)
        currentWeatherViewModel.data.observe(viewLifecycleOwner, Observer {
            currentWeatherAdapter.data = it
            binding.currentTemperature.text = kelvinToCelsius(it.temp).toString() + "\u00B0"
            binding.feelsLike.text = getString(R.string.feels_like) + " " + kelvinToCelsius(it.feels_like).toString() + "\u00B0"
            binding.currentTime.timeZone = it.timezone
            binding.currentTemperature.setOnClickListener {
                Log.e("clicked", "clicked")
                (requireActivity() as MainActivity).setTheme(R.style.Theme_Dark)
            }
        })

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