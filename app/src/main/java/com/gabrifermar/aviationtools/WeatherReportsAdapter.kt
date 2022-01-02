package com.gabrifermar.aviationtools

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.gabrifermar.aviationtools.databinding.ItemWeatherReportBinding
import com.gabrifermar.aviationtools.model.data.Data
import com.gabrifermar.aviationtools.view.Weather
import com.gabrifermar.aviationtools.view.fragments.WeatherDetails
import com.gabrifermar.aviationtools.view.fragments.WeatherReports

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
        fragmentTransaction.replace(R.id.weather_fragment_reports, fragment)
        fragmentTransaction.commit()
    }
}