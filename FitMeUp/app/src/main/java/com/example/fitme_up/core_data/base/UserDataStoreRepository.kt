package com.example.fitme_up.core_data.base

import android.content.Context
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.runBlocking
import java.math.BigDecimal


val Context.userDataStore by preferencesDataStore(
    name = USER_PREFERENCES_NAME
)

class UserDataStoreRepository(
    private val context: Context
) {

    fun getAccessToken(): Flow<String?> {
        val repository = DataStoreRepository(context.userDataStore)
        return repository.readFromDataStore(UserDataStoreKeys.ACCESS_TOKEN_KEY)
    }

    suspend fun isLoggedIn(): Boolean {
        return getAccessToken().firstOrNull() != null
    }

    suspend fun saveUserSession(accessToken: String) {
        val repository = DataStoreRepository(context.userDataStore)
        repository.saveToDataStore(UserDataStoreKeys.ACCESS_TOKEN_KEY, accessToken)
    }

    fun getUserId(): Flow<String?> {
        val repository = DataStoreRepository(context.userDataStore)
        return repository.readFromDataStore(UserDataStoreKeys.USER_ID_KEY)
    }

    suspend fun saveFullName(name: String){
        val repository = DataStoreRepository(context.userDataStore)
        repository.saveToDataStore(UserDataStoreKeys.FULLNAME, name)
    }

    fun getFullname(): Flow<String?> {
        val repository = DataStoreRepository(context.userDataStore)
        return repository.readFromDataStore(UserDataStoreKeys.FULLNAME)
    }

    suspend fun clearUserSession() {
        val repository = DataStoreRepository(context.userDataStore)
        repository.clearAll()
    }
}