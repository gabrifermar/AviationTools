/*
 * Created by gabrifermar on 20/6/22 5:48
 * Copyright Ⓒ 2022. All rights reserved
 * Last modified: 20/6/22 5:28
 */

package com.gabrifermar.aviationtools.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.gabrifermar.aviationtools.R
import com.gabrifermar.aviationtools.databinding.FragmentWeatherDetailsBinding
import com.gabrifermar.aviationtools.model.data.Data
import com.gabrifermar.aviationtools.view.Weather
import com.gabrifermar.aviationtools.viewmodel.WeatherDetailsViewModel

class WeatherDetails : Fragment() {

    companion object {
        fun newInstance() = WeatherDetails()
    }

    private lateinit var viewModel: WeatherDetailsViewModel
    private lateinit var result: Data
    private var _binding: FragmentWeatherDetailsBinding? = null
    private val binding get() = _binding!!
    private val args: WeatherDetailsArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentWeatherDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this)[WeatherDetailsViewModel::class.java]

        //get data
        getData()

        setupColor(result.flightcategory)

        binding.weatherDetailsTvIcao.text = result.icao
        binding.weatherDetailsTvStationName.text = result.station.name
        binding.weatherDetailsTvTemperature.text =
            (activity as Weather).getString(R.string.temperatureCelsius, result.temperature.celsius)

        binding.weatherDetailsTvWindDegrees.text = (activity as Weather).getString(
            R.string.windDegrees,
            result.wind.degrees.toString().padStart(3, '0')
        )
        binding.weatherDetailsTvWindSpeed.text =
            (activity as Weather).getString(R.string.windSpeed, result.wind.kts)
        binding.weatherDetailsIvWindArrow.rotation = result.wind.degrees.toFloat() - 180f
    }

    private fun setupColor(code: String) {
        if (code == "IFR" || code == "LIFR")
            binding.weatherDetailsConstraintHeader.backgroundTintList =
                (activity as Weather).getColorStateList(R.color.IFR)
        else
            binding.weatherDetailsConstraintHeader.backgroundTintList =
                (activity as Weather).getColorStateList(R.color.VFR)

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun getData() {
        result = args.meteo
        //result = this.arguments?.getSerializable("icao") as Data
    }

}