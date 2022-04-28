package com.example.gps_background_test

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface postInterface {
    @POST("https://www.woojae.tk/")
    fun post(@Body loc : locationData)
    : Call<BaseResponse>
}