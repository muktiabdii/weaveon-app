package com.example.weaveon.data.model

data class ChatMessageData(
    val message: String? = null,
    val sender: String? = null,
    val timestamp: Long? = null
) {
    constructor() : this(null, null, null)
}
