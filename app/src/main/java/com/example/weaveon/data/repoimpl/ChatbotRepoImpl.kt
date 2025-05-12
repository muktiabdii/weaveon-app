package com.example.weaveon.data.repoimpl

import android.content.Context
import android.util.Log
import com.example.weaveon.R
import com.example.weaveon.data.model.Content
import com.example.weaveon.data.model.GeminiRequest
import com.example.weaveon.data.model.Part
import com.example.weaveon.data.source.GeminiService
import com.example.weaveon.domain.repository.ChatbotRepository

class ChatbotRepoImpl(private val context: Context, private val geminiService: GeminiService) : ChatbotRepository {

    override suspend fun getChatbotReply(prompt: String): String {
        val apiKey = context.getString(R.string.gemini_api_key)
        // Perbaiki URL dengan menambahkan https://
        val url = "https://generativelanguage.googleapis.com/v1beta/models/gemini-2.0-flash:generateContent?key=$apiKey"

        // Log URL dan API Key
        Log.d("GeminiAPI", "Request URL: $url")
        Log.d("GeminiAPI", "API Key: $apiKey")

        // Buat objek GeminiRequest dengan benar menggunakan prompt
        val request = GeminiRequest(
            contents = listOf(
                Content(parts = listOf(Part(text = prompt)))
            )
        )

        return try {
            // Lakukan request ke Gemini API dengan benar
            val response = geminiService.sendPrompt(url, request)  // Response langsung berupa GeminiResponse

            // Cek apakah response body ada dan kembalikan hasilnya
            response.candidates?.get(0)?.content?.parts?.get(0)?.text.orEmpty()
        } catch (e: Exception) {
            "Exception: ${e.message}"
        }
    }
}
