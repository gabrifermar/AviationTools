/*
 * Created by gabrifermar on 2/4/22 19:28
 * Copyright Ⓒ 2022. All rights reserved
 * Last modified: 2/4/22 19:26
 */

package com.gabrifermar.aviationtools.view.fragments

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.gabrifermar.aviationtools.R
import com.gabrifermar.aviationtools.model.adapter.WeatherReportsAdapter
import com.gabrifermar.aviationtools.databinding.FragmentWeatherReportsBinding
import com.gabrifermar.aviationtools.model.data.Data
import com.gabrifermar.aviationtools.view.Weather
import com.gabrifermar.aviationtools.viewmodel.WeatherReportsViewModel

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
        val nameObserver = Observer<List<String>> {
            adapter.notifyItemRangeInserted(0, it.size)
        }
        viewModel.stationsLiveData.observe(activity as Weather,nameObserver)


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
        val rvSize=viewModel.stations.size
        viewModel.clear()
        adapter.notifyItemRangeRemoved(0,rvSize)
        _binding = null
    }

}