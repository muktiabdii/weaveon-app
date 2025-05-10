package com.example.weaveon.presentation.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.weaveon.R
import com.example.weaveon.presentation.ui.components.InputFormField
import com.example.weaveon.presentation.ui.components.AuthActionButton
import com.example.weaveon.presentation.ui.theme.Base
import com.example.weaveon.presentation.ui.theme.Primary09
import com.example.weaveon.presentation.viewmodel.UserViewModel

@Composable
fun ForgotPasswordScreen(
    userViewModel: UserViewModel,
    onSubmitClick: () -> Unit = {},
    onBackClick: () -> Unit = {}
) {
    var email by remember { mutableStateOf("") }
    val isLoading by userViewModel.isLoading.collectAsState()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                color = Base,
            )
    ) {
        Image(
            painter = painterResource(id = R.drawable.bg_1),
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .height(440.dp)
                .align(Alignment.BottomCenter),
            contentScale = ContentScale.FillBounds
        )

        Image(
            painter = painterResource(id = R.drawable.bg_2),
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .height(340.dp)
                .align(Alignment.BottomCenter),
            contentScale = ContentScale.FillBounds
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 24.dp)
                .padding(top = 6.dp)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            // tombol back
            IconButton(
                onClick = { onBackClick() },
                modifier = Modifier
                    .offset(x = (-12).dp)
                    .align(Alignment.Start)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_back),
                    contentDescription = "Back",
                    tint = Color.Black
                )
            }

            // Title and subtitle
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 42.dp, bottom = 10.dp),
                horizontalAlignment = Alignment.Start
            ) {
                Text(
                    text = "Lupa Kata Sandi",
                    style = TextStyle(
                        fontSize = 30.sp,
                        fontFamily = FontFamily(Font(R.font.poppins_semibold)),
                        color = Color.Black
                    )
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = "Silahkan masukkan alamat anda untuk meminta pengaturan ulang kata sandi",
                    style = TextStyle(
                        fontSize = 14.sp,
                        fontFamily = FontFamily(Font(R.font.poppins_regular)),
                        color = Primary09
                    )
                )
            }

            Spacer(modifier = Modifier.height(10.dp))

            // Email input
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.Start
            ) {
                Text(
                    text = "Email",
                    style = TextStyle(
                        fontSize = 14.sp,
                        fontFamily = FontFamily(Font(R.font.poppins_medium)),
                        color = Primary09
                    ),
                    modifier = Modifier.padding(bottom = 8.dp)
                )

                InputFormField(
                    value = email,
                    onValueChange = { email = it },
                    placeholder = "Masukkan Email Anda",
                    leadingIcon = R.drawable.ic_email
                )
            }

            Spacer(modifier = Modifier.height(26.dp))

            // Submit button
            AuthActionButton(
                text = "Kirim",
                onClick = onSubmitClick,
                modifier = Modifier.fillMaxWidth(),
                isLoading = isLoading
            )

            // Fill remaining space
            Spacer(modifier = Modifier.weight(1f))
        }
    }
}
