package com.gabrifermar.aviationtools.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.gabrifermar.aviationtools.R
import com.gabrifermar.aviationtools.databinding.ActivityWeatherBinding
import com.gabrifermar.aviationtools.view.fragments.WeatherReports

class Weather : AppCompatActivity() {

    private lateinit var binding: ActivityWeatherBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityWeatherBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setFragment(WeatherReports())


    }

    private fun setFragment(fragment:Fragment) {
        val fragmentManager=supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.weather_fragment_reports,fragment)
        fragmentTransaction.commit()
    }
}