package com.example.hology.domain.model

data class WevyActivity(
    val id: String,
    val title: String,
    val description: String,
    val goal: String,
    val steps: List<String>,
    val tips: String
)

data class Wevy(
    val id: String,
    val title: String,
    val description: String,
    val activities: List<WevyActivity>
)
