package com.example.hology.domain.usecase

import com.example.hology.data.model.EmotionDetectionResponse
import com.example.hology.domain.repository.WevyRepository
import okhttp3.MultipartBody

class WevyUseCase(private val repository: WevyRepository) {

    // function to detect emotion
    suspend fun detectEmotion(file: MultipartBody.Part): EmotionDetectionResponse? {
        return repository.detectEmotion(file)
    }

    // function to save emotion
    suspend fun saveEmotion(userId: String, wevyId: String, activityId: String, result: EmotionDetectionResponse): Result<Unit> {
        return repository.saveEmotion(userId, wevyId, activityId, result)
    }

    // function to get emotion
    suspend fun getEmotion(userId: String, wevyId: String, activityId: String): EmotionDetectionResponse? {
        return repository.getEmotion(userId, wevyId, activityId)
    }
}
