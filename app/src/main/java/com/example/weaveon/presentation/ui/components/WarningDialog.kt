package com.example.weaveon.presentation.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.OutlinedButton
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
import com.example.weaveon.presentation.ui.theme.NeutralWhite
import com.example.weaveon.presentation.ui.theme.Secondary05
import com.example.weaveon.presentation.ui.theme.Secondary07
import com.example.weaveon.presentation.ui.theme.Secondary08
import com.example.weaveon.presentation.ui.theme.Secondary09

@Composable
fun WarningDialog(
    imageRes: Int,
    title: String,
    message: String,
    primaryButtonText: String,
    secondaryButtonText: String,
    onPrimaryButtonClick: () -> Unit,
    onSecondaryButtonClick: () -> Unit,
    onDismissRequest: () -> Unit
) {
    AlertDialog(
        onDismissRequest = onDismissRequest,
        confirmButton = {},
        containerColor = Color(0xFFEAF9F9),
        shape = RoundedCornerShape(16.dp),
        text = {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // Brain with magnifying glass icon
                Image(
                    painter = painterResource(id = imageRes),
                    contentDescription = "",
                    modifier = Modifier
                        .size(170.dp)
                )

                // Title
                Text(
                    text = title,
                    textAlign = TextAlign.Center,
                    style = TextStyle(
                        fontSize = 24.sp,
                        fontFamily = FontFamily(Font(R.font.poppins_semibold)),
                        color = Secondary09,
                        lineHeight = 28.sp
                    )
                )

                Spacer(modifier = Modifier.height(15.dp))

                // Message text
                Text(
                    text = message,
                    style = TextStyle(
                        fontSize = 14.sp,
                        fontFamily = FontFamily(Font(R.font.poppins_regular)),
                        color = Secondary08
                    ),
                    textAlign = TextAlign.Center
                )

                Spacer(modifier = Modifier.height(10.dp))

                // Buttons row
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 16.dp),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    // "Lewati" (Skip) button - Outlined style
                    OutlinedButton(
                        onClick = onSecondaryButtonClick,
                        modifier = Modifier
                            .weight(1f)
                            .height(45.dp),
                        shape = RoundedCornerShape(30.dp),
                        colors = ButtonDefaults.outlinedButtonColors(
                            contentColor = Secondary07
                        )
                    ) {
                        Text(
                            text = secondaryButtonText,
                            style = TextStyle(
                                fontSize = 16.sp,
                                fontFamily = FontFamily(Font(R.font.poppins_medium))
                            )
                        )
                    }

                    // "Akses" (Access) button - Filled style
                    Button(
                        onClick = onPrimaryButtonClick,
                        modifier = Modifier
                            .weight(1f)
                            .height(45.dp),
                        shape = RoundedCornerShape(30.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Secondary05,
                            contentColor = NeutralWhite
                        )
                    ) {
                        Text(
                            text = primaryButtonText,
                            style = TextStyle(
                                fontSize = 16.sp,
                                fontFamily = FontFamily(Font(R.font.poppins_medium))
                            )
                        )
                    }
                }
            }
        }
    )
}

@Preview
@Composable
private fun PreviewWarningDialog() {
    WarningDialog(
        imageRes = R.drawable.ic_success_3d,
        title = "Oops!",
        message = "Silahkan akses fitur Kidscover untuk mengisi data personalisasi anak",
        primaryButtonText = "Akses",
        secondaryButtonText = "Lewati",
        onPrimaryButtonClick = {},
        onSecondaryButtonClick = {},
        onDismissRequest = {}
    )
}