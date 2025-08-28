package com.example.hology.ui.exercise

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.hology.R
import com.example.hology.cache.exerciseList
import com.example.hology.ui.common.ExerciseTicketCard
import com.example.hology.ui.common.TopNavbar
import com.example.hology.ui.theme.NeutralWhite
import com.example.hology.ui.theme.Primary00
import com.example.hology.ui.theme.Primary08
import com.example.hology.ui.theme.Secondary09

@Composable
fun ExerciseDetailScreen(
    navController: NavController,
    exerciseId: String
) {

    val exercise = exerciseList.find { it.id == exerciseId }

    val exerciseImage = mapOf(
        "Komunikasi" to R.drawable.foto_exercise,
        "Interaksi Sosial" to R.drawable.foto_exercise_2,
        "Perilaku Berulang" to R.drawable.foto_exercise_3,
        "Sensorik & Motorik" to R.drawable.foto_exercise_4,
        "Kognitif" to R.drawable.foto_exercise_5,
        "Regulasi Emosi" to R.drawable.foto_exercise
    )

    val imageResource = exerciseImage[exercise?.title] ?: R.drawable.foto_exercise

    Scaffold(
        topBar = {
            TopNavbar(
                title = "Exercise",
                navController = navController
            )
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(NeutralWhite)
                .padding(innerPadding)
        ) {
            Image(
                painter = painterResource(id = R.drawable.bg_5),
                contentDescription = null,
                modifier = Modifier.fillMaxWidth(),
                contentScale = ContentScale.Crop
            )

            LazyColumn(
                modifier = Modifier
                    .fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(16.dp),
                contentPadding = PaddingValues(bottom = 16.dp)
            ) {
                item {

                    // header
                    Image(
                        painter = painterResource(id = imageResource),
                        contentDescription = null,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(220.dp),
                        contentScale = ContentScale.Crop
                    )

                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 12.dp, start = 16.dp, end = 16.dp, bottom = 4.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Box(
                            modifier = Modifier
                                .background(Secondary09, RoundedCornerShape(20.dp))
                                .padding(horizontal = 24.dp, vertical = 8.dp)
                        ) {
                            Text(
                                text = exercise?.title ?: "",
                                style = MaterialTheme.typography.titleMedium.copy(
                                    color = Primary00,
                                    fontFamily = FontFamily(Font(R.font.poppins_semibold)),
                                    fontSize = 16.sp
                                )
                            )
                        }
                    }
                }

                item {

                    // description
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp),
                        verticalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        Text(
                            text = exercise?.description ?: "",
                            style = MaterialTheme.typography.bodyMedium.copy(
                                color = Secondary09,
                                fontFamily = FontFamily(Font(R.font.poppins_semibold)),
                                fontSize = 14.sp,
                                lineHeight = 20.sp,
                                textAlign = TextAlign.Center
                            )
                        )

                        Text(
                            text = "Pilih salah satu challenge di bawah ini dan ikuti di kecil menyelesaikan challenge tersebut bersama dengan parent/caregiver",
                            style = MaterialTheme.typography.bodySmall.copy(
                                color = Primary08,
                                fontFamily = FontFamily(Font(R.font.poppins_regular)),
                                fontSize = 13.sp,
                                textAlign = TextAlign.Center,
                                lineHeight = 18.sp
                            )
                        )
                    }
                }

                item {

                    // exercise cards
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp, vertical = 16.dp),
                        verticalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        exercise?.activities?.forEach { activity ->
                            ExerciseTicketCard(
                                number = activity.id,
                                title = activity.title,
                                description = activity.description,
                                onItemClick = {
                                    navController.navigate("exercise_activity/$exerciseId/${activity.id}")
                                }
                            )
                        }
                    }
                }
            }
        }
    }
}
