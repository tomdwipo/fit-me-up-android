package com.example.fitme_up.core_data.base

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import java.io.IOException


class DataStoreRepository(private val prefDataStore: DataStore<Preferences>) {

    fun <T> readFromDataStore(key: Preferences.Key<T>, defaultValue: T): Flow<T> {
        return prefDataStore.data.catch { ex ->
            if (ex is IOException) {
                emit(emptyPreferences())
            } else throw ex
        }.map { preferences ->
            preferences[key] ?: defaultValue
        }
    }

    fun <T> readFromDataStore(key: Preferences.Key<T>): Flow<T?> {
        return prefDataStore.data.catch { ex ->
            if (ex is IOException) {
                emit(emptyPreferences())
            } else throw ex
        }.map { preferences ->
            preferences[key]
        }
    }

    suspend fun <T> saveToDataStore(key: Preferences.Key<T>, value: T) {
        prefDataStore.edit { preferences ->
            preferences[key] = value
        }
    }

    suspend fun <T> removeByKey(key: Preferences.Key<T>) {
        prefDataStore.edit { preferences ->
            preferences.remove(key)
        }
    }

    suspend fun clearAll() {
        prefDataStore.edit { preferences ->
            preferences.clear()
        }
    }
}

