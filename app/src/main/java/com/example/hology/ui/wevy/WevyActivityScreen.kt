package com.example.hology.ui.wevy

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.hology.R
import com.example.hology.cache.wevyList
import com.example.hology.ui.common.ActionButton
import com.example.hology.ui.common.TopNavbar
import com.example.hology.ui.theme.NeutralBlack
import com.example.hology.ui.theme.NeutralWhite
import com.example.hology.ui.theme.Primary01
import com.example.hology.ui.theme.Primary05
import com.example.hology.ui.theme.Primary08
import com.example.hology.ui.theme.Primary09
import com.example.hology.ui.theme.Secondary01
import com.example.hology.ui.theme.Secondary04
import com.example.hology.ui.theme.Secondary05
import com.example.hology.ui.theme.Secondary09
import com.example.hology.ui.theme.Secondary10

@Composable
fun WevyActivityScreen(
    navController: NavController,
    wevyId: String,
    activityId: String
) {

    val activity = wevyList.find { it.id == wevyId }?.activities?.find { it.id == activityId }

    Scaffold(
        topBar = {
            TopNavbar(
                title = "Wevy",
                navController = navController
            )
        },
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .background(NeutralWhite)
                .padding(innerPadding)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(120.dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.bg_5),
                    contentDescription = null,
                    modifier = Modifier.fillMaxWidth(),
                    contentScale = ContentScale.FillBounds
                )

                Image(
                    painter = painterResource(id = R.drawable.header_wevy),
                    contentDescription = null,
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.FillBounds
                )

                Text(
                    text = activity?.title ?: "",
                    fontSize = 20.sp,
                    fontFamily = FontFamily(Font(R.font.poppins_semibold)),
                    color = Color.White,
                    modifier = Modifier
                        .align(Alignment.BottomCenter)
                        .padding(bottom = 18.dp)
                )
            }

            Column(
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .padding(top = 30.dp)
                    .fillMaxWidth()
            ) {
                // Description Text
                Text(
                    text = activity?.description ?: "",
                    fontSize = 14.sp,
                    fontFamily = FontFamily(Font(R.font.poppins_regular)),
                    color = Color.Black,
                    lineHeight = 20.sp,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 8.dp)
                )

                Spacer(modifier = Modifier.height(32.dp))

                // Purpose Card
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    colors = CardDefaults.cardColors(containerColor = NeutralWhite)
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.ic_goal),
                            contentDescription = null,
                            modifier = Modifier.size(32.dp)
                        )

                        Spacer(modifier = Modifier.width(8.dp))

                        Text(
                            text = "Apa sih tujuan dari task ini?",
                            fontSize = 16.sp,
                            fontFamily = FontFamily(Font(R.font.poppins_bold)),
                            color = Secondary10
                        )
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                // Info Card
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(12.dp),
                    colors = CardDefaults.cardColors(containerColor = Color(0xFFFF8C62)),
                    elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
                ) {
                    Text(
                        text = activity?.goal ?: "",
                        fontSize = 14.sp,
                        fontFamily = FontFamily(Font(R.font.poppins_regular)),
                        color = NeutralWhite,
                        modifier = Modifier.padding(vertical = 16.dp, horizontal = 10.dp),
                        textAlign = TextAlign.Center,
                        lineHeight = 20.sp
                    )
                }

                Spacer(modifier = Modifier.height(32.dp))

                // Steps Section
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.ic_steps),
                        contentDescription = null,
                        modifier = Modifier.size(32.dp)
                    )

                    Spacer(modifier = Modifier.width(8.dp))

                    Text(

                        text = "Langkah-langkah",
                        fontSize = 16.sp,
                        fontFamily = FontFamily(Font(R.font.poppins_bold)),
                        color = Secondary10
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))

                activity?.steps?.forEachIndexed { index, step ->
                    StepItem(
                        number = index + 1,
                        text = step
                    )
                    if (index < activity.steps.size - 1) {
                        Spacer(modifier = Modifier.height(12.dp))
                    }
                }

                Spacer(modifier = Modifier.height(32.dp))

                // Tips Section
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.bulp_3d_2),
                        contentDescription = null,
                        modifier = Modifier.size(32.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "Tips Untuk Si Kecil",
                        fontSize = 16.sp,
                        fontFamily = FontFamily(Font(R.font.poppins_bold)),
                        color = Secondary10
                    )
                }

                Spacer(modifier = Modifier.height(8.dp))

                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(16.dp),
                    colors = CardDefaults.cardColors(containerColor = Primary01),
                    elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
                ) {
                    Text(
                        text = activity?.tips ?: "",
                        fontSize = 14.sp,
                        fontFamily = FontFamily(Font(R.font.poppins_regular)),
                        color = Secondary09,
                        modifier = Modifier.padding(vertical = 16.dp, horizontal = 10.dp),
                        textAlign = TextAlign.Center,
                        lineHeight = 20.sp
                    )
                }

                Spacer(modifier = Modifier.height(38.dp))

                // button
                Button(
                    onClick = { navController.navigate("wevy_record") },
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
                        text = "Mulai Rekaman",
                        fontSize = 15.sp,
                        fontFamily = FontFamily(Font(R.font.poppins_medium))
                    )
                }

                Spacer(modifier = Modifier.height(24.dp))
            }
        }
    }
}

@Composable
private fun StepItem(
    number: Int,
    text: String
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .border(
                width = 1.dp,
                color = Secondary04,
                shape = RoundedCornerShape(12.dp)
            )
            .padding(vertical = 18.dp, horizontal = 14.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .size(30.dp)
                .background(
                    color = Secondary10,
                    shape = RoundedCornerShape(31.dp)
                ),
            contentAlignment = Alignment.Center,
        ) {
            Text(
                text = number.toString(),
                fontSize = 12.sp,
                fontFamily = FontFamily(Font(R.font.poppins_semibold)),
                color = Color.White
            )
        }

        Spacer(modifier = Modifier.width(10.dp))

        Text(
            text = text,
            fontSize = 14.sp,
            fontFamily = FontFamily(Font(R.font.poppins_semibold)),
            color = Primary08,
            modifier = Modifier.weight(1f)
        )
    }
}