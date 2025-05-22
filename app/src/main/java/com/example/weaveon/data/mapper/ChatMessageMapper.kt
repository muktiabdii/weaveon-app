package com.example.weaveon.data.mapper

import com.example.weaveon.data.model.ChatMessageData
import com.example.weaveon.domain.model.ChatMessageDomain

object ChatMessageMapper {
    fun ChatMessageData.toDomain(): ChatMessageDomain {
        return ChatMessageDomain(
            content = this.message ?: "",
            isOutgoing = this.sender == "user",
            isLoading = false
        )
    }
}
