package com.tare.authentication

import androidx.datastore.preferences.core.booleanPreferencesKey

object PreferenceKeys {
    val IS_USER_LOGGED_IN = booleanPreferencesKey("is_user_logged_in")
    const val User_Logged_In = "Login"
    const val User_Id = "USERID"
    const val User_Key = "APIKEY"
}