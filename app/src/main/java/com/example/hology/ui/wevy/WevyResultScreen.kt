@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.hology.ui.wevy

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.hology.R
import com.example.hology.cache.UserData
import com.example.hology.cache.wevyResultList
import com.example.hology.ui.common.TopNavbar
import com.example.hology.ui.theme.NeutralBlack
import com.example.hology.ui.theme.NeutralWhite
import com.example.hology.ui.theme.Primary03

@Composable
fun WevyResultScreen(
    navController: NavController,
    wevyId: String,
    activityId: String,
    viewModel: WevyViewModel
) {

    val state by viewModel.state.collectAsState()
    val user = UserData

    LaunchedEffect(Unit) {
        viewModel.getEmotion(user.uid, wevyId, activityId)
    }

    Scaffold(
        topBar = {
            TopNavbar(
                title = "Wevy",
                navController = navController
            )
        },
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .background(color = NeutralWhite)
        ) {
            Image(
                painter = painterResource(id = R.drawable.bg_5),
                contentDescription = null,
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )

            when (state) {
                is WevyState.Loading -> {
                    CircularProgressIndicator(
                        modifier = Modifier
                            .size(size = 150.dp)
                            .padding(bottom = 25.dp)
                            .align(Alignment.Center),
                        color = Primary03,
                        strokeWidth = 10.dp
                    )
                }

                is WevyState.Success -> {
                    val emotion = (state as WevyState.Success).data
                    val activeLabel = emotion.label.lowercase()
                    val matched = wevyResultList.find { it.label.lowercase() == activeLabel }

                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .verticalScroll(rememberScrollState())
                            .padding(16.dp)
                    ) {
                        // Tampilkan semua emot
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = 47.dp),
                            horizontalArrangement = Arrangement.Center
                        ) {
                            wevyResultList.forEach { item ->
                                val isActive = item.label.lowercase() == activeLabel
                                Image(
                                    painter = painterResource(
                                        id = if (isActive) item.activeIcon else item.inactiveIcon
                                    ),
                                    contentDescription = item.label,
                                    modifier = Modifier.size(60.dp)
                                )

                                if (item != wevyResultList.last()) {
                                    Spacer(modifier = Modifier.width(16.dp))
                                }
                            }
                        }

                        Spacer(modifier = Modifier.height(24.dp))

                        Text(
                            text = matched?.label ?: "",
                            color = matched?.color ?: Color.Black,
                            fontSize = 16.sp,
                            fontFamily = FontFamily(Font(R.font.poppins_semibold)),
                            textAlign = TextAlign.Center,
                            modifier = Modifier.fillMaxWidth()
                        )

                        Spacer(modifier = Modifier.height(32.dp))

                        // Card activity description
                        if (matched != null) {
                            Card(
                                modifier = Modifier.fillMaxWidth(),
                                shape = RoundedCornerShape(16.dp),
                                colors = CardDefaults.cardColors(containerColor = Color.Transparent),
                                border = BorderStroke(1.dp, NeutralBlack)
                            ) {
                                Column(
                                    modifier = Modifier.padding(14.dp)
                                ) {
                                    Text(
                                        text = matched.description,
                                        color = NeutralBlack,
                                        fontSize = 15.sp,
                                        fontFamily = FontFamily(Font(R.font.poppins_regular)),
                                        lineHeight = 20.sp
                                    )
                                }
                            }
                        }
                    }
                }


                is WevyState.Error -> {
                    Text(
                        text = (state as WevyState.Error).message,
                        color = Color.Red,
                        modifier = Modifier.align(Alignment.Center)
                    )
                }

                else -> {}
            }
        }
    }
}
