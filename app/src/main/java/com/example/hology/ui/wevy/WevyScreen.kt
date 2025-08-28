package com.example.hology.ui.wevy

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.hology.R
import com.example.hology.ui.common.TopNavbar
import com.example.hology.ui.theme.NeutralWhite
import com.example.hology.ui.theme.Primary08
import com.example.hology.ui.theme.Secondary09
import com.example.hology.ui.common.ExerciseCard
import com.example.hology.ui.common.WevyCard
import com.example.hology.ui.theme.NeutralBlack

@Composable
fun WevyScreen(
    navController: NavController
) {
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
                        verticalArrangement = Arrangement.spacedBy(11.dp)
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.wevy_wevy),
                            contentDescription = null,
                            modifier = Modifier.size(140.dp)
                        )

                        // description text
                        Text(
                            text = "Ayo temukan potensi si kecil! Mulailah dengan memilih kategori aktivitas untuk direkam dan dianalisis.",
                            style = MaterialTheme.typography.bodyMedium.copy(
                                color = Secondary09,
                                fontFamily = FontFamily(Font(R.font.poppins_semibold)),
                                textAlign = TextAlign.Center,
                                fontSize = 16.sp,
                                lineHeight = 20.sp
                            ),
                            modifier = Modifier.padding(horizontal = 16.dp)
                        )

                        Text(
                            text = "Jelajahi semua kategori! Dengan mencoba beragam aktivitas, Anda akan mendapatkan gambaran perbandingan minat anak yang lebih kaya dan mendalam.",
                            style = MaterialTheme.typography.bodySmall.copy(
                                color = Primary08,
                                fontFamily = FontFamily(Font(R.font.poppins_regular)),
                                fontSize = 12.sp,
                                textAlign = TextAlign.Center,
                                lineHeight = 14.sp
                            ),
                            modifier = Modifier.padding(bottom = 16.dp, start = 16.dp, end = 16.dp)
                        )
                    }
                }

                item {

                    // explore card grid
                    Column(
                        verticalArrangement = Arrangement.spacedBy(16.dp),
                        modifier = Modifier.padding(horizontal = 16.dp)
                    ) {
                        WevyCard(text = "Logika & Pola")
                        WevyCard(text = "Seni & Visual")
                        WevyCard(text = "Verbal")
                        WevyCard(text = "Sosial & Imajinasi")
                        WevyCard(text = "Musik & Auditori")
                        WevyCard(text = "Motorik & Gerak")
                    }
                }
            }
        }
    }
}
