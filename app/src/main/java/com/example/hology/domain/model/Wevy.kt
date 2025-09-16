package com.example.hology.domain.model

import androidx.compose.ui.graphics.Color

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
    val image: Int,
    val description: String,
    val activities: List<WevyActivity>
)

data class WevyResult(
    val label: String,
    val color: Color,
    val activeIcon: Int,
    val inactiveIcon: Int,
    val description: String
)

