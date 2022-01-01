package com.gabrifermar.aviationtools.model.data

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class RawWeather(
    @SerializedName("results") var results: Int,
    @SerializedName("data") var rawreport:List<String>
)
