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
import com.example.hology.ui.common.TopNavbar
import com.example.hology.ui.theme.NeutralBlack
import com.example.hology.ui.theme.NeutralWhite

data class MoodOption(
    val color: Color,
    val label: String
)

@Composable
fun WevyResultScreen(
    navController: NavController
) {
    var selectedMood by remember { mutableIntStateOf(0) }

    val moodOptions = listOf(
        MoodOption(Color(0xFF33A136), "Sangat Senang"),
        MoodOption(Color(0xFF77BF3A), "Cukup Senang"),
        MoodOption(Color(0xFFFCB506), "Kurang Senang"),
        MoodOption(Color(0xFFF67812), "Sangat Tidak Senang")
    )

    val activeDrawables = listOf(
        R.drawable.ic_very_happy,
        R.drawable.ic_happy,
        R.drawable.ic_unhappy,
        R.drawable.ic_very_unhappy
    )

    val inactiveDrawables = listOf(
        R.drawable.ic_very_happy_gray,
        R.drawable.ic_happy_gray,
        R.drawable.ic_unhappy_gray,
        R.drawable.ic_very_unhappy_grey
    )

    Scaffold(
        topBar = {
            TopNavbar(
                title = "Wevy",
                navController = navController
            )
        },
    ) {
        innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .background(color = NeutralWhite)
        ) {

            // background
            Image(
                painter = painterResource(id = R.drawable.bg_5),
                contentDescription = null,
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
                    .padding(16.dp)
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 47.dp),
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        moodOptions.forEachIndexed { index, mood ->
                            MoodButton(
                                mood = mood,
                                isSelected = selectedMood == index,
                                onClick = { selectedMood = index },
                                activeDrawableRes = activeDrawables[index],
                                inactiveDrawableRes = inactiveDrawables[index]
                            )
                            if (index != moodOptions.lastIndex) {
                                Spacer(modifier = Modifier.width(16.dp))
                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(24.dp))

                    Text(
                        text = moodOptions[selectedMood].label,
                        color = moodOptions[selectedMood].color,
                        fontSize = 16.sp,
                        fontFamily = FontFamily(Font(R.font.poppins_semibold))
                    )
                }

                Spacer(modifier = Modifier.height(32.dp))

                // Activity Description Card
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(16.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = Color.Transparent
                    ),
                    border = BorderStroke(1.dp, NeutralBlack)
                ) {
                    Column(
                        modifier = Modifier.padding(14.dp)
                    ) {
                        Text(
                            text = "Pada aktivitas Susun Balok, anak menunjukkan antusiasme yang cukup tinggi. Berdasarkan hasil analisis, anak terlihat fokus dan tertarik selama proses menyusun balok. Respon emosional yang positif mulai terlihat sejak menit-menit awal hingga pertengahan aktivitas. Namun, terdapat sedikit penurunan minat di bagian tengah yang kemungkinan disebabkan oleh tantangan atau tingkat kesulitan yang mulai meningkat.",
                            color = NeutralBlack,
                            fontSize = 15.sp,
                            fontFamily = FontFamily(Font(R.font.poppins_regular)),
                            lineHeight = 20.sp
                        )
                    }
                }

                Spacer(modifier = Modifier.height(24.dp))
            }
        }
    }
}

@Composable
fun MoodButton(
    mood: MoodOption,
    isSelected: Boolean,
    onClick: () -> Unit,
    activeDrawableRes: Int,
    inactiveDrawableRes: Int
) {
    val scale by animateFloatAsState(
        targetValue = if (isSelected) 1.1f else 1f,
        animationSpec = tween(200)
    )

    Box(
        modifier = Modifier
            .size(64.dp)
            .scale(scale)
            .clip(CircleShape)
            .clickable { onClick() },
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(id = if (isSelected) activeDrawableRes else inactiveDrawableRes),
            contentDescription = mood.label,
            modifier = Modifier.size(64.dp)
        )
    }
}
