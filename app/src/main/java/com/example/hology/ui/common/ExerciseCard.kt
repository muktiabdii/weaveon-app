package com.example.hology.ui.common

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.hology.R
import com.example.hology.ui.theme.Secondary09

@Composable
fun ExerciseCard(
    title: String,
    modifier: Modifier = Modifier,
    onItemClick: () -> Unit = {}
) {
    Card(
        modifier = modifier
            .height(120.dp)
            .shadow(
                elevation = 1.dp,
                shape = RoundedCornerShape(16.dp),
                ambientColor = Color.Gray.copy(alpha = 0.4f),
                spotColor = Color.Gray.copy(alpha = 0.4f)
            )
            .clickable { onItemClick() },
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.Transparent
        )
    ) {
        Box(
            modifier = Modifier.fillMaxSize()
        ) {

            // envelope background
            Image(
                painter = painterResource(id = R.drawable.envelope),
                contentDescription = null,
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.FillBounds
            )

            // title
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = 65.dp, start = 16.dp, end = 16.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = title,
                    style = MaterialTheme.typography.titleMedium.copy(
                        fontFamily = FontFamily(Font(R.font.poppins_semibold)),
                        fontSize = 15.sp,
                        color = Secondary09,
                        textAlign = TextAlign.Center,
                        lineHeight = 18.sp
                    )
                )
            }
        }
    }
}