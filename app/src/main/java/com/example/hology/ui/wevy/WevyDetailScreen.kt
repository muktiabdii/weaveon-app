package com.example.hology.ui.wevy

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
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
import com.example.hology.cache.wevyList
import com.example.hology.ui.common.TicketCard
import com.example.hology.ui.common.TopNavbar
import com.example.hology.ui.theme.NeutralWhite
import com.example.hology.ui.theme.Primary00
import com.example.hology.ui.theme.Primary08
import com.example.hology.ui.theme.Secondary09

@Composable
fun WevyDetailScreen(
    navController: NavController,
    wevyId: String
) {

    val wevy = wevyList.find { it.id == wevyId }

    val wevyImage = mapOf(
        "Logika & Pola" to R.drawable.foto_exercise,
        "Seni & Visual" to R.drawable.foto_exercise_2,
        "Verbal" to R.drawable.foto_exercise_3,
        "Sosial & Imajinasi" to R.drawable.foto_exercise_4,
        "Musik & Auditori" to R.drawable.foto_exercise_5,
        "Motorik & Gerak" to R.drawable.foto_exercise
    )

    val imageResource = wevyImage[wevy?.title] ?: R.drawable.foto_exercise

    Scaffold(
        topBar = {
            TopNavbar(
                title = "Wevy",
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
                                .background(
                                    brush = Brush.horizontalGradient(
                                        colors = listOf(
                                            Color(0xFFFFA686),
                                            Color(0xFFC5C784)
                                        )
                                    ),
                                    RoundedCornerShape(20.dp)
                                )
                                .padding(horizontal = 24.dp, vertical = 8.dp)
                        ) {
                            Text(
                                text = wevy?.title ?: "",
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
                            text = wevy?.description ?: "",
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

                    // wevy cards
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp, vertical = 16.dp),
                        verticalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        wevy?.activities?.forEach { activity ->
                            TicketCard(
                                number = activity.id,
                                title = activity.title,
                                description = activity.description,
                                onItemClick = { navController.navigate("wevy_activity") }
                            )
                        }
                    }
                }
            }
        }
    }
}
