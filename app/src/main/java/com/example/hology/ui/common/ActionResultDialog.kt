package com.example.hology.ui.common

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.hology.R
import com.example.hology.ui.theme.Green
import com.example.hology.ui.theme.NeutralBlack
import com.example.hology.ui.theme.Red
import com.example.hology.ui.theme.Secondary08

@Composable
fun ActionResultDialog(
    isSuccess: Boolean,
    title: String,
    message: String,
    buttonText: String,
    onDismissRequest: () -> Unit,
    onButtonClick: () -> Unit
) {

    AlertDialog(
        onDismissRequest = onDismissRequest,
        confirmButton = {  },
        containerColor = Color(0xFFEAF9F9),
        shape = RoundedCornerShape(16.dp),
        text = {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                val iconRes = if (isSuccess) R.drawable.ic_success_3d else R.drawable.ic_failure_3d
                Image(
                    painter = painterResource(id = iconRes),
                    contentDescription = if (isSuccess) "Success Icon" else "Failure Icon",
                    modifier = Modifier
                        .size(150.dp)
                        .padding(bottom = 25.dp)
                )

                Text(
                    text = title,
                    textAlign = TextAlign.Center,
                    style = TextStyle(
                        fontSize = 20.sp,
                        fontFamily = FontFamily(Font(R.font.poppins_semibold)),
                        color = if (isSuccess) NeutralBlack else Red,
                        lineHeight = 28.sp
                    )
                )

                Spacer(modifier = Modifier.height(15.dp))

                Text(
                    text = message,
                    style = TextStyle(
                        fontSize = 14.sp,
                        fontFamily = FontFamily(Font(R.font.poppins_regular)),
                        color = Secondary08
                    ),
                    textAlign = TextAlign.Center
                )

                Spacer(modifier = Modifier.height(25.dp))

                Button(
                    onClick = onButtonClick,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(43.dp),
                    shape = RoundedCornerShape(30.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = if (isSuccess) Green else Color.Red,
                        contentColor = Color.White
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
    )
}