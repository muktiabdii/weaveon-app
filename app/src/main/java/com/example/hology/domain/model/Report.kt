package com.example.hology.domain.model

data class Conclusion(
    val id: String,
    val description: String
)

data class ReportTextState(
    val conclusion: String = "",
    val categoryId: String = ""
)

data class ChartData(
    val label: String,
    val value: Float
)

data class Category(
    val id: String,
    val title: String,
    val value: Float? = null,
    val description: String,
    val recommendedActivity: List<RecommendedActivity>
)

data class RecommendedActivity(
    val id: String,
    val title: String,
    val description: String,
    val image: Int
)