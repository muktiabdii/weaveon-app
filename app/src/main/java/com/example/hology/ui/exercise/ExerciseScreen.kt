package com.example.hology.ui.exercise

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.hology.R
import com.example.hology.ui.common.TopNavbar
import com.example.hology.ui.theme.NeutralWhite
import com.example.hology.ui.theme.Primary08
import com.example.hology.ui.theme.Secondary09
import com.example.hology.ui.common.ExerciseCard


@Composable
fun ExerciseScreen(
    navController: NavController
) {
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
        ){
            Image(
                painter = painterResource(id = R.drawable.bg_5),
                contentDescription = null,
                modifier = Modifier.fillMaxWidth(),
                contentScale = ContentScale.Crop
            )

            LazyColumn(
                modifier = Modifier
                    .fillMaxSize(),
                contentPadding = PaddingValues(16.dp),
                verticalArrangement = Arrangement.spacedBy(24.dp)
            ) {
                item {

                    // header
                    Column(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.spacedBy(10.dp)
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.lego_3d),
                            contentDescription = "Building blocks",
                            modifier = Modifier.size(140.dp)
                        )

                        // description text
                        Text(
                            text = "Exercise ini dirancang untuk membantu orangtua melatih kemampuan anak sesuai dengan kebutuhan perkembangannya.",
                            style = MaterialTheme.typography.bodyMedium.copy(
                                color = Secondary09,
                                fontFamily = FontFamily(Font(R.font.poppins_semibold)),
                                textAlign = TextAlign.Center,
                                fontSize = 14.sp,
                                lineHeight = 20.sp
                            ),
                            modifier = Modifier.padding(horizontal = 16.dp)
                        )

                        Text(
                            text = "Pilih area yang ingin diasah, lalu temukan berbagai aktivitas seru yang bisa dilakukan bersama si kecil di rumah.",
                            style = MaterialTheme.typography.bodySmall.copy(
                                color = Primary08,
                                fontFamily = FontFamily(Font(R.font.poppins_regular)),
                                fontSize = 12.sp,
                                textAlign = TextAlign.Center,
                                lineHeight = 18.sp
                            ),
                            modifier = Modifier.padding(bottom = 16.dp, start = 16.dp, end = 16.dp)
                        )
                    }
                }

                item {

                    // exercise categories grid
                    Column(
                        verticalArrangement = Arrangement.spacedBy(16.dp)
                    ) {

                        // first row
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.spacedBy(16.dp)
                        ) {
                            ExerciseCard(
                                title = "Komunikasi",
                                modifier = Modifier.weight(1f),
                                onItemClick = {  }
                            )

                            ExerciseCard(
                                title = "Interaksi Sosial",
                                modifier = Modifier.weight(1f),
                                onItemClick = {  }
                            )
                        }

                        // second row
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.spacedBy(16.dp)
                        ) {
                            ExerciseCard(
                                title = "Perilaku Berulang",
                                modifier = Modifier.weight(1f),
                                onItemClick = {  }
                            )

                            ExerciseCard(
                                title = "Kognitif",
                                modifier = Modifier.weight(1f),
                                onItemClick = {  }
                            )
                        }

                        // third row
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.spacedBy(16.dp)
                        ) {
                            ExerciseCard(
                                title = "Sensorik",
                                modifier = Modifier.weight(1f),
                                onItemClick = {  }
                            )

                            ExerciseCard(
                                title = "Regulasi Emosi",
                                modifier = Modifier.weight(1f),
                                onItemClick = {  }
                            )
                        }

                        Spacer (modifier = Modifier.height(25.dp))
                    }
                }
            }
        }
    }
}
