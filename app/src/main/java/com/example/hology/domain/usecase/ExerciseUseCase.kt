package com.example.hology.domain.usecase

import android.net.Uri
import com.example.hology.domain.model.CloudinaryResponse
import com.example.hology.domain.repository.ExerciseRepository

class ExerciseUseCase(private val repository: ExerciseRepository) {

    // function upload image
    suspend operator fun invoke(uri: Uri): CloudinaryResponse? {
        return repository.uploadImage(uri)
    }
}