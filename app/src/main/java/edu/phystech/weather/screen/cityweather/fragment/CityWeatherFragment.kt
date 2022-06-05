package edu.phystech.weather.screen.cityweather.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import edu.phystech.weather.App
import edu.phystech.weather.R
import edu.phystech.weather.screen.cityweather.adapter.CurrentWeatherAdapter
import edu.phystech.weather.screen.cityweather.adapter.DailyForecastRecyclerViewAdapter
import edu.phystech.weather.screen.cityweather.adapter.WeekForecastAdapter
import edu.phystech.weather.databinding.FragmentCityWeatherBinding
import edu.phystech.weather.screen.cityweather.viewmodels.CurrentWeatherViewModel
import edu.phystech.weather.screen.cityweather.viewmodels.DailyWeatherViewModel
import edu.phystech.weather.screen.cityweather.viewmodels.HourlyWeatherViewModel

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
        initCityViews()
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
        weekAdapter = WeekForecastAdapter(binding, layoutInflater)
        dailyViewModel.data.observe(viewLifecycleOwner, Observer {
            weekAdapter.data = it
        })
    }

    private fun initCityViews() {
        binding.cityName.text = city
        binding.toolCity.text = city
    }

    private fun initCurrentWeather() {
        currentWeatherAdapter = CurrentWeatherAdapter(binding, requireContext())
        currentWeatherViewModel.data.observe(viewLifecycleOwner, Observer {
            currentWeatherAdapter.data = it
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