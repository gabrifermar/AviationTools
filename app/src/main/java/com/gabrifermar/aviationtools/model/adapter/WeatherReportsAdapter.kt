/*
 * Created by gabrifermar on 2/4/22 19:22
 * Copyright Ⓒ 2022. All rights reserved
 * Last modified: 12/3/22 12:47
 */

package com.gabrifermar.aviationtools.model.adapter

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.gabrifermar.aviationtools.R
import com.gabrifermar.aviationtools.model.data.Data
import com.gabrifermar.aviationtools.view.Weather
import com.gabrifermar.aviationtools.view.fragments.WeatherDetails

class WeatherReportsAdapter(
    private val context: Context,
    private val stations: List<String>,
    private val reports: List<String>,
    private val conditions: List<String>,
    private val result:List<Data>
) : RecyclerView.Adapter<WeatherReportsAdapter.WeatherReportsHolder>() {

    class WeatherReportsHolder(val view: View) : RecyclerView.ViewHolder(view) {

        fun show(station: String, report: String, condition: String) {
            view.findViewById<TextView>(R.id.itemweatherreport_tv_station).text = station
            view.findViewById<TextView>(R.id.itemweatherreport_tv_condition).text = condition
            view.findViewById<TextView>(R.id.itemweatherreport_tv_report).text = report
        }
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
        holder.show(stations[position], reports[position], conditions[position])

        holder.view.findViewById<CardView>(R.id.itemweatherreport_cv_report).setOnClickListener {
            openDetails(holder.adapterPosition)
        }
    }

    override fun getItemCount(): Int {
        return stations.size
    }

    private fun openDetails(position: Int) {
        val bundle = Bundle()
        bundle.putSerializable("icao", result[position])
        val fragment = WeatherDetails()
        fragment.arguments = bundle
        setFragment(fragment)
    }

    private fun setFragment(fragment: Fragment) {
        val fragmentManager = (context as Weather).supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.weather_fragment_reports, fragment,"details").addToBackStack("main")
        fragmentTransaction.commit()
    }
}