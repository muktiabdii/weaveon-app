package com.example.weaveon.data.source

import com.example.weaveon.data.model.GeminiRequest
import com.example.weaveon.data.model.GeminiResponse
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.Query

object GeminiService {

    // Interface GeminiService langsung di dalam object
    interface Service {
        @POST("v1beta/models/gemini-2.0-flash:generateContent")
        suspend fun sendPrompt(
            @Query("key") apiKey: String,
            @Body request: GeminiRequest
        ): GeminiResponse
    }

    // Inisialisasi Retrofit dan Service
    private val retrofit = Retrofit.Builder()
        .baseUrl("https://generativelanguage.googleapis.com/") // base URL tetap dibutuhkan
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val service: Service = retrofit.create(Service::class.java)
}
