package com.example.weaveon.presentation.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.weaveon.R
import com.example.weaveon.presentation.ui.theme.NeutralBlack
import com.example.weaveon.presentation.ui.theme.Secondary05
import com.example.weaveon.presentation.ui.theme.Secondary08
import com.example.weaveon.presentation.ui.theme.Secondary09

@Composable
fun OtpVerificationDialog(
    email: String,
    onVerifyClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .width(364.dp)
            .background(Color(0xAA000000)), // semi-transparent background
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .background(Color(0xFFEFF7F9), shape = RoundedCornerShape(16.dp))
                .padding(horizontal = 24.dp, vertical = 32.dp)
                .width(IntrinsicSize.Min),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Icon
            Image(
                painter = painterResource(id = R.drawable.ic_profile_3d), // ganti dengan ikon kamu
                contentDescription = "User Icon",
                modifier = Modifier
                    .size(100.dp)
                    .clip(RoundedCornerShape(12.dp))
            )

            Spacer(modifier = Modifier.height(25.dp))

            // Title
            Text(
                text = "Verifikasi akun anda",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = NeutralBlack,
                fontFamily = FontFamily(Font(R.font.poppins_semibold))
            )

            Spacer(modifier = Modifier.height(18.dp))

            // Subtext
            Text(
                text = "Masukkan 5 digit verifikasi kode\nyang kami kirim melalui email",
                fontSize = 14.sp,
                color = Secondary08,
                textAlign = TextAlign.Center,
                fontFamily = FontFamily(Font(R.font.poppins_regular))
            )

            Spacer(modifier = Modifier.height(18.dp))

            Text(
                text = email,
                fontSize = 14.sp,
                color = NeutralBlack,
                fontWeight = FontWeight.Medium,
                textAlign = TextAlign.Center,
                fontFamily = FontFamily(Font(R.font.poppins_medium)),
                style = TextStyle(textDecoration = TextDecoration.Underline)
            )

            Spacer(modifier = Modifier.height(25.dp))

            // Input fields
            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                repeat(5) {
                    Box(
                        modifier = Modifier
                            .size(48.dp)
                            .border(1.dp, Secondary09, shape = RoundedCornerShape(6.dp)),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(text = "", fontSize = 20.sp) // Bisa ganti dengan TextField untuk interaktivitas
                    }
                }
            }

            Spacer(modifier = Modifier.height(30.dp))

            // Button
            Button(
                onClick = onVerifyClick,
                colors = ButtonDefaults.buttonColors(
                    containerColor = Secondary05,
                    contentColor = Secondary09
                ),
                shape = RoundedCornerShape(30.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp)
            ) {
                Text(
                    text = "Verifikasi",
                    fontSize = 16.sp,
                    fontFamily = FontFamily(Font(R.font.poppins_medium))
                )
            }
        }
    }
}

@Preview
@Composable
private fun PreviewOtpVerificationDialog() {
    OtpVerificationDialog(
        email = "abdisyukur10@gmail.com",
        onVerifyClick = {}
    )
}