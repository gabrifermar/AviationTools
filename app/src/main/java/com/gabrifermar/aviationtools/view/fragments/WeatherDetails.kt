package com.gabrifermar.aviationtools.view.fragments

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.provider.ContactsContract
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.gabrifermar.aviationtools.R
import com.gabrifermar.aviationtools.databinding.FragmentWeatherDetailsBinding
import com.gabrifermar.aviationtools.model.data.Data
import com.gabrifermar.aviationtools.view.Weather
import com.gabrifermar.aviationtools.viewmodel.WeatherDetailsViewModel
import java.io.Serializable

class WeatherDetails : Fragment() {

    companion object {
        fun newInstance() = WeatherDetails()
    }

    private lateinit var viewModel: WeatherDetailsViewModel
    private var _binding: FragmentWeatherDetailsBinding? = null
    private val binding get() = _binding!!

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

        val result = this.arguments?.getSerializable("icao") as Data

        binding.weatherDetailsConstraintHeader.backgroundTintList =
            (activity as Weather).getColorStateList(R.color.gray)
        binding.weatherDetailsTvIcao.text = result.icao
        binding.weatherDetailsTvStationName.text = result.station.name
        binding.weatherDetailsTvTemperature.text =
            (activity as Weather).getString(R.string.temperatureCelsius, result.temperature.celsius)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun getData() {
        val data = this.arguments
        data!!.getString("hola")
    }


}