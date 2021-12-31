package com.gabrifermar.aviationtools

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.gabrifermar.aviationtools.view.Weather

class WeatherReportsAdapter(
    private val stations: List<String>,
    private val reports: List<String>,
    private val conditions: List<String>
) : RecyclerView.Adapter<WeatherReportsAdapter.WeatherReportsHolder>() {

    class WeatherReportsHolder(val view: View) : RecyclerView.ViewHolder(view) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeatherReportsHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return WeatherReportsHolder(
            layoutInflater.inflate(
                R.layout.item_weather_report, parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: WeatherReportsHolder, position: Int) {
        TODO("Not yet implemented")
    }

    override fun getItemCount(): Int {
        return stations.size
    }

}