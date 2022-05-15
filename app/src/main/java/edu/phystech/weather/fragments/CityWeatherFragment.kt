package edu.phystech.weather.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.ListView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import edu.phystech.weather.App
import edu.phystech.weather.R
import edu.phystech.weather.adapter.RecyclerViewAdapter
import edu.phystech.weather.databinding.FragmentCityWeatherBinding
class CityWeatherFragment : Fragment() {
    private lateinit var binding : FragmentCityWeatherBinding
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: RecyclerViewAdapter
    private lateinit var linearLayout: LinearLayout

    private val hourViewModel: WeatherViewModel by viewModels { factory() }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCityWeatherBinding.inflate(inflater, container, false)
        recyclerView = binding.nestedScroll.dayForecast!!
        linearLayout = binding.nestedScroll.weekForecast!!
        adapter = RecyclerViewAdapter()


        hourViewModel.data.observe(viewLifecycleOwner, Observer { adapter.data  = it })

        val layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = adapter
        recyclerView.setNestedScrollingEnabled(false)

        val item = LayoutInflater.from(context).inflate(R.layout.week_list_item, null)
        val item2 = LayoutInflater.from(context).inflate(R.layout.week_list_item, null)
        item2.findViewById<TextView>(R.id.day).text = "вторник"
        linearLayout.addView(item)
        linearLayout.addView(item2)


        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
    }
}