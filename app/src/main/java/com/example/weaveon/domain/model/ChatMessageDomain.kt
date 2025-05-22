package com.example.weaveon.domain.model

data class ChatMessageDomain(
    val content: String,
    val isOutgoing: Boolean,
    val isLoading: Boolean = false
)
