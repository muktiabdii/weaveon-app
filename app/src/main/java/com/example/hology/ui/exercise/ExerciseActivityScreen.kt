package com.example.hology.ui.exercise

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import androidx.navigation.NavController
import com.example.hology.R
import com.example.hology.cache.exerciseList
import com.example.hology.ui.common.ActionButton
import com.example.hology.ui.common.TopNavbar
import com.example.hology.ui.theme.NeutralWhite
import com.example.hology.ui.theme.Primary00
import com.example.hology.ui.theme.Secondary05
import com.example.hology.ui.theme.Secondary08
import com.example.hology.ui.theme.Secondary09

@Composable
fun ExerciseActivityScreen(
    navController: NavController,
    exerciseId: String,
    activityId: String
) {

    val scrollState = rememberScrollState()
    var showUploadDialog by remember { mutableStateOf(false) }
    var selectedFeedback by remember { mutableStateOf<String?>(null) }

    val activity = exerciseList.find { it.id == exerciseId }?.activities?.find { it.id == activityId }

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
                .padding(innerPadding)
                .verticalScroll(scrollState)
        ) {

            // header
            Image(
                painter = painterResource(id = activity?.image ?: R.drawable.foto_kata_penting_lebihtolong),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(240.dp)
                    .offset(y = (-20).dp)
            )

            // title card
            Card(
                modifier = Modifier
                    .align(Alignment.TopCenter)
                    .offset(y = 150.dp)
                    .padding(horizontal = 50.dp)
                    .zIndex(2f),
                colors = CardDefaults.cardColors(containerColor = Secondary09),
                shape = RoundedCornerShape(50.dp),
            ) {
                Text(
                    text = activity?.title ?: "",
                    modifier = Modifier
                        .padding(horizontal = 40.dp, vertical = 8.dp),
                    color = Primary00,
                    fontSize = 16.sp,
                    fontFamily = FontFamily(Font(R.font.poppins_semibold)),
                    textAlign = TextAlign.Center
                )
            }

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = 180.dp)
                    .clip(RoundedCornerShape(topStart = 32.dp, topEnd = 32.dp))
                    .background(NeutralWhite)
                    .zIndex(1f)
            ) {
                Spacer(modifier = Modifier.height(30.dp))

                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(top = 6.dp, start = 30.dp, end = 30.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(100.dp)
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.bg_koala),
                            contentDescription = null,
                            contentScale = ContentScale.FillBounds,
                            modifier = Modifier.fillMaxSize()
                        )
                        Column(
                            modifier = Modifier
                                .fillMaxSize(),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center
                        ) {
                            Row(
                                modifier = Modifier
                                    .padding(16.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Image(
                                    painter = painterResource(id = R.drawable.koala_show),
                                    contentDescription = "Koala character",
                                    modifier = Modifier.size(50.dp)
                                )
                                Spacer(modifier = Modifier.width(12.dp))
                                Text(
                                    text = "Apa yang harus parents lakukan?",
                                    fontSize = 16.sp,
                                    fontFamily = FontFamily(Font(R.font.poppins_bold)),
                                    color = Secondary08
                                )
                            }
                        }
                    }

                    // description
                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        colors = CardDefaults.cardColors(containerColor = Color.Transparent),
                    ) {
                        Column(
                            modifier = Modifier.padding(6.dp),
                            verticalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            Text(
                                text = activity?.description ?: "",
                                fontSize = 14.sp,
                                color = Secondary09,
                                lineHeight = 20.sp,
                                textAlign = TextAlign.Center
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(27.dp))

                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        colors = CardDefaults.cardColors(containerColor = Color(0xFFFF8C62)),
                        shape = RoundedCornerShape(16.dp),
                        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
                    ) {
                        Column (
                            modifier = Modifier
                                .padding(16.dp)
                                .fillMaxWidth(),
                            verticalArrangement = Arrangement.spacedBy(8.dp),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {

                            // goal title
                            Text(
                                text = "Apa sih tujuan dari task ini?",
                                fontSize = 15.sp,
                                fontFamily = FontFamily(Font(R.font.poppins_bold)),
                                color = NeutralWhite,
                                textAlign = TextAlign.Center,
                            )

                            // goal description
                            Text(
                                text = activity?.goal ?: "",
                                fontSize = 13.sp,
                                fontFamily = FontFamily(Font(R.font.poppins_regular)),
                                color = NeutralWhite,
                                lineHeight = 20.sp,
                                textAlign = TextAlign.Center
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(14.dp))

                    // tips card
                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        colors = CardDefaults.cardColors(containerColor = Color(0xFFFFFACD)),
                        shape = RoundedCornerShape(16.dp),
                        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
                    ) {
                        Column(
                            modifier = Modifier
                                .padding(16.dp)
                                .fillMaxWidth(),
                            verticalArrangement = Arrangement.spacedBy(8.dp),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.bulp_3d_2),
                                contentDescription = "Light bulb",
                                modifier = Modifier
                                    .size(100.dp)
                                    .align(Alignment.CenterHorizontally)
                            )

                            Text(
                                text = "Tips buat si kecil",
                                fontSize = 16.sp,
                                fontFamily = FontFamily(Font(R.font.poppins_bold)),
                                color = Secondary08,
                                textAlign = TextAlign.Center, // <--- Optional
                                modifier = Modifier.align(Alignment.CenterHorizontally)
                            )

                            Text(
                                text = activity?.tips ?: "",
                                fontSize = 13.sp,
                                fontFamily = FontFamily(Font(R.font.poppins_regular)),
                                color = Secondary09,
                                lineHeight = 20.sp,
                                textAlign = TextAlign.Center
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(39.dp))

                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        colors = CardDefaults.cardColors(containerColor = Color.Transparent),
                        shape = RoundedCornerShape(16.dp)
                    ) {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(start = 16.dp, top = 16.dp, end = 16.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                            ) {
                                Image(
                                    painter = painterResource(id = R.drawable.koala_shy),
                                    contentDescription = "Koala character",
                                    modifier = Modifier.size(120.dp)
                                )
                                Image(
                                    painter = painterResource(id = R.drawable.bubble_chat_exercise),
                                    contentDescription = "Chat Bubble",
                                    modifier = Modifier.size(170.dp)
                                )
                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(14.dp))

                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        colors = CardDefaults.cardColors(containerColor = Color.Transparent),
                        shape = RoundedCornerShape(16.dp)
                    ) {
                        Column(
                            modifier = Modifier.padding(start = 16.dp, end = 16.dp, bottom = 16.dp)
                        ) {
                            Text(
                                text = "Unggah rekaman atau foto aktivitas anak saat menyelesaikan tugas ini. Dokumentasi ini akan membantu kami memahami perkembangan dan memberikan saran yang lebih tepat untuk anak.",
                                fontSize = 13.sp,
                                fontFamily = FontFamily(Font(R.font.poppins_semibold)),
                                color = Secondary09,
                                lineHeight = 16.sp,
                                textAlign = TextAlign.Center
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(39.dp))

                    // upload Button
                    Button(
                        onClick = { /* Handle button click */ },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(44.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Secondary05,
                            contentColor = Color.White
                        ),
                        shape = RoundedCornerShape(24.dp)
                    ) {
                        Text(
                            text = "Unggah Dokumentasi",
                            fontSize = 15.sp,
                            fontFamily = FontFamily(Font(R.font.poppins_medium))
                        )
                    }

                    Spacer(modifier = Modifier.height(24.dp))
                }
            }
        }
    }
}
