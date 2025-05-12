package com.example.weaveon.domain.repository

interface ChatbotRepository {
    suspend fun getChatbotReply(prompt: String): String
}
