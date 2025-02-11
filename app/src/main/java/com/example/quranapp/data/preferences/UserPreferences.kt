package com.example.quranapp.data.preferences

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.*
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "user_prefs")

class UserPreferences(context: Context) {
    private val dataStore = context.dataStore

    companion object {
        private val ONBOARDING_STATUS = booleanPreferencesKey("onboarding_status")
    }

    fun getOnboardingStatus(): Flow<Boolean> {
        return dataStore.data.map { preferences ->
            preferences[ONBOARDING_STATUS] ?: false
        }
    }

    suspend fun setOnboardingStatus(status: Boolean) {
        dataStore.edit { preferences ->
            preferences[ONBOARDING_STATUS] = status
        }
    }
}
