package com.example.weaveon.data.source

import com.example.weaveon.data.model.GeminiRequest
import com.example.weaveon.data.model.GeminiResponse
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.Url

interface GeminiService {
    @POST
    suspend fun sendPrompt(
        @Url url: String,  // Terima URL sebagai parameter
        @Body request: GeminiRequest
    ): GeminiResponse
}
