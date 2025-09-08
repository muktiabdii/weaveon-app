package com.example.hology.domain.usecase

import android.net.Uri
import com.example.hology.domain.model.CloudinaryResponse
import com.example.hology.domain.model.ExerciseHistoryItem
import com.example.hology.domain.model.ExerciseProgress
import com.example.hology.domain.repository.ExerciseRepository
import kotlinx.coroutines.flow.Flow

class ExerciseUseCase(private val repository: ExerciseRepository) {

    // function upload image
    suspend operator fun invoke(uri: Uri): CloudinaryResponse? {
        return repository.uploadImage(uri)
    }

    // function save image
    suspend fun saveImageUrl(
        userId: String,
        exerciseId: String,
        activityId: String,
        imageUrl: String
    ): Result<Unit> {
        return repository.saveImageUrl(userId, exerciseId, activityId, imageUrl)
    }

    // function get exercise done
    fun getExerciseProgress(exerciseId: String): Flow<ExerciseProgress> {
        return repository.getExerciseProgress(exerciseId)
    }

    // function get exercise history
    suspend fun getExerciseHistory(userId: String): Result<List<ExerciseHistoryItem>> {
        return repository.getExerciseHistory(userId)
    }
}