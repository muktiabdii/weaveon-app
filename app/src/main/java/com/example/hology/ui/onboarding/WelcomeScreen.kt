package com.example.hology.ui.onboarding

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
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
import com.example.hology.ui.theme.Base
import com.example.hology.ui.theme.Primary09
import com.example.hology.R
import com.example.hology.ui.theme.NeutralWhite
import com.example.hology.ui.theme.Primary08
import com.example.hology.ui.theme.Secondary05

@Composable
fun WelcomeScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Base)
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Spacer(modifier = Modifier.height(30.dp))

        // Title
        Text(
            text = "Mulai Bersama\nKami!",
            fontSize = 32.sp,
            fontFamily = FontFamily(Font(R.font.poppins_bold)),
            color = Primary09,
            textAlign = TextAlign.Center,
            lineHeight = 40.sp
        )

        Spacer(modifier = Modifier.height(12.dp))

        // description
        Text(
            text = "Bantu anak tumbuh dan belajar dengan cara yang seru, personal, dan didukung teknologi. Bersama WeaveOn, tiap langkah jadi lebih bermakna.",
            fontSize = 16.sp,
            color = Primary08,
            fontFamily = FontFamily(Font(R.font.poppins_regular)),
            textAlign = TextAlign.Center,
            lineHeight = 22.sp,
            modifier = Modifier.padding(horizontal = 16.dp)
        )

        Spacer(modifier = Modifier.weight(1f))

        // image
        Image(
            painter = painterResource(id = R.drawable.boy_boarding),
            contentDescription = "Character Boy",
            modifier = Modifier
                .size(320.dp)
                .padding(bottom = 10.dp),
            contentScale = ContentScale.Fit
        )

        Spacer(modifier = Modifier.weight(1f))

        // login button
        Button(
            onClick = {
                navController.navigate("login") {
                    popUpTo("welcome") { inclusive = true }
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(48.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Secondary05
            ),
            shape = RoundedCornerShape(28.dp)
        ) {
            Text(
                text = "Masuk",
                color = NeutralWhite,
                fontSize = 16.sp,
                fontFamily = FontFamily(Font(R.font.poppins_medium))
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        // register button
        OutlinedButton(
            onClick = {
                navController.navigate("register") {
                    popUpTo("welcome") { inclusive = true }
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(48.dp),
            colors = ButtonDefaults.outlinedButtonColors(
                contentColor = Base
            ),
            border = ButtonDefaults.outlinedButtonBorder.copy(
                width = 2.dp,
                brush = androidx.compose.ui.graphics.SolidColor(Secondary05)
            ),
            shape = RoundedCornerShape(28.dp)
        ) {
            Text(
                text = "Daftar",
                color = Secondary05,
                fontSize = 16.sp,
                fontFamily = FontFamily(Font(R.font.poppins_medium))
            )
        }

        Spacer(modifier = Modifier.height(32.dp))
    }
}