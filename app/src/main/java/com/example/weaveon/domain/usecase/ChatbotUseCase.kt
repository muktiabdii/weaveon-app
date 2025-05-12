package com.example.weaveon.domain.usecase

import com.example.weaveon.domain.repository.ChatbotRepository

class ChatbotUseCase(private val repository: ChatbotRepository) {
    suspend operator fun invoke(prompt: String): String {
        return repository.getChatbotReply(prompt)
    }
}
