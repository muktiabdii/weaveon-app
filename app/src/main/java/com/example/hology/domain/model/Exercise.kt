package com.example.hology.domain.model

data class ExerciseActivity(
    val id: String,
    val title: String,
    val image: Int,
    val description: String,
    val goal: String,
    val tips: String
)

data class Exercise(
    val id: String,
    val title: String,
    val image: Int,
    val description: String,
    val activities: List<ExerciseActivity>
)
