/*
 * Created by gabrifermar on 2/4/22 19:22
 * Copyright Ⓒ 2022. All rights reserved
 * Last modified: 1/2/22 19:16
 */

package com.gabrifermar.aviationtools.model.api

import androidx.core.content.PackageManagerCompat
import com.gabrifermar.aviationtools.model.data.DecodedWeather
import com.gabrifermar.aviationtools.model.data.RawWeather
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Url

interface ApiRequests {
    @GET
    suspend fun getRawMetar(@Url url: String):Response<RawWeather>

    @GET
    suspend fun getDecodedMetar(@Url url:String):Response<DecodedWeather>
}