package com.example.hology.domain.model

data class ExerciseHistoryItem(
    val exerciseId: String,
    val activityId: String,
    val imageUrl: String,
    val timestamp: Long
)

data class ExerciseHistoryUi(
    val exerciseTitle: String,
    val activityTitle: String,
    val imageUrl: String,
    val date: String
)

