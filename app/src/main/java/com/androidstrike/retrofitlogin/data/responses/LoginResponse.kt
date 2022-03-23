package com.androidstrike.retrofitlogin.data.responses

//data class containing the objects in the api route
//returns the user class because all the parameters in the 'User' class are within this
data class LoginResponse(
    val user: User
)