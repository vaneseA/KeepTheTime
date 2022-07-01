package com.example.keepthetime.api

import android.provider.ContactsContract
import com.example.keepthetime.models.BasicResponse
import retrofit2.Call
import retrofit2.http.*

interface APIList {


    //    User
    @FormUrlEncoded
    @POST("/user")
    fun postRequestLogin(
        @Field("email") email: String,
        @Field("password") password: String,
    ): Call<BasicResponse>

    @FormUrlEncoded
    @PUT("/user")
    fun putRequestSignUp(
        @Field("email") email: String,
        @Field("password") pw: String,
        @Field("nick_name") nickname: String,
    ): Call<BasicResponse>


    @GET("user/check")
    fun getRequestCheck(
        @Query("type") type: String,
        @Query("value") value: String,
    ): Call<BasicResponse>
}