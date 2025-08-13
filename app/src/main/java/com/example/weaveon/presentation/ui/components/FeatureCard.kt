package com.example.weaveon.presentation.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import com.example.weaveon.R
import com.example.weaveon.presentation.ui.theme.Primary09
import com.example.weaveon.presentation.ui.theme.Secondary09

@Composable
fun FeatureCard(
    title: String,
    description: String,
    icon: Int,
) {
    Box(
        modifier = Modifier
            .width(100.dp)
            .wrapContentHeight()
    ) {
        // Gambar keluar dari Card
        Image(
            painter = painterResource(id = icon),
            contentDescription = "$title Icon",
            modifier = Modifier
                .size(65.dp)
                .align(Alignment.TopCenter)
                .offset(y = (-8).dp)
                .zIndex(1f)
        )

        Card(
            shape = RoundedCornerShape(12.dp),
            colors = CardDefaults.cardColors(
                containerColor = Color.Transparent
            ),
            modifier = Modifier
                .height(180.dp)
                .width(125.dp)
                .align(Alignment.TopCenter)
                .padding(top = 28.dp)
                .background(
                    brush = Brush.verticalGradient(
                        colors = listOf(
                            Color(0xFFFFD9CC),
                            Color(0xFFFFBFA9)
                        )
                    ),
                    shape = RoundedCornerShape(12.dp)
                )
        ) {
            Column(
                modifier = Modifier
                    .padding(top = 28.dp, start = 8.dp, end = 8.dp, bottom = 12.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = title,
                    fontFamily = FontFamily(Font(R.font.poppins_semibold)),
                    fontSize = 14.sp,
                    color = Secondary09
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = description,
                    fontSize = 11.sp,
                    fontFamily = FontFamily(Font(R.font.poppins_regular)),
                    textAlign = TextAlign.Center,
                    lineHeight = 15.sp,
                    color = Primary09
                )
            }
        }
    }
}
