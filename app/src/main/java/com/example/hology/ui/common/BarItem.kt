package com.example.hology.ui.common

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.hology.domain.model.ChartData

@Composable
fun BarItem(
    data: ChartData,
    modifier: Modifier = Modifier
) {
    val normalizedValue = data.value.coerceIn(1f, 4f)

    val maxHeight = 300.dp
    val barHeight = (normalizedValue / 4f) * maxHeight.value

    Box(
        modifier = modifier
            .width(30.dp)
            .height(barHeight.dp)
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        Color(0xFFFF8762),
                        Color(0xFFF5F5F5)
                    )
                ),
                shape = RoundedCornerShape(topStart = 4.dp, topEnd = 4.dp)
            )
    )
}
