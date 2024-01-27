package com.example.fitme_up.core_data.base

import androidx.datastore.preferences.core.stringPreferencesKey


const val USER_PREFERENCES_NAME = "user_data_store_preferences"

object UserDataStoreKeys {
    val ACCESS_TOKEN_KEY = stringPreferencesKey("ACCESS_TOKEN_KEY")
    val USER_ID_KEY = stringPreferencesKey("USER_ID_KEY")
}
