package com.example.hology.domain.repository

import com.example.hology.data.model.EmotionDetectionResponse
import com.example.hology.domain.model.ChartData
import com.example.hology.domain.model.WevyHistoryItem
import com.example.hology.domain.model.WevyProgress
import kotlinx.coroutines.flow.Flow
import okhttp3.MultipartBody

interface WevyRepository {
    suspend fun detectEmotion(file: MultipartBody.Part): EmotionDetectionResponse?
    suspend fun saveEmotion(userId: String, wevyId: String, activityId: String, result: EmotionDetectionResponse): Result<Unit>
    fun getWevyProgress(wevyId: String): Flow<WevyProgress>
    suspend fun getEmotion(userId: String, wevyId: String, activityId: String): EmotionDetectionResponse?
    suspend fun getCategoryChartData(userId: String): List<ChartData>
    suspend fun getWevyHistory(userId: String): Result<List<WevyHistoryItem>>
}
