package com.udacity.load.app.data.network

import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Streaming
import retrofit2.http.Url

interface ApiInterface {

    @Streaming
    @GET
    suspend fun load(
        @Url url: String
    ): Response<ResponseBody>

}