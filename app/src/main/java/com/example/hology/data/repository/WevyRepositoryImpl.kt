package com.example.hology.data.repository

import android.content.Context
import android.util.Log
import com.example.hology.data.datastore.WevyPreferencesManager
import com.example.hology.data.model.EmotionDetectionResponse
import com.example.hology.data.remote.api.ApiEmotionDetectionService
import com.example.hology.data.remote.firebase.FirebaseProvider
import com.example.hology.domain.model.WevyProgress
import com.example.hology.domain.repository.WevyRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.tasks.await
import okhttp3.MultipartBody

class WevyRepositoryImpl(private val preferences: WevyPreferencesManager) : WevyRepository {

    private val database = FirebaseProvider.database

    // function to detect emotion
    override suspend fun detectEmotion(
        file: MultipartBody.Part
    ): EmotionDetectionResponse? {
        return try {
            val response = ApiEmotionDetectionService.instance.detectEmotion(file)
            if (response.isSuccessful) {
                response.body()
            } else {
                return null
            }
        } catch (e: Exception) {
            return null
        }
    }

    // function to save emotion
    override suspend fun saveEmotion(
        userId: String,
        wevyId: String,
        activityId: String,
        result: EmotionDetectionResponse
    ): Result<Unit> {
        return try {
            val data = mapOf(
                "label" to result.label,
                "timestamp" to System.currentTimeMillis()
            )

            database.child("users")
                .child(userId)
                .child("wevy")
                .child(wevyId)
                .child("activities")
                .child(activityId)
                .updateChildren(data)
                .await()

            preferences.setWevyDone(wevyId, activityId, true)

            Result.success(Unit)
        } catch (e: Exception) {
            Log.e("Wevy", "Gagal simpan hasil emosi: ${e.message}", e)
            Result.failure(e)
        }
    }

    // function to get wevy done
    override fun getWevyProgress(wevyId: String): Flow<WevyProgress> {
        return preferences.getWevyProgress(wevyId)
    }

    // function to get emotion
    override suspend fun getEmotion(
        userId: String,
        wevyId: String,
        activityId: String
    ): EmotionDetectionResponse? {
        return try {
            val snapshot = database.child("users")
                .child(userId)
                .child("wevy")
                .child(wevyId)
                .child("activities")
                .child(activityId)
                .get()
                .await()
            if (snapshot.exists()) {
                val label = snapshot.child("label").getValue(String::class.java)
                val timestamp = snapshot.child("timestamp").getValue(Long::class.java)

                if (label != null && timestamp != null) {
                    EmotionDetectionResponse(
                        score = 0.0,
                        label = label,
                        distribution = emptyMap()
                    )
                } else {
                    null
                }
            } else {
                null
            }
        } catch (e: Exception) {
            Log.e("Wevy", "Gagal mengambil hasil emosi: ${e.message}", e)
            null
        }
    }
}
