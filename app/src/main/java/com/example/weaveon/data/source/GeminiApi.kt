package com.example.weaveon.data.source

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object GeminiApi {
    fun createService(): GeminiService {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://generativelanguage.googleapis.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        return retrofit.create(GeminiService::class.java)
    }
}
