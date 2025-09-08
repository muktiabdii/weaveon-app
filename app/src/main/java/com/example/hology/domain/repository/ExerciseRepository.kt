package com.example.hology.domain.repository

import android.net.Uri
import com.example.hology.domain.model.CloudinaryResponse
import com.example.hology.domain.model.ExerciseProgress
import kotlinx.coroutines.flow.Flow

interface ExerciseRepository {
    suspend fun uploadImage(uri: Uri): CloudinaryResponse?
    suspend fun saveImageUrl(userId: String, exerciseId: String, activityId: String, imageUrl: String): Result<Unit>
    fun getExerciseProgress(exerciseId: String): Flow<ExerciseProgress>
}