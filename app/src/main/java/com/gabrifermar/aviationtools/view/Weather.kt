/*
 * Created by gabrifermar on 2/4/22 19:22
 * Copyright Ⓒ 2022. All rights reserved
 * Last modified: 2/4/22 18:46
 */

package com.gabrifermar.aviationtools.view

import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.gabrifermar.aviationtools.R
import com.gabrifermar.aviationtools.databinding.ActivityWeatherBinding
import com.gabrifermar.aviationtools.view.fragments.WeatherDetails
import com.gabrifermar.aviationtools.view.fragments.WeatherReports

class Weather : AppCompatActivity() {

    private lateinit var binding: ActivityWeatherBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWeatherBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //backArrow
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        //setFragment(WeatherReports(), "main")
    }

    private fun setFragment(fragment: Fragment, tag: String) {
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.weather_fragment_reports, fragment, tag)
        fragmentTransaction.commit()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        onBackPressed()
        return true
    }

}