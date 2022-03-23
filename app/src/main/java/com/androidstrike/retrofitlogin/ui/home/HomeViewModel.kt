package com.androidstrike.retrofitlogin.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.androidstrike.retrofitlogin.data.network.Resource
import com.androidstrike.retrofitlogin.data.repo.UserRepo
import com.androidstrike.retrofitlogin.data.responses.LoginResponse
import kotlinx.coroutines.launch
import retrofit2.Response

class HomeViewModel(
    private val repo: UserRepo
) : ViewModel() {

    private val _user: MutableLiveData<Resource<LoginResponse>> = MutableLiveData()
    val user: LiveData<Resource<LoginResponse>>
        get() = _user

    fun getUser() = viewModelScope.launch {
        _user.value = Resource.Loading
        _user.value = repo.getUser()
    }
}