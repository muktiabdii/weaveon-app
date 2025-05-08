package com.example.weaveon.presentation.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.weaveon.R
import com.example.weaveon.presentation.ui.theme.Secondary05
import com.example.weaveon.presentation.ui.theme.Secondary08
import com.example.weaveon.presentation.ui.theme.Secondary09

@Composable
fun ActionResultDialog(
    isSuccess: Boolean,
    title: String,
    message: String,
    buttonText: String,
    onButtonClick: () -> Unit
) {
    Surface(
        modifier = Modifier
            .width(364.dp)
            .padding(24.dp),
        shape = RoundedCornerShape(16.dp),
        color = Color(0xFFEAF9F9),
        shadowElevation = 8.dp
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Spacer(modifier = Modifier.height(20.dp))

            // Icon success / failure
            val iconRes = if (isSuccess) R.drawable.ic_google else R.drawable.ic_facebook
            Image(
                painter = painterResource(id = iconRes),
                contentDescription = if (isSuccess) "Success Icon" else "Failure Icon",
                modifier = Modifier
                    .size(100.dp)
                    .padding(bottom = 25.dp)
            )

            // Title
            Text(
                text = title,
                textAlign = TextAlign.Center,
                style = TextStyle(
                    fontSize = 20.sp,
                    fontWeight = FontWeight.SemiBold,
                    fontFamily = FontFamily(Font(R.font.poppins_semibold)),
                    color = Color.Black,
                    lineHeight = 28.sp
                ),
            )

            Spacer(modifier = Modifier.height(15.dp))

            // Message
            Text(
                text = message,
                style = TextStyle(
                    fontSize = 14.sp,
                    fontFamily = FontFamily(Font(R.font.poppins_regular)),
                    color = Secondary08
                )
            )

            Spacer(modifier = Modifier.height(25.dp))

            // Button
            Button(
                onClick = onButtonClick,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(43.dp),
                shape = RoundedCornerShape(30.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Secondary05,
                    contentColor = Secondary09
                )
            ) {
                Text(
                    text = buttonText,
                    style = TextStyle(
                        fontSize = 16.sp,
                        fontFamily = FontFamily(Font(R.font.poppins_medium))
                    )
                )
            }
        }
    }
}


@Preview
@Composable
private fun PreviewActionResultDialog() {
    ActionResultDialog(
        isSuccess = true,
        title = "Berhasil memperbarui kata sandi!",
        message = "Silahkan masuk kembali",
        buttonText = "Masuk",
        onButtonClick = {}
    )
}
