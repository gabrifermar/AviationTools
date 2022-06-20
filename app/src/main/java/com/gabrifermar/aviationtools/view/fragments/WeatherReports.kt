/*
 * Created by gabrifermar on 20/6/22 5:48
 * Copyright Ⓒ 2022. All rights reserved
 * Last modified: 20/6/22 5:31
 */

package com.gabrifermar.aviationtools.view.fragments

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import android.view.KeyEvent
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.gabrifermar.aviationtools.R
import com.gabrifermar.aviationtools.model.adapter.WeatherReportsAdapter
import com.gabrifermar.aviationtools.databinding.FragmentWeatherReportsBinding
import com.gabrifermar.aviationtools.view.Weather
import com.gabrifermar.aviationtools.viewmodel.WeatherReportsViewModel
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipDrawable

class WeatherReports : Fragment() {

    private lateinit var viewModel: WeatherReportsViewModel
    private lateinit var adapter: WeatherReportsAdapter
    private lateinit var navgraph: NavController
    private var _binding: FragmentWeatherReportsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentWeatherReportsBinding.inflate(inflater, container, false)
        navgraph = findNavController()
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
        viewModel.stationsLiveData.observe(activity as Weather, nameObserver)

        binding.weatherCgIcao.setOnCheckedChangeListener{ _, checkedId ->
            val chip = binding.weatherCgIcao.findViewById<Chip>(checkedId)
            binding.weatherCgIcao.removeView(chip)
        }

        binding.weatherTxtIcao.setOnKeyListener(View.OnKeyListener{ _, keyCode, event ->
            if (keyCode == KeyEvent.KEYCODE_ENTER && event.action == KeyEvent.ACTION_UP) {
                if (binding.weatherTxtIcao.text.length == 4) {
                    //check icon
                    addChip(binding.weatherTxtIcao.text.toString())
                } else {
                    binding.weatherTxtIcao.error = resources.getString(R.string.wrongicao)
                    binding.weatherTxtIcao.requestFocus()
                }
                return@OnKeyListener true
            }
            false
        })
    }

    private fun addChip(text: String) {
        //variables
        val chip = Chip(activity as Weather)
        val drawable = ChipDrawable.createFromAttributes(activity as Weather, null, 0, R.style.Metarchip)

        //setup chip
        chip.setChipDrawable(drawable)
        chip.text = text
        chip.isCloseIconVisible = true
        binding.weatherCgIcao.addView(chip)
    }

    private fun initRecycler() {
        binding.weatherRvReports.layoutManager = LinearLayoutManager(activity)
        adapter =
            WeatherReportsAdapter(
                activity as Weather,
                viewModel.stations,
                viewModel.reports,
                viewModel.conditions,
                viewModel.result,
                navgraph
            )
        binding.weatherRvReports.adapter = adapter
    }

    //TODO: pte revisar si se puede evitar el borrar todo y recuperarlo a volver a cargar el fragmento
    override fun onDestroy() {
        super.onDestroy()
        val rvSize = viewModel.stations.size
        viewModel.clear()
        adapter.notifyItemRangeRemoved(0, rvSize)
        _binding = null
    }

    override fun onDestroyView() {
        super.onDestroyView()
        val rvSize = viewModel.stations.size
        viewModel.clear()
        adapter.notifyItemRangeRemoved(0, rvSize)
        _binding = null
    }

    override fun onResume() {
        super.onResume()
        adapter.notifyDataSetChanged()
    }
}