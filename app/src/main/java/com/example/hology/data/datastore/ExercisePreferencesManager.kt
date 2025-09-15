package com.example.hology.data.datastore

import android.content.Context
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import androidx.datastore.preferences.core.edit
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import com.example.hology.domain.model.ExerciseProgress

// datastore preferences
private val Context.dataStore by preferencesDataStore("exercise_preferences")

class ExercisePreferencesManager(private val context: Context) {

    // function to helper create dynamic key
    private fun doneKey(exerciseId: String, activityId: String) =
        booleanPreferencesKey("exercise_${exerciseId}_activity_${activityId}_done")

    // simpan status done
    suspend fun setExerciseDone(exerciseId: String, activityId: String, done: Boolean) {
        context.dataStore.edit { prefs ->
            prefs[doneKey(exerciseId, activityId)] = done
        }
    }

    // get exercise progress
    fun getExerciseProgress(exerciseId: String): Flow<ExerciseProgress> {
        return context.dataStore.data.map { prefs ->
            val activities = prefs.asMap()
                .filterKeys { it.name.startsWith("exercise_${exerciseId}_activity_") }
                .mapKeys { entry ->
                    entry.key.name
                        .removePrefix("exercise_${exerciseId}_activity_")
                        .removeSuffix("_done")
                }
                .mapValues { entry -> entry.value as Boolean }

            ExerciseProgress(
                exerciseId = exerciseId,
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
