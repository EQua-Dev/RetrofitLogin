package com.androidstrike.retrofitlogin.data.network

import com.androidstrike.retrofitlogin.data.responses.LoginResponse
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface AuthInterface {

    @FormUrlEncoded
    @POST("auth/login")

   suspend fun login(
        @Field("email") email: String,
        @Field("password") password: String,
    ) : LoginResponse


}


