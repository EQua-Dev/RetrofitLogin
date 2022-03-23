package com.androidstrike.retrofitlogin.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.androidstrike.retrofitlogin.R
import com.androidstrike.retrofitlogin.data.network.Resource
import com.androidstrike.retrofitlogin.data.network.UserApi
import com.androidstrike.retrofitlogin.data.repo.UserRepo
import com.androidstrike.retrofitlogin.data.responses.User
import com.androidstrike.retrofitlogin.databinding.FragmentHomeBinding
import com.androidstrike.retrofitlogin.ui.base.BaseFragment
import com.androidstrike.retrofitlogin.ui.visible
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking

class Home : BaseFragment<HomeViewModel, FragmentHomeBinding, UserRepo>() {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.progressbar.visible(false)
        viewModel.getUser()

        viewModel.user.observe(viewLifecycleOwner, Observer {
            when (it) {
                is Resource.Success -> {
                    binding.progressbar.visible(false)
                    updateUI(it.value.user)
                }
                is Resource.Loading -> {
                    binding.progressbar.visible(true)
                }
            }
        })
    }

    private fun updateUI(user: User) {
        with(binding) {
            textViewId.text = user.id.toString()
            textViewName.text = user.name
            textViewEmail.text = user.email
        }
    }

    override fun getViewModel() = HomeViewModel::class.java

    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentHomeBinding.inflate(inflater, container, false)

    override fun getFragmentRepo(): UserRepo {
        val token = runBlocking { userPreferences.authToken.first() }
        val api = remoteDataSource.buildApi(UserApi::class.java, token)

        return UserRepo(api)
    }

}