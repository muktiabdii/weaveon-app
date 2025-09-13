package com.example.hology.ui.common

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.hology.R
import com.example.hology.domain.model.ChartData
import com.example.hology.ui.theme.NeutralBlack
import com.example.hology.ui.theme.NeutralWhite

@Composable
fun BarChart(
    modifier: Modifier = Modifier,
    data: List<ChartData> = defaultChartData()
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp)
            .height(450.dp),
        colors = CardDefaults.cardColors(
            containerColor = NeutralWhite
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 8.dp
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(24.dp)
        ) {

            // chart content
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(300.dp)
            ) {
                // grid lines
                val dash = PathEffect.dashPathEffect(floatArrayOf(30f, 12f), 0f) 
                listOf(0.dp, 75.dp, 150.dp, 225.dp, 300.dp).forEach { y ->
                    Canvas(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(1.dp)
                            .offset(y = y)
                    ) {
                        drawLine(
                            color = Color.Gray.copy(alpha = 0.3f),
                            start = androidx.compose.ui.geometry.Offset(0f, 0f),
                            end = androidx.compose.ui.geometry.Offset(size.width, 0f),
                            strokeWidth = 2f,
                            pathEffect = dash
                        )
                    }
                }

                Row(
                    modifier = Modifier.fillMaxSize(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {

                    // emoji indicators
                    Box(
                        modifier = Modifier
                            .fillMaxHeight()
                            .wrapContentWidth(Alignment.End)
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.ic_very_happy),
                            contentDescription = "Very Happy",
                            modifier = Modifier
                                .size(24.dp)
                                .align(Alignment.TopCenter)
                        )

                        Image(
                            painter = painterResource(id = R.drawable.ic_happy),
                            contentDescription = "Happy",
                            modifier = Modifier
                                .size(24.dp)
                                .align(Alignment.TopCenter)
                                .offset(y = 75.dp)
                        )

                        Image(
                            painter = painterResource(id = R.drawable.ic_unhappy),
                            contentDescription = "Unhappy",
                            modifier = Modifier
                                .size(24.dp)
                                .align(Alignment.TopCenter)
                                .offset(y = 150.dp)
                        )

                        Image(
                            painter = painterResource(id = R.drawable.ic_very_unhappy),
                            contentDescription = "Very Unhappy",
                            modifier = Modifier
                                .size(24.dp)
                                .align(Alignment.TopCenter)
                                .offset(y = 225.dp)
                        )
                    }

                    // bar chart area
                    Row(
                        modifier = Modifier
                            .weight(1f)
                            .fillMaxHeight()
                            .padding(start = 30.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.Bottom
                    ) {
                        data.forEach { chartItem ->
                            BarItem(data = chartItem)
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(20.dp))

            // bottom labels
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 30.dp, top = 40.dp),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                data.forEach { chartItem ->
                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .fillMaxHeight(),
                        contentAlignment = Alignment.TopCenter
                    ) {
                        Text(
                            text = chartItem.label,
                            fontSize = 10.sp,
                            color = NeutralBlack,
                            fontFamily = FontFamily(Font(R.font.poppins_regular)),
                            textAlign = TextAlign.End,
                            maxLines = 1,
                            overflow = TextOverflow.Visible,
                            softWrap = false,
                            modifier = Modifier
                                .rotate(-60f)
                                .align(Alignment.TopEnd)
                        )
                    }
                }
            }
        }
    }
}

private fun defaultChartData(): List<ChartData> {
    return listOf(
        ChartData("Logika & Pola", 25f),
        ChartData("Seni & Visual", 50f),
        ChartData("Verbal", 50f),
        ChartData("Sosial & Imajinasi", 25f),
        ChartData("Musik & Auditori", 85f),
        ChartData("Logika & Pola", 75f)
    )
}
