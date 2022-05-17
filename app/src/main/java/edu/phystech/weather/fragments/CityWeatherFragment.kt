package edu.phystech.weather.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import edu.phystech.weather.App
import edu.phystech.weather.adapter.RecyclerViewAdapter
import edu.phystech.weather.adapter.WeekForecastAdapter
import edu.phystech.weather.databinding.FragmentCityWeatherBinding

class CityWeatherFragment : Fragment() {
    private lateinit var binding : FragmentCityWeatherBinding
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: RecyclerViewAdapter
    private lateinit var linearLayout: LinearLayout

    private lateinit var hourViewModel: HourlyWeatherViewModel
    private lateinit var city: String


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        city =
        if (arguments != null) {
            requireArguments().getString(CITY_KEY)!!
        } else {
            DEFAULT_CITY
        }

        hourViewModel = HourlyWeatherViewModel((requireActivity().applicationContext as App).hourlyDataDescriptor, city, requireActivity())
        binding = FragmentCityWeatherBinding.inflate(inflater, container, false)
        recyclerView = binding.nestedScroll.dayForecast!!
        linearLayout = binding.nestedScroll.weekForecast!!
        adapter = RecyclerViewAdapter()


        hourViewModel.data.observe(viewLifecycleOwner, Observer {
            adapter.data = it
        })

        val layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = adapter
        recyclerView.setNestedScrollingEnabled(false)

        val layoutInflater = LayoutInflater.from(requireContext())
        val weekAdapter = WeekForecastAdapter(linearLayout, layoutInflater)
        val dailyViewModel = DailyWeatherViewModel((requireActivity().applicationContext as App).dailyDataDescriptor, city, requireActivity())
        dailyViewModel.data.observe(viewLifecycleOwner, Observer {
            weekAdapter.data = it
        })


        return binding.root
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