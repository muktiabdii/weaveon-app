package com.example.weaveon.data.model

data class FormCategory(
    val categoryId: String,
    val questionId: String,
    val question: String,
    val relatedKeywords: List<String>
)

