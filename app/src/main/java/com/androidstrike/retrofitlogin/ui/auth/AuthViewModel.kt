package com.androidstrike.retrofitlogin.ui.auth

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.androidstrike.retrofitlogin.data.network.Resource
import com.androidstrike.retrofitlogin.data.repo.AuthRepo
import com.androidstrike.retrofitlogin.data.responses.LoginResponse
import kotlinx.coroutines.launch

//this class interacts with the UI and the repository according to the MVVM pattern
//the repository is passed as a parameter to enable communication with its functions from here
class AuthViewModel(
    private val repo: AuthRepo
) : ViewModel() {

    private val _loginResponse: MutableLiveData<Resource<LoginResponse>> = MutableLiveData()
    val loginResponse: LiveData<Resource<LoginResponse>>
    get() = _loginResponse

    fun login(
        email: String,
        password: String,
    ) =
        viewModelScope.launch {
            _loginResponse.value = repo.login(email, password)
        }

    fun saveAuthToken(token: String) = viewModelScope.launch {
        repo.saveAuthToken(token)
    }
}