package com.example.weaveon.presentation.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.weaveon.R
import com.example.weaveon.presentation.ui.components.BottomBar
import com.example.weaveon.presentation.ui.components.WarningDialog
import com.example.weaveon.presentation.ui.theme.NeutralWhite
import com.example.weaveon.presentation.ui.theme.Secondary05
import com.example.weaveon.presentation.ui.theme.Secondary09
import com.example.weaveon.presentation.viewmodel.KidscoverViewModel

@Composable
fun LandingPageChatbot(
    kidscoverViewModel: KidscoverViewModel,
    onAccessKidscover: () -> Unit = {},
    onSkipKidscover: () -> Unit = {}
) {
    // State to control dialog visibility
    val showDialog = remember { mutableStateOf(false) }

    Scaffold(
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFFF8F8F8))
                .padding(innerPadding),
            contentAlignment = Alignment.Center
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.padding(top = 16.dp, bottom = 24.dp, start = 24.dp, end = 24.dp)
            ) {
                // Speech Bubble
                Box(
                    contentAlignment = Alignment.Center,
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.bubble_chat_landing_page_chatbot),
                        contentDescription = "Speech bubble",
                        modifier = Modifier.fillMaxWidth()
                    )

                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier
                            .widthIn(max = 220.dp) // Atur maksimal lebar teks agar tidak keluar dari bubble
                            .wrapContentHeight()
                            .offset(y = (-18).dp)
                            .graphicsLayer {
                                rotationZ = -2f // Miring ke kiri 5 derajat
                            }
                    ) {
                        Text(
                            text = "Hi Parents, Aku Aibu!",
                            textAlign = TextAlign.Center,
                            color = Secondary09,
                            fontFamily = FontFamily(Font(R.font.poppins_semibold)),
                            fontSize = 18.sp,
                            lineHeight = 20.sp, // jangan terlalu tinggi
                            modifier = Modifier
                                .padding(horizontal = 8.dp)
                                .wrapContentHeight()
                        )
                        Text(
                            text = "Yuk, mulai ngobrol kapan aja! Aku di sini buat bantu dengan sepenuh hati",
                            textAlign = TextAlign.Center,
                            color = Secondary09,
                            fontFamily = FontFamily(Font(R.font.poppins_medium)),
                            fontSize = 14.sp,
                            lineHeight = 18.sp,
                            modifier = Modifier
                                .padding(horizontal = 8.dp, vertical = 4.dp)
                                .wrapContentHeight()
                        )
                    }

                }
                // Koala Character
                Image(
                    painter = painterResource(id = R.drawable.koala_say_hi), // You'll need to add a koala image to your resources
                    contentDescription = "Koala mascot",
                    modifier = Modifier.size(270.dp)
                )
                // Chat Button
                Button(
                    onClick = {
                        kidscoverViewModel.hasChildData { hasData ->
                            if (hasData) {
                                onSkipKidscover()
                            } else {
                                showDialog.value = true
                            }
                        }
                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Secondary05
                    ),
                    shape = RoundedCornerShape(24.dp),
                    modifier = Modifier.width(180.dp)
                ) {
                    Text(
                        text = "Chat Sekarang",
                        fontFamily = FontFamily(Font(R.font.poppins_medium)),
                        color = NeutralWhite,
                        fontSize = 16.sp,
                        modifier = Modifier.padding(vertical = 4.dp)
                    )
                }
            }

            // Show the warning dialog when showDialog is true
            if (showDialog.value) {
                WarningDialog(
                    title = "Oops!",
                    message = "Silahkan akses fitur Kidscover untuk mengisi data personalisasi anak",
                    primaryButtonText = "Akses",
                    secondaryButtonText = "Lewati",
                    imageRes = R.drawable.ic_brain_3d,
                    onPrimaryButtonClick = {
                        showDialog.value = false
                        onAccessKidscover()
                    },
                    onSecondaryButtonClick = {
                        showDialog.value = false
                        onSkipKidscover()
                    },
                    onDismissRequest = {
                        showDialog.value = false
                    }
                )
            }
        }
    }
}
