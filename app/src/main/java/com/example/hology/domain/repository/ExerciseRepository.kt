package com.example.hology.domain.repository

import android.net.Uri
import com.example.hology.domain.model.CloudinaryResponse

interface ExerciseRepository {
    suspend fun uploadImage(uri: Uri): CloudinaryResponse?
}