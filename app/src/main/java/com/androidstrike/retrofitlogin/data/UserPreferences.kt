package com.androidstrike.retrofitlogin.data

import android.content.Context
import androidx.datastore.DataStore
//import androidx.datastore.core.DataStore
import androidx.datastore.createDataStore
import androidx.datastore.preferences.Preferences
//import androidx.datastore.preferences.core.Preferences
//import androidx.datastore.preferences.core.edit
//import androidx.datastore.preferences.core.stringPreferencesKey
//import androidx.datastore.preferences.core.stringSetPreferencesKey
import androidx.datastore.preferences.createDataStore
import androidx.datastore.preferences.edit
import androidx.datastore.preferences.preferencesKey
//import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map


//private val Context.dataStore by preferencesDataStore("my_data_store")
//this is a class to save the token of the logged in user
class UserPreferences(
    context: Context
) {
    //get the application context to be used whenever this class is called
    private val applicationContext = context.applicationContext

    //the dataStore component is a modification of the shared prefs method which saves data in key-value pair
    private val dataStore: DataStore<Preferences>

//    private val dataStore = context.dataStore

    init {
        dataStore = applicationContext.createDataStore(
            name = "my_data_store"
        )//.createDataStore(

        //)
    }

    //function to fetch the stored token and put into a map with the key
    val authToken: Flow<String?>
        get() = dataStore.data.map { preferences ->
            preferences[KEY_AUTH]
        }

    //function to save the user's token using the already defined key
    suspend fun saveAuthToken(authToken: String) {
        dataStore.edit { preference ->
            preference[KEY_AUTH] = authToken
        }
    }

    companion object {
        //        private val KEY_AUTH = stringPreferencesKey("key_auth")
        private val KEY_AUTH = preferencesKey<String>("key_auth")
    }

}