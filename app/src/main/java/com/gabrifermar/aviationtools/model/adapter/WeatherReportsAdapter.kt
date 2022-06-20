/*
 * Created by gabrifermar on 20/6/22 5:48
 * Copyright Ⓒ 2022. All rights reserved
 * Last modified: 20/6/22 5:38
 */

package com.gabrifermar.aviationtools.model.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.navigation.NavController
import androidx.recyclerview.widget.RecyclerView
import com.gabrifermar.aviationtools.R
import com.gabrifermar.aviationtools.model.data.Data
import com.gabrifermar.aviationtools.view.fragments.WeatherReportsDirections

class WeatherReportsAdapter(
    private val context: Context,
    private val stations: List<String>,
    private val reports: List<String>,
    private val conditions: List<String>,
    private val result: List<Data>,
    private val navgraph: NavController
) : RecyclerView.Adapter<WeatherReportsAdapter.WeatherReportsHolder>() {

    class WeatherReportsHolder(val view: View) : RecyclerView.ViewHolder(view) {

        fun show(station: String, report: String, condition: String, context: Context) {
            view.findViewById<TextView>(R.id.itemweatherreport_tv_station).text = station
            view.findViewById<TextView>(R.id.itemweatherreport_tv_condition).text = condition
            view.findViewById<TextView>(R.id.itemweatherreport_tv_report).text = report
            if (condition == "IFR" || condition == "LIFR") {
                view.findViewById<CardView>(R.id.itemweatherreport_cv_condition)
                    .setCardBackgroundColor(ContextCompat.getColor(context, R.color.IFR))
            } else {
                view.findViewById<CardView>(R.id.itemweatherreport_cv_condition)
                    .setCardBackgroundColor(ContextCompat.getColor(context, R.color.VFR))
            }
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
        holder.show(stations[position], reports[position], conditions[position], context)

        holder.view.findViewById<CardView>(R.id.itemweatherreport_cv_report).setOnClickListener {
            openDetails(holder.adapterPosition)
        }
    }

    override fun getItemCount(): Int {
        return stations.size
    }

    private fun openDetails(position: Int) {
        val action = WeatherReportsDirections.actionWeatherReportsToWeatherDetails(result[position])
        navgraph.navigate(action)
    }

}