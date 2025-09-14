package com.example.hology.data.repository

import android.content.Context
import android.util.Log
import com.example.hology.cache.reportCategoryList
import com.example.hology.cache.wevyList
import com.example.hology.data.datastore.WevyPreferencesManager
import com.example.hology.data.model.EmotionDetectionResponse
import com.example.hology.data.remote.api.ApiEmotionDetectionService
import com.example.hology.data.remote.firebase.FirebaseProvider
import com.example.hology.domain.model.ChartData
import com.example.hology.domain.model.WevyHistoryItem
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

    // function to get category chart data
    override suspend fun getCategoryChartData(userId: String): List<ChartData> {
        return try {
            val snapshot = database.child("users")
                .child(userId)
                .child("wevy")
                .get()
                .await()

            if (!snapshot.exists()) return emptyList()

            val chartDataList = mutableListOf<ChartData>()

            for (wevySnapshot in snapshot.children) {
                val wevyId = wevySnapshot.key ?: continue
                val activitiesSnapshot = wevySnapshot.child("activities")

                val scores = mutableListOf<Int>()

                // loop all of activities
                for (activitySnapshot in activitiesSnapshot.children) {
                    val label = activitySnapshot.child("label").getValue(String::class.java)
                    val score = emotionScore[label?.lowercase()] ?: continue
                    scores.add(score)
                }

                // calculate average
                if (scores.isNotEmpty()) {
                    val avg = scores.average().toFloat()
                    val categoryTitle = wevyList.find { it.id == wevyId }?.title ?: "Kategori $wevyId"

                    chartDataList.add(
                        ChartData(
                            label = categoryTitle,
                            value = avg
                        )
                    )
                }
            }

            Log.d("Wevy", "Data chart: $chartDataList")
            chartDataList
        } catch (e: Exception) {
            Log.e("Wevy", "Gagal ambil data chart: ${e.message}", e)
            emptyList()
        }
    }

    // mapping emotion score
    private val emotionScore = mapOf(
        "sangat tidak senang" to 1,
        "kurang senang" to 2,
        "cukup senang" to 3,
        "sangat senang" to 4
    )

    // function to get wevy history
    override suspend fun getWevyHistory(userId: String): Result<List<WevyHistoryItem>> {
        return try {
            val snapshot = database.child("users")
                .child(userId)
                .child("wevy")
                .get()
                .await()
            if (!snapshot.exists()) return Result.failure(Exception("No data found"))
            val history = mutableListOf<WevyHistoryItem>()
            for (wevySnapshot in snapshot.children) {
                val wevyId = wevySnapshot.key ?: continue
                val activitiesSnapshot = wevySnapshot.child("activities")
                for (activitySnapshot in activitiesSnapshot.children) {
                    val activityId = activitySnapshot.key ?: continue
                    history.add(
                        WevyHistoryItem(
                            wevyId = wevyId,
                            activityId = activityId
                        )
                    )
                }
            }
            Result.success(history)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}
