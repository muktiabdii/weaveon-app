package com.example.weaveon.domain.repository

import com.example.weaveon.domain.model.ChatMessageDomain

interface ChatbotRepository {
    suspend fun getChatbotReply(prompt: String): String
    suspend fun getSavedChats(): List<ChatMessageDomain>
}
