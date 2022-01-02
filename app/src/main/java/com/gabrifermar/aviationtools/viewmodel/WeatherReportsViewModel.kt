package com.gabrifermar.aviationtools.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.gabrifermar.aviationtools.model.api.ApiRequests
import com.gabrifermar.aviationtools.model.data.Data
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class WeatherReportsViewModel : ViewModel() {

    var stations = mutableListOf<String>()
    var reports = mutableListOf<String>()
    var conditions = mutableListOf<String>()
    var result = mutableListOf<Data>()
    private lateinit var results: List<Data>
    var stationsLiveData = MutableLiveData<List<String>>()

    fun apiCall() {
        CoroutineScope(Dispatchers.IO).launch {
            val api = Retrofit.Builder().baseUrl("https://api.checkwx.com/metar/")
                .addConverterFactory(GsonConverterFactory.create()).build()

            val decodedWeather = api.create(ApiRequests::class.java)
                .getDecodedMetar("LEMD,LEMG,LEAS,LECO,LEBL,LEVC,LEVD/decoded/?x-api-key=d49660ce845e4f3db1fc469256")
                .body()

            results = decodedWeather?.data ?: emptyList()

            results.forEach {
                stations.add(it.icao)
                reports.add(it.rawReport)
                conditions.add(it.flightcategory)
            }

            result.addAll(results)

            stationsLiveData.postValue(stations)

        }
    }
}