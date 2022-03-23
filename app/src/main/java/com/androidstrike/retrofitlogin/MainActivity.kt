package com.androidstrike.retrofitlogin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.asLiveData
import com.androidstrike.retrofitlogin.data.UserPreferences
import com.androidstrike.retrofitlogin.ui.AuthActivity
import com.androidstrike.retrofitlogin.ui.home.HomeActivity
import com.androidstrike.retrofitlogin.ui.startNewActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val userPreferences = UserPreferences(this)
        //checks the user preferences and if it is not null, it means that user has previously logged in
        userPreferences.authToken.asLiveData().observe(this, Observer {
            //checks: if there is a saved auth token, launches the home activity else launches the auth activity
            val activity = if (it == null) AuthActivity::class.java else HomeActivity::class.java
            startNewActivity(activity)

        })

    }
}