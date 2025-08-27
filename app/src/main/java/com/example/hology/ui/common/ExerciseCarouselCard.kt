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
import com.example.hology.R
import com.example.hology.domain.model.ExerciseItem
import com.example.hology.ui.theme.Secondary09

@Composable
fun ExerciseCarouselCard(exerciseItem: ExerciseItem, isActive: Boolean) {
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
            Image(
                painter = painterResource(id = exerciseItem.imageResId),
                contentDescription = "Children playing",
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color(0xFFFFBFA9))
                    .offset(y = (-5).dp),
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
                            text = exerciseItem.title,
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