package com.example.hology.domain.model

data class ExerciseProgress(
    val exerciseId: String,
    val activities: Map<String, Boolean>
)
