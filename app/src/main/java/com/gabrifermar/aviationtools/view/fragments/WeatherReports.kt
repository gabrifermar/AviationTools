package com.gabrifermar.aviationtools.view.fragments

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.gabrifermar.aviationtools.R
import com.gabrifermar.aviationtools.WeatherReportsAdapter
import com.gabrifermar.aviationtools.databinding.FragmentWeatherReportsBinding
import com.gabrifermar.aviationtools.model.api.ApiRequests
import com.gabrifermar.aviationtools.model.data.Data
import com.gabrifermar.aviationtools.view.Weather
import com.gabrifermar.aviationtools.viewmodel.WeatherReportsViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class WeatherReports : Fragment() {

    companion object {
        fun newInstance() = WeatherReports()
    }

    private lateinit var viewModel: WeatherReportsViewModel
    private lateinit var adapter: WeatherReportsAdapter
    private lateinit var result: List<Data>
    private var _binding: FragmentWeatherReportsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentWeatherReportsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this)[WeatherReportsViewModel::class.java]

        //api
        viewModel.apiCall()

        //recycler
        initRecycler()

        //listeners
        startListeners()

    }

    private fun startListeners() {
        viewModel.stationsLiveData.observe(activity as Weather, {
            adapter.notifyItemRangeInserted(0, it.size)
        })

    }

    private fun initRecycler() {
        binding.weatherreportsRvReports.layoutManager = LinearLayoutManager(activity)
        adapter =
            WeatherReportsAdapter(
                activity as Weather,
                viewModel.stations,
                viewModel.reports,
                viewModel.conditions,
                viewModel.result
            )
        binding.weatherreportsRvReports.adapter = adapter
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}