package com.gabrifermar.aviationtools.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.gabrifermar.aviationtools.databinding.ActivityMenuBinding

class Menu : AppCompatActivity() {

    private lateinit var binding: ActivityMenuBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMenuBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //listeners
        startListeners()
    }

    private fun startListeners() {
        binding.menuCvWeather.setOnClickListener {
            startActivity(Intent(this, Weather::class.java))
        }
    }
}