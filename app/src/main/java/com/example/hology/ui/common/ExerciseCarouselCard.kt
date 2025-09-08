package com.example.hology.ui.common

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.hology.R
import com.example.hology.domain.model.ExerciseHistoryUi
import com.example.hology.ui.theme.Secondary09

@Composable
fun ExerciseCarouselCard(exerciseItem: ExerciseHistoryUi, isActive: Boolean) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp),
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        colors = CardDefaults.cardColors(containerColor = Color.Transparent),
    ) {
        Box(
            modifier = Modifier.fillMaxSize()
        ) {

            // image
            AsyncImage(
                model = exerciseItem.imageUrl,
                contentDescription = "Exercise Image",
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )

            // label background
            Box(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .fillMaxWidth()
                    .height(70.dp)
                    .background(
                        color = Color(0xFFFFBFA9),
                    )
                    .padding(horizontal = 12.dp, vertical = 8.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column(
                        modifier = Modifier.weight(1f)
                    ) {

                        // title
                        Text(
                            text = exerciseItem.activityTitle,
                            fontSize = 13.sp,
                            fontFamily = FontFamily(Font(R.font.poppins_semibold)),
                            lineHeight = 16.sp,
                            color = Secondary09
                        )

                        // date
                        Text(
                            text = exerciseItem.date,
                            fontSize = 12.sp,
                            color = Secondary09,
                            fontFamily = FontFamily(Font(R.font.poppins_regular))
                        )
                    }

                    // share button
                    IconButton(
                        onClick = { /* Share functionality */ },
                        modifier = Modifier
                            .size(32.dp)
                            .padding(start = 8.dp)
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_share),
                            contentDescription = "Share",
                            tint = Secondary09,
                            modifier = Modifier.size(20.dp)
                        )
                    }
                }
            }
        }
    }
}