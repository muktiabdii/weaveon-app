package com.example.hology.ui.common

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import com.example.hology.R
import com.example.hology.ui.theme.Primary09
import com.example.hology.ui.theme.Secondary09

@Composable
fun FeatureCard(
    title: String,
    description: String,
    icon: Int,
    onClick: () -> Unit = {}
) {
    Box(
        modifier = Modifier
            .width(150.dp)
            .wrapContentHeight()
            .clickable(onClick = onClick)
    ) {

        // gambar
        Image(
            painter = painterResource(id = icon),
            contentDescription = "$title Icon",
            modifier = Modifier
                .size(85.dp)
                .align(Alignment.TopCenter)
                .offset(y = (-12).dp)
                .zIndex(1f)
        )

        Card(
            shape = RoundedCornerShape(12.dp),
            colors = CardDefaults.cardColors(
                containerColor = Color.Transparent
            ),
            modifier = Modifier
                .height(180.dp)
                .width(170.dp)
                .align(Alignment.TopCenter)
                .padding(top = 34.dp)
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
                    .padding(top = 36.dp, start = 12.dp, end = 12.dp, bottom = 12.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                // title
                Text(
                    text = title,
                    fontFamily = FontFamily(Font(R.font.poppins_semibold)),
                    fontSize = 14.sp,
                    color = Secondary09
                )

                Spacer(modifier = Modifier.height(6.dp))

                // description
                Text(
                    text = description,
                    fontSize = 12.sp,
                    fontFamily = FontFamily(Font(R.font.poppins_regular)),
                    textAlign = TextAlign.Center,
                    lineHeight = 14.sp,
                    color = Primary09
                )
            }
        }
    }
}