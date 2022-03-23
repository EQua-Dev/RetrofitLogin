package com.androidstrike.retrofitlogin.data.repo

import com.androidstrike.retrofitlogin.data.UserPreferences
import com.androidstrike.retrofitlogin.data.network.AuthInterface

//repository class to handle the login and signup functions
class AuthRepo(
    private val api: AuthInterface,
    private val preferences: UserPreferences
) : BaseRepo() {

    //login function containing email and password values from user
    suspend fun login(
        email: String,
        password: String
    ) = safeApiCall {
        api.login(email, password)
    }
    //the function calls the login function from our API interface passing the user entered email and password

    //function to save the user token and all from the viewModel
    suspend fun saveAuthToken(token: String){
        preferences.saveAuthToken(token)
    }

}

//PS: The Base Repo is always extended