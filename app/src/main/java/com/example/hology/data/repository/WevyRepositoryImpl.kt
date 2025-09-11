package com.example.hology.data.repository

import android.util.Log
import com.example.hology.data.model.EmotionDetectionResponse
import com.example.hology.data.remote.api.ApiEmotionDetectionService
import com.example.hology.data.remote.firebase.FirebaseProvider
import com.example.hology.domain.repository.WevyRepository
import kotlinx.coroutines.tasks.await
import okhttp3.MultipartBody

class WevyRepositoryImpl() : WevyRepository {

    private val database = FirebaseProvider.database

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

            Result.success(Unit)
        } catch (e: Exception) {
            Log.e("Wevy", "Gagal simpan hasil emosi: ${e.message}", e)
            Result.failure(e)
        }
    }

}
