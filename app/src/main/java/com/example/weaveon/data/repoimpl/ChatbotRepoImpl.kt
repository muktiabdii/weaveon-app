package com.example.weaveon.data.repoimpl

import android.content.Context
import com.example.weaveon.R
import com.example.weaveon.data.model.Content
import com.example.weaveon.data.model.GeminiRequest
import com.example.weaveon.data.model.Part
import com.example.weaveon.data.source.GeminiService
import com.example.weaveon.domain.repository.ChatbotRepository
import retrofit2.HttpException

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
            response.candidates?.get(0)?.content?.parts?.get(0)?.text
                ?: throw IllegalStateException("No valid response from API")
        } catch (e: HttpException) {
            when (e.code()) {
                424 -> throw Exception("HTTP 424: Failed Dependency - Check API quota or request format")
                else -> throw Exception("HTTP ${e.code()}: ${e.message()}")
            }
        } catch (e: Exception) {
            throw Exception("Error: ${e.message ?: "Unknown error"}")
        }
    }
}