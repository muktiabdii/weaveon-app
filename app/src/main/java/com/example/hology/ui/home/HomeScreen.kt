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
import com.example.hology.domain.model.ExerciseItem
import com.example.hology.ui.common.ExerciseCarousel
import com.example.hology.ui.common.FeatureCard
import com.example.hology.ui.theme.NeutralBlack
import com.example.hology.ui.theme.NeutralWhite
import com.example.hology.ui.theme.Primary09
import com.example.hology.ui.theme.Secondary07
import com.example.hology.ui.theme.Secondary09

@Composable
fun HomeScreen(
    navController: NavController
) {
    val scrollState = rememberScrollState()

    val exerciseItems = listOf(
        ExerciseItem(
            id = 1,
            title = "Permainan meniru suara hewan",
            date = "13 Mei 2025",
            imageResId = R.drawable.foto_exercise
        ),
        ExerciseItem(
            id = 2,
            title = "Bermain puzzle alfabet",
            date = "10 Mei 2025",
            imageResId = R.drawable.foto_exercise_2
        ),
        ExerciseItem(
            id = 3,
            title = "Bercerita dengan boneka",
            date = "7 Mei 2025",
            imageResId = R.drawable.foto_exercise_3
        ),
        ExerciseItem(
            id = 4,
            title = "Menggambar warna-warni",
            date = "5 Mei 2025",
            imageResId = R.drawable.foto_exercise_4
        ),
        ExerciseItem(
            id = 5,
            title = "Mengenal anggota tubuh",
            date = "2 Mei 2025",
            imageResId = R.drawable.foto_exercise_5
        )
    )

    Scaffold(
        containerColor = NeutralWhite
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .verticalScroll(scrollState)
        ) {
            Image(
                painter = painterResource(id = R.drawable.header_home_screen),
                contentDescription = null,
                modifier = Modifier.fillMaxWidth(),
                contentScale = ContentScale.Crop
            )

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 15.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {

                    // header
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

                    // profile picture
                    Image(
                        imageVector = Icons.Default.Person,
                        contentDescription = "Profile Picture",
                        modifier = Modifier
                            .size(50.dp)
                            .clip(CircleShape)
                            .background(NeutralWhite),
                        contentScale = ContentScale.Crop
                    )
                }

                Spacer(modifier = Modifier.height(5.dp))

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

                            // konten
                            Row{
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

                // feature section
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
                    // wevy
                    FeatureCard(
                        title = "Wevy",
                        description = "Ungkap potensi anak lewat rekaman aktivitas",
                        icon = R.drawable.wevy_home,
                        onClick = {
                            navController.navigate("wevy")
                        }
                    )

                    Spacer(modifier = Modifier.width(4.dp))

                    // exercise
                    FeatureCard(
                        title = "Exercise",
                        description = "Kumpulan permainan untuk belajar sambil bermain",
                        icon = R.drawable.brain_3d_2,
                        onClick = {
                            navController.navigate("exercise")
                        }
                    )
                }

                Spacer(modifier = Modifier.height(35.dp))

                // exercise carousel
                ExerciseCarousel(exerciseItems = exerciseItems)

                Spacer(modifier = Modifier.height(35.dp))
            }
        }
    }
}
