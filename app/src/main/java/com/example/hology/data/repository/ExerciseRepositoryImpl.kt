package com.example.hology.data.repository

import android.content.Context
import android.net.Uri
import com.example.hology.data.remote.api.CloudinaryConfig
import com.example.hology.domain.model.CloudinaryResponse
import com.example.hology.domain.repository.ExerciseRepository
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody

class ExerciseRepositoryImpl(
    private val service: CloudinaryConfig,
    private val context: Context
) : ExerciseRepository {

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
}