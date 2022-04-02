/*
 * Created by gabrifermar on 2/4/22 19:22
 * Copyright Ⓒ 2022. All rights reserved
 * Last modified: 1/2/22 19:16
 */

package com.gabrifermar.aviationtools.model.data

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName
import java.io.Serializable

@Keep
data class DecodedWeather(
    @SerializedName("results") var results: Int,
    @SerializedName("data") var data: List<Data>
)

@Keep
data class Data(
    @SerializedName("barometer") var barometer: Barometer,
    @SerializedName("clouds") var clouds: List<Cloud>,
    @SerializedName("conditions") var conditions: List<Conditions>,
    @SerializedName("dewpoint") var dewpoint: DewPoint,
    @SerializedName("elevation") var elevation: Elevation,
    @SerializedName("flight_category") var flightcategory: String,
    @SerializedName("humidity") var humidity: Humidity,
    @SerializedName("icao") var icao: String,
    @SerializedName("observed") var reportTime: String,
    @SerializedName("raw_text") var rawReport: String,
    @SerializedName("station") var station: Station,
    @SerializedName("temperature") var temperature: Temperature,
    @SerializedName("visibility") var visibility: Visibility,
    @SerializedName("wind") var wind: Wind
) : Serializable

@Keep
data class Barometer(
    @SerializedName("hg") var hg: Double,
    @SerializedName("hpa") var hpa: Int
)

@Keep
data class Cloud(
    @SerializedName("code") var code: String,
    @SerializedName("text") var text: String,
    //@SerializedName("feet") var feet: Int
)

@Keep
data class Conditions(
    @SerializedName("code") var code: String,
    @SerializedName("text") var text: String
)

@Keep
data class DewPoint(
    @SerializedName("celsius") var celsius: Int,
    @SerializedName("fahrenheit") var fahrenheit: Int
)

@Keep
data class Elevation(
    @SerializedName("feet") var feet: Int,
    @SerializedName("meters") var meters: Int
)

@Keep
data class Humidity(
    @SerializedName("percent") var percent: Int
)

@Keep
data class Station(
    @SerializedName("location") var location: String,
    @SerializedName("name") var name: String,
    @SerializedName("type") var type: String
)

@Keep
data class Temperature(
    @SerializedName("celsius") var celsius: Int,
    @SerializedName("fahrenheit") var fahrenheit: Int
)

@Keep
data class Visibility(
    @SerializedName("meters") var meters: String
)

@Keep
data class Wind(
    @SerializedName("degrees") var degrees: Int,
    @SerializedName("speed_kts") var kts: Int
)