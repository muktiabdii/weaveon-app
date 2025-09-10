package com.example.hology.ui.exercise

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.fontResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.hology.R
import com.example.hology.cache.UserData
import com.example.hology.cache.exerciseList
import com.example.hology.ui.common.ExerciseCarouselCard
import com.example.hology.ui.common.TopNavbar
import com.example.hology.ui.theme.NeutralWhite
import com.example.hology.ui.theme.Primary08
import com.example.hology.ui.theme.Secondary09

@Composable
fun JejakExerciseScreen(
    navController: NavController,
    viewModel: ExerciseViewModel
) {

    val history by viewModel.history.collectAsState()
    val user = UserData
    val exerciseList = exerciseList

    LaunchedEffect(Unit) {
        viewModel.loadExerciseHistory(user.uid, exerciseList)
    }

    Scaffold(
        topBar = {
            TopNavbar(
                title = "Jejak Exercise",
                navController = navController
            )
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .background(NeutralWhite)
        ) {
            Image(
                painter = painterResource(id = R.drawable.bg_5),
                contentDescription = null,
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )

            LazyColumn(
                contentPadding = PaddingValues(vertical = 24.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp),
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 16.dp)
            ) {

                // title
                item {
                    Text(
                        text = "Kenang setiap langkah kecil si Kecil",
                        fontSize = 22.sp,
                        fontFamily = FontFamily(Font(R.font.poppins_semibold)),
                        color = Secondary09,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.fillMaxWidth()
                    )
                }

                // subtitle
                item {
                    Text(
                        text = "Di sini, kamu bisa melihat kembali momen-momen berharga saat mendampingi proses tumbuh kembang anak lewat berbagai aktivitas yang pernah dilakukan bersama.",
                        fontSize = 14.sp,
                        fontFamily = FontFamily(Font(R.font.poppins_regular)),
                        color = Primary08,
                        textAlign = TextAlign.Center,
                        lineHeight = 20.sp,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 8.dp)
                    )
                }

                item {
                    Spacer(modifier = Modifier.height(16.dp))
                }

                // exercise cards
                items(history) { exerciseItem ->
                    ExerciseCarouselCard(exerciseItem = exerciseItem, isActive = true)
                }
            }
        }
    }
}
