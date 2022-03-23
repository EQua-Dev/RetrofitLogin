package com.androidstrike.retrofitlogin.data.network

import com.androidstrike.retrofitlogin.data.responses.LoginResponse
import retrofit2.http.GET

interface UserApi {

    @GET("user")
    suspend fun getUser(): LoginResponse
}