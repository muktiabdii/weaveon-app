package com.example.hology.data.datastore

import android.content.Context
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import com.example.hology.domain.model.WevyProgress
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

private val Context.dataStore by preferencesDataStore("wevy_preferences")

class WevyPreferencesManager(private val context: Context) {

    // function helper to create dynamic key
    private fun doneKey(wevyId: String, activityId: String) =
        booleanPreferencesKey("wevy_${wevyId}_activity_${activityId}_done")

    // save done status
    suspend fun setWevyDone(wevyId: String, activityId: String, done: Boolean) {
        context.dataStore.edit { prefs ->
            prefs[doneKey(wevyId, activityId)] = done
        }
    }

    // get wevy progress
    fun getWevyProgress(wevyId: String): Flow<WevyProgress> {
        return context.dataStore.data.map { prefs ->
            val activities = prefs.asMap()
                .filterKeys { it.name.startsWith("wevy_${wevyId}_activity_") }
                .mapKeys { entry ->
                    entry.key.name
                        .removePrefix("wevy_${wevyId}_activity_")
                        .removeSuffix("_done")
                }
                .mapValues { entry -> entry.value as Boolean }

            WevyProgress(
                wevyId = wevyId,
                activities = activities
            )
        }
    }

    // clear all preferences
    suspend fun clear() {
        context.dataStore.edit { prefs ->
            prefs.clear()
        }
    }
}