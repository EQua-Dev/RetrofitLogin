package com.androidstrike.retrofitlogin.ui.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.androidstrike.retrofitlogin.data.repo.AuthRepo
import com.androidstrike.retrofitlogin.data.repo.BaseRepo
import com.androidstrike.retrofitlogin.data.repo.UserRepo
import com.androidstrike.retrofitlogin.ui.auth.AuthViewModel
import com.androidstrike.retrofitlogin.ui.home.HomeViewModel
import java.lang.IllegalArgumentException


//this class handles the rendering for all viewModels used in this project
class ViewModelFactory(
    private val repo: BaseRepo
) : ViewModelProvider.NewInstanceFactory() {

    //in its create method...
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return when {
                //... it checks the viewModel which is using it and casts the base repo to that viewModel's repo
            modelClass.isAssignableFrom(AuthViewModel::class.java) -> AuthViewModel(repo as AuthRepo) as T
            modelClass.isAssignableFrom(HomeViewModel::class.java) -> HomeViewModel(repo as UserRepo) as T
            else -> throw IllegalArgumentException("ViewModelClass Not Found")
        }
    }

}