package com.gabrifermar.aviationtools.view.fragments

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.gabrifermar.aviationtools.WeatherReportsAdapter
import com.gabrifermar.aviationtools.databinding.FragmentWeatherReportsBinding
import com.gabrifermar.aviationtools.model.api.ApiRequests
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
    private var stations = mutableListOf<String>()
    private var reports = mutableListOf<String>()
    private var conditions = mutableListOf<String>()
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

        //recycler
        initRecycler()

        //api
        apiCall()
    }

    private fun apiCall() {
        CoroutineScope(Dispatchers.IO).launch {

            val api = Retrofit.Builder().baseUrl("https://api.checkwx.com/metar/")
                .addConverterFactory(GsonConverterFactory.create()).build()

            val decodedWeather = api.create(ApiRequests::class.java)
                .getDecodedMetar("LEMD,LEMG,LEAS,LECO,LEBL,LEVC,LEVD/decoded/?x-api-key=d49660ce845e4f3db1fc469256")
                .body()

            val result = decodedWeather?.data ?: emptyList()

            requireActivity().runOnUiThread {


                result.forEach {
                    stations.add(it.icao)
                    reports.add(it.rawReport)
                    conditions.add(it.flightcategory)
                    adapter.notifyDataSetChanged()
                }

            }
        }
    }

    private fun initRecycler() {
        binding.weatherreportsRvReports.layoutManager = LinearLayoutManager(activity)
        adapter = WeatherReportsAdapter(stations, reports, conditions)
        binding.weatherreportsRvReports.adapter = adapter
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun sendData(): Bundle {
        val data = Bundle()
        data.putString("hola", "hola")
        return data
    }

}