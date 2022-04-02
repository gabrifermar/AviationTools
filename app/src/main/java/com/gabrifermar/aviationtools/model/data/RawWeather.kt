/*
 * Created by gabrifermar on 2/4/22 19:22
 * Copyright Ⓒ 2022. All rights reserved
 * Last modified: 1/2/22 19:16
 */

package com.gabrifermar.aviationtools.model.data

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class RawWeather(
    @SerializedName("results") var results: Int,
    @SerializedName("data") var rawreport:List<String>
)
