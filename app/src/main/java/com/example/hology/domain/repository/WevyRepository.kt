package com.example.hology.domain.repository

import com.example.hology.data.model.EmotionDetectionResponse
import okhttp3.MultipartBody

interface WevyRepository {
    suspend fun detectEmotion(file: MultipartBody.Part): EmotionDetectionResponse?
}
