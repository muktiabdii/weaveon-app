package com.example.hology.domain.usecase

import com.example.hology.data.model.EmotionDetectionResponse
import com.example.hology.domain.model.ChartData
import com.example.hology.domain.model.WevyProgress
import com.example.hology.domain.repository.WevyRepository
import kotlinx.coroutines.flow.Flow
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

    // function to get wevy done
    fun getWevyProgress(wevyId: String): Flow<WevyProgress> {
        return repository.getWevyProgress(wevyId)
    }

    // function to get emotion
    suspend fun getEmotion(userId: String, wevyId: String, activityId: String): EmotionDetectionResponse? {
        return repository.getEmotion(userId, wevyId, activityId)
    }

    // function to get category chart data
    suspend fun getCategoryChartData(userId: String): List<ChartData> {
        return repository.getCategoryChartData(userId)
    }
}
