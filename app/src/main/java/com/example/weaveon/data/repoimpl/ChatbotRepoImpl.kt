package com.example.weaveon.data.repoimpl

import android.content.Context
import com.example.weaveon.R
import com.example.weaveon.data.model.Content
import com.example.weaveon.data.model.GeminiRequest
import com.example.weaveon.data.model.Part
import com.example.weaveon.data.source.GeminiService
import com.example.weaveon.domain.repository.ChatbotRepository

class ChatbotRepoImpl(private val context: Context) : ChatbotRepository {

    override suspend fun getChatbotReply(prompt: String): String {
        val apiKey = context.getString(R.string.gemini_api_key)

        val request = GeminiRequest(
            contents = listOf(
                Content(parts = listOf(Part(text = prompt)))
            )
        )

        return try {
            val response = GeminiService.service.sendPrompt(apiKey, request)
            response.candidates?.get(0)?.content?.parts?.get(0)?.text.orEmpty()
        } catch (e: Exception) {
            "Exception: ${e.message}"
        }
    }
}
