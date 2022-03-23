package com.androidstrike.retrofitlogin.data.repo

import com.androidstrike.retrofitlogin.data.network.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.HttpException

//repository to follow the mvvm pattern
abstract class BaseRepo {

    //suspend function to make api call safely
    suspend fun <T> safeApiCall(
        apiCall: suspend () -> T
    ): Resource<T>{
        return withContext(Dispatchers.IO){
            //run api call in IO thread
            try {
                //if call returns successful response, set its values to the resource sealed class
                Resource.Success(apiCall.invoke())
            }catch (throwable: Throwable){
                //if call returns unsuccessful response...
                when(throwable){
                    is HttpException ->{
                        //if the error response is a http error set its response to the corresponding parameters in the resource sealed class
                        Resource.Failure(false,throwable.code(), throwable.response()?.errorBody())
                    }
                    else ->{
                        //if the error response is a network error set its response to the corresponding parameters in the resource sealed class
                        Resource.Failure(true,null,null)
                    }
                }
            }
        }
    }
}


//PS: This class is constant for api logins, no variable worth changing yet is here
//...unless you know EXACTLY what you're doing