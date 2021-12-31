package com.gabrifermar.aviationtools.view.fragments

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.gabrifermar.aviationtools.WeatherReportsAdapter
import com.gabrifermar.aviationtools.databinding.FragmentWeatherReportsBinding
import com.gabrifermar.aviationtools.viewmodel.WeatherReportsViewModel

class WeatherReports : Fragment() {

    companion object {
        fun newInstance() = WeatherReports()
    }

    private lateinit var viewModel: WeatherReportsViewModel
    private lateinit var adapter:WeatherReportsAdapter
    private var stations= mutableListOf<String>()
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
    }

    private fun initRecycler() {
        binding.weatherreportsRvReports.layoutManager = LinearLayoutManager(activity)
        adapter = WeatherReportsAdapter(stations,)
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