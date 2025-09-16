package com.example.hology.ui.home

import androidx.compose.ui.graphics.Brush
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import androidx.navigation.NavController
import com.example.hology.R
import com.example.hology.cache.UserData
import com.example.hology.cache.exerciseList
import com.example.hology.ui.common.BarChart
import com.example.hology.ui.common.ExerciseCarousel
import com.example.hology.ui.common.FeatureCard
import com.example.hology.ui.exercise.ExerciseViewModel
import com.example.hology.ui.report.GraphicState
import com.example.hology.ui.report.ReportViewModel
import com.example.hology.ui.theme.NeutralBlack
import com.example.hology.ui.theme.NeutralWhite
import com.example.hology.ui.theme.Primary03
import com.example.hology.ui.theme.Primary09
import com.example.hology.ui.theme.Secondary07
import com.example.hology.ui.theme.Secondary09

@Composable
fun HomeScreen(
    navController: NavController,
    exerciseViewModel: ExerciseViewModel,
    reportViewModel: ReportViewModel
) {
    val scrollState = rememberScrollState()

    val graphicState by reportViewModel.graphicState.collectAsState()
    val history by exerciseViewModel.history.collectAsState()
    val user = UserData
    val exercises = exerciseList

    LaunchedEffect(Unit) {
        exerciseViewModel.loadExerciseHistory(user.uid, exercises)
        reportViewModel.getCategoryChartData(user.uid)
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(NeutralWhite)
            .verticalScroll(scrollState)
    ) {
        Image(
            painter = painterResource(id = R.drawable.header_home_screen),
            contentDescription = null,
            modifier = Modifier.fillMaxWidth(),
            contentScale = ContentScale.FillBounds
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            // Header
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 15.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column {
                    Text(
                        text = "Hi, Bunda!",
                        color = Secondary09,
                        fontSize = 22.sp,
                        fontFamily = FontFamily(Font(R.font.poppins_semibold))
                    )
                    Text(
                        text = "Temani tumbuh si kecil, yuk!",
                        color = Secondary07,
                        fontSize = 14.sp,
                        fontFamily = FontFamily(Font(R.font.poppins_medium))
                    )
                }

                // Profile picture
                Box(
                    modifier = Modifier
                        .size(50.dp)
                        .clip(CircleShape)
                        .background(NeutralBlack),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = Icons.Default.Person,
                        contentDescription = "Profile Picture",
                        tint = Color.White,
                        modifier = Modifier.size(30.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.height(5.dp))

            // Card info
            Box(modifier = Modifier.fillMaxWidth()) {
                Image(
                    painter = painterResource(id = R.drawable.bulp_3d),
                    contentDescription = null,
                    modifier = Modifier
                        .size(110.dp)
                        .offset(x = (-12).dp, y = 48.dp)
                        .zIndex(1f)
                )

                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 32.dp),
                    colors = CardDefaults.cardColors(containerColor = Color.Transparent)
                ) {
                    Box(modifier = Modifier.fillMaxWidth()) {
                        Image(
                            painter = painterResource(id = R.drawable.card_home_screen),
                            contentDescription = null,
                            modifier = Modifier.fillMaxWidth(),
                            contentScale = ContentScale.Crop
                        )

                        Row {
                            Spacer(modifier = Modifier.width(110.dp))
                            Column(
                                horizontalAlignment = Alignment.CenterHorizontally,
                                modifier = Modifier.padding(top = 20.dp, end = 25.dp)
                            ) {
                                Text(
                                    text = "Tahukah kamu?",
                                    fontFamily = FontFamily(Font(R.font.poppins_semibold)),
                                    fontSize = 16.sp,
                                    color = Primary09,
                                    textAlign = TextAlign.Center
                                )
                                Spacer(modifier = Modifier.height(14.dp))
                                Text(
                                    text = "Anak autis cenderung lebih mudah memahami gambar dibandingkan kata-kata.",
                                    textAlign = TextAlign.Center,
                                    lineHeight = 15.sp,
                                    fontSize = 13.sp,
                                    fontFamily = FontFamily(Font(R.font.poppins_medium)),
                                    color = Primary09
                                )
                            }
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(35.dp))

            // Feature section
            Text(
                text = "Fitur kami",
                fontFamily = FontFamily(Font(R.font.poppins_semibold)),
                fontSize = 18.sp,
                color = Primary09
            )

            Spacer(modifier = Modifier.height(16.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                FeatureCard(
                    title = "Wevy",
                    description = "Ungkap potensi anak lewat rekaman aktivitas",
                    icon = R.drawable.wevy_home,
                    onClick = { navController.navigate("wevy") }
                )
                Spacer(modifier = Modifier.width(4.dp))
                FeatureCard(
                    title = "Exercise",
                    description = "Kumpulan permainan untuk belajar sambil bermain",
                    icon = R.drawable.brain_3d_2,
                    onClick = { navController.navigate("exercise") }
                )
            }

            Spacer(modifier = Modifier.height(35.dp))

            // Progress section
            Text(
                text = "Progress Statistik",
                fontFamily = FontFamily(Font(R.font.poppins_semibold)),
                fontSize = 18.sp,
                color = Primary09
            )

            Spacer(modifier = Modifier.height(16.dp))

            when (graphicState) {
                is GraphicState.Loading -> {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(200.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        CircularProgressIndicator(color = Primary03, strokeWidth = 5.dp)
                    }
                }

                is GraphicState.Success -> {
                    val chartData = (graphicState as GraphicState.Success).data
                    if (chartData.isEmpty()) {
                        Text(
                            text = "Belum ada aktivitas",
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(24.dp),
                            textAlign = TextAlign.Center,
                            color = Color.Gray,
                            fontFamily = FontFamily(Font(R.font.poppins_regular)),
                            fontSize = 14.sp
                        )
                    } else {
                        BarChart(
                            modifier = Modifier.fillMaxWidth(),
                            data = chartData
                        )
                    }
                }

                is GraphicState.Error -> {
                    Text(
                        text = (graphicState as GraphicState.Error).message,
                        color = Color.Red,
                        modifier = Modifier.padding(24.dp)
                    )
                }

                GraphicState.Idle -> {}
            }

            Spacer(modifier = Modifier.height(35.dp))

            // Exercise carousel
            ExerciseCarousel(
                exerciseItems = history,
                onSeeAllClick = { navController.navigate("jejak-exercise") }
            )

            Spacer(modifier = Modifier.height(35.dp))
        }
    }
}
