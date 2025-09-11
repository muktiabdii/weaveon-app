package com.example.hology.data.repository

import android.content.Context
import android.util.Log
import com.example.hology.data.model.EmotionDetectionResponse
import com.example.hology.data.remote.api.ApiEmotionDetectionService
import com.example.hology.domain.repository.WevyRepository
import okhttp3.MultipartBody

class WevyRepositoryImpl(private val context: Context) : WevyRepository {

    override suspend fun detectEmotion(file: MultipartBody.Part): EmotionDetectionResponse? {
        return try {
            Log.d("Wevy", "Mulai upload file: ${file.body.contentType()} | ${file.headers}")

            val response = ApiEmotionDetectionService.instance.detectEmotion(file)
            Log.d("Wevy", "Request URL = ${response.raw().request.url}")
            Log.d("Wevy", "Response code: ${response.code()}")
            Log.d("Wevy", "Response body: ${response.body()}")

            if (response.isSuccessful) {
                response.body()
            } else {
                Log.e("Wevy", "Request gagal: ${response.errorBody()?.string()}")
                null
            }
        } catch (e: Exception) {
            Log.e("Wevy", "Exception waktu upload: ${e.message}", e)
            null
        }
    }
}
