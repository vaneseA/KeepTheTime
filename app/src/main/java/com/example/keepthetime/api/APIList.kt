package com.example.keepthetime.api

import com.example.keepthetime.models.BasicResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface APIList {


    //    User
    @GET("user/check")
    fun getRequestCheck(
        @Query("type") type: String,
        @Query("value") value: String,
    ): Call<BasicResponse>
}