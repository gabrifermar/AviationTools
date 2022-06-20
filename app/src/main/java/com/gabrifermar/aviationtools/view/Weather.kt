/*
 * Created by gabrifermar on 20/6/22 5:48
 * Copyright Ⓒ 2022. All rights reserved
 * Last modified: 20/6/22 5:01
 */

package com.gabrifermar.aviationtools.view

import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
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

//        //backArrow
//        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
//        val navHostFragment = supportFragmentManager.findFragmentById(R.id.weather_fragment_reports) as NavHostFragment
//        navHostFragment.findNavController().run {
//            this.graph
//        }

    }

}