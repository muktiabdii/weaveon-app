package com.example.hology.ui.common

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.hology.R

@Composable
fun OnBoardingImage(
    currentPage: Int,
    modifier: Modifier = Modifier
) {
    Image(
        painter = when (currentPage) {
            0 -> painterResource(id = R.drawable.logo)
            1 -> painterResource(id = R.drawable.wevy_boarding)
            2 -> painterResource(id = R.drawable.report_boarding)
            else -> painterResource(id = R.drawable.exercise_boarding)
        },
        contentDescription = null,
        modifier = modifier
            .size(200.dp),
        contentScale = ContentScale.Fit
    )
}
