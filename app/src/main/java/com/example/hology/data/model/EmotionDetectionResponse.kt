package com.example.hology.data.model

data class EmotionDetectionResponse(
    val score: Double,
    val label: String,
    val distribution: Map<String, Double>
)
