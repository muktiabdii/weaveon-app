package com.example.hology.domain.usecase

import com.example.hology.cache.conclusionList
import com.example.hology.data.model.EmotionDetectionResponse
import com.example.hology.domain.model.Category
import com.example.hology.domain.model.ChartData
import com.example.hology.domain.model.Conclusion
import com.example.hology.domain.model.WevyProgress
import com.example.hology.domain.repository.WevyRepository
import kotlinx.coroutines.flow.Flow
import okhttp3.MultipartBody
import kotlin.math.round

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

    // function to generate conclusion
    suspend fun generateReport(
        userId: String,
        allCategories: List<Category>,
    ): Pair<String, String> {

        // get category chart data
        val chartData = repository.getCategoryChartData(userId)
        val conclusionTemplates = conclusionList

        if (chartData.isEmpty()) {
            return Pair("Belum ada data aktivitas yang bisa dianalisis.", "")
        }

        // sort category chart data by value
        val sorted = chartData.sortedByDescending { it.value }
        val topCategories = sorted.take(3)

        // determine conclusion based on top categories
        val conclusion: String = when (topCategories.size) {
            1 -> {
                val main = topCategories[0]
                conclusionTemplates[0].description
                    .replace("[nama kategori]", main.label)
                    .replace("[sangat senang/senang/tidak senang/sangat tidak senang]", main.value.toExpression())
            }
            2 -> {
                val main = topCategories[0]
                val second = topCategories[1]
                conclusionTemplates[1].description
                    .replace("[kategori 1]", main.label)
                    .replace("[kategori 2]", second.label)
                    .replace("[kategori utama]", main.label)
                    .replace("[sangat senang/senang/tidak senang/sangat tidak senang]", main.value.toExpression())
            }
            else -> {
                val main = topCategories[0]
                conclusionTemplates[2].description
                    .replace("[kategori utama]", main.label)
                    .replace("[sangat senang/senang/tidak senang/sangat tidak senang]", main.value.toExpression())
            }
        }

        // get category id
        val categoryId = topCategories.firstOrNull()?.let { top ->
            allCategories.find { it.title == top.label }?.id ?: ""
        } ?: ""

        return Pair(conclusion, categoryId)
    }

    // helper function to convert float to expression
    private fun Float.toExpression(): String {
        return when (round(this).toInt()) {
            4 -> "Sangat senang"
            3 -> "Senang"
            2 -> "Kurang senang"
            1 -> "Sangat tidak senang"
            else -> "Tidak diketahui"
        }
    }

}
