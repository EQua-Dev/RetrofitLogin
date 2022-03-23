package com.androidstrike.retrofitlogin.ui.auth

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import com.androidstrike.retrofitlogin.databinding.FragmentLoginBinding
import com.androidstrike.retrofitlogin.data.network.AuthInterface
import com.androidstrike.retrofitlogin.data.network.Resource
import com.androidstrike.retrofitlogin.data.repo.AuthRepo
import com.androidstrike.retrofitlogin.ui.base.BaseFragment
import com.androidstrike.retrofitlogin.ui.enable
import com.androidstrike.retrofitlogin.ui.home.HomeActivity
import com.androidstrike.retrofitlogin.ui.startNewActivity
import com.androidstrike.retrofitlogin.ui.visible
import kotlinx.coroutines.launch

class Login : BaseFragment<AuthViewModel, FragmentLoginBinding, AuthRepo>() {

    //this function will contain all the logic for the fragment
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        //hides the progress bar on fragment launch
        binding.progressbar.visible(false)
        //disables the log in button immediately fragment is launched
        binding.buttonLogin.enable(false)

        viewModel.loginResponse.observe(viewLifecycleOwner, Observer {
            //hides the progress bar when API check is done
            binding.progressbar.visible(false)
            when (it) {
                is Resource.Success -> {
                    //if the login wa successful, save the auth token in the dataPreference using viewModel
                    viewModel.saveAuthToken(it.value.user.access_token.toString())
                    requireActivity().startNewActivity(HomeActivity::class.java)

                }
                is Resource.Failure -> {
                    Toast.makeText(requireContext(), "Login Failure", Toast.LENGTH_SHORT).show()
                }
            }
        })

        //function to watch for changes in the password edit text
        binding.editTextTextPassword.addTextChangedListener {
            val email = binding.editTextTextEmailAddress.text.toString().trim()
            //make the button visible if the email and password inputs are not empty
            binding.buttonLogin.enable(email.isNotEmpty() && it.toString().trim().isNotEmpty())
        }
        binding.buttonLogin.setOnClickListener {
            val email = binding.editTextTextEmailAddress.text.toString().trim()
            val password = binding.editTextTextPassword.text.toString().trim()

            //shows the progress bar during API check
            binding.progressbar.visible(true)
            viewModel.login(email, password)
        }

    }

    //the viewBinding function returns the viewModel class (as that is the class that communicates with the UI)
    override fun getViewModel() = AuthViewModel::class.java

    //in this function, the layout is inflated to the activity
    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentLoginBinding.inflate(inflater, container, false)

    //in this function, the AuthRepo is called passing the API interface parameter...we create it instance in the base fragment 1st
    override fun getFragmentRepo() =
        AuthRepo(remoteDataSource.buildApi(AuthInterface::class.java), userPreferences)

}