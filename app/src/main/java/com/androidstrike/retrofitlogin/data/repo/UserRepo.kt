package com.androidstrike.retrofitlogin.data.repo

import com.androidstrike.retrofitlogin.data.UserPreferences
import com.androidstrike.retrofitlogin.data.network.AuthInterface
import com.androidstrike.retrofitlogin.data.network.UserApi

//repository class to handle the login and signup functions
class UserRepo(
    private val api: UserApi
) : BaseRepo() {

    suspend fun getUser() = safeApiCall {
        api.getUser()
    }
}

//PS: The Base Repo is always extended