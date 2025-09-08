package com.example.hology.data.repository

import android.content.Context
import android.net.Uri
import com.example.hology.data.datastore.ExercisePreferencesManager
import com.example.hology.data.remote.api.CloudinaryConfig
import com.example.hology.data.remote.firebase.FirebaseProvider
import com.example.hology.domain.model.CloudinaryResponse
import com.example.hology.domain.model.ExerciseHistoryItem
import com.example.hology.domain.model.ExerciseProgress
import com.example.hology.domain.repository.ExerciseRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.tasks.await
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody

class ExerciseRepositoryImpl(
    private val service: CloudinaryConfig,
    private val context: Context,
    private val preferences: ExercisePreferencesManager
) : ExerciseRepository {

    private val database = FirebaseProvider.database

    // function upload image
    override suspend fun uploadImage(uri: Uri): CloudinaryResponse? {
        val inputStream = context.contentResolver.openInputStream(uri)
        val bytes = inputStream?.readBytes() ?: return null

        val requestFile = RequestBody.create("image/*".toMediaTypeOrNull(), bytes)
        val body = MultipartBody.Part.createFormData("file", "image.jpg", requestFile)
        val preset = RequestBody.create("text/plain".toMediaTypeOrNull(), "ml_default")

        val response = service.uploadImage(body, preset)
        return if (response.isSuccessful) {
            response.body()?.let {
                CloudinaryResponse(url = it.url, secureUrl = it.secureUrl)
            }
        } else null
    }

    // function save image
    override suspend fun saveImageUrl(
        userId: String,
        exerciseId: String,
        activityId: String,
        imageUrl: String
    ): Result<Unit> {
        return try{
            val data = mapOf(
                "imageUrl" to imageUrl,
                "timestamp" to System.currentTimeMillis()
            )

            database.child("users")
                .child(userId)
                .child("exercises")
                .child(exerciseId)
                .child("activities")
                .child(activityId)
                .updateChildren(data)
                .await()

            preferences.setExerciseDone(exerciseId, activityId, true)

            Result.success(Unit)
        } catch (e: Exception){
            Result.failure(e)
        }
    }

    // function get exercise done
    override fun getExerciseProgress(exerciseId: String): Flow<ExerciseProgress> {
        return preferences.getExerciseProgress(exerciseId)
    }

    // function get exercise history
    override suspend fun getExerciseHistory(userId: String): Result<List<ExerciseHistoryItem>> {
        return try {
            val snapshot = database.child("users")
                .child(userId)
                .child("exercises")
                .get()
                .await()

            val history = mutableListOf<ExerciseHistoryItem>()

            for (exerciseSnapshot in snapshot.children) {
                val exerciseId = exerciseSnapshot.key ?: continue
                val activitiesSnapshot = exerciseSnapshot.child("activities")
                for (activitySnapshot in activitiesSnapshot.children) {
                    val activityId = activitySnapshot.key ?: continue
                    val imageUrl = activitySnapshot.child("imageUrl").getValue(String::class.java) ?: ""
                    val timestamp = activitySnapshot.child("timestamp").getValue(Long::class.java) ?: 0L

                    history.add(
                        ExerciseHistoryItem(
                            exerciseId = exerciseId,
                            activityId = activityId,
                            imageUrl = imageUrl,
                            timestamp = timestamp
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