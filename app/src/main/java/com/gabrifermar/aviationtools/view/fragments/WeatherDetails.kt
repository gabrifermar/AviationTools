package com.gabrifermar.aviationtools.view.fragments

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.gabrifermar.aviationtools.R
import com.gabrifermar.aviationtools.databinding.FragmentWeatherDetailsBinding
import com.gabrifermar.aviationtools.viewmodel.WeatherDetailsViewModel

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

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this)[WeatherDetailsViewModel::class.java]
        // TODO: Use the ViewModel
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun getData(){
        val data=this.arguments
        data!!.getString("hola")
    }

}