package com.androidstrike.retrofitlogin.data.network

import okhttp3.ResponseBody

//sealed class to wrap and handle API response i.e success and failure
sealed class Resource<out T>{

    //returns a value if the api call is successful
    data class Success<out T>(val value: T): Resource<T>()

    data class Failure(
        val isNetworkError: Boolean?,
        val errorCode: Int?,
        val errorBody: ResponseBody?
    ): Resource<Nothing>()
    object Loading: Resource<Nothing>()
}
