package com.example.weaveon.presentation.ui.screens

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.weaveon.R
import com.example.weaveon.presentation.ui.components.InputFormField
import com.example.weaveon.presentation.ui.components.AuthActionButton
import com.example.weaveon.presentation.ui.theme.Base
import com.example.weaveon.presentation.ui.theme.Primary04
import com.example.weaveon.presentation.ui.theme.Primary09
import com.example.weaveon.presentation.viewmodel.UserViewModel

@Composable
fun RegisterScreen(
    userViewModel: UserViewModel,
    onRegisterClick: () -> Unit = {},
    onGoogleRegisterClick: () -> Unit = {},
    onFacebookRegisterClick: () -> Unit = {},
    onLoginClick: () -> Unit = {},
    onBackClick: () -> Unit = {}
) {
    val context = LocalContext.current
    val name by userViewModel.name.collectAsState()
    val email by userViewModel.email.collectAsState()
    val password by userViewModel.password.collectAsState()
    val confirmPassword by userViewModel.passwordKonfirmation.collectAsState()
    val isLoading by userViewModel.isLoading.collectAsState()
    val error by userViewModel.error.collectAsState()

    LaunchedEffect(error) {
        error?.let {
            Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
            userViewModel.clearError()
        }
    }

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
                    .padding(top = 14.dp, bottom = 16.dp),
                horizontalAlignment = Alignment.Start
            ) {
                Text(
                    text = "Buat Akun",
                    style = TextStyle(
                        fontSize = 30.sp,
                        fontFamily = FontFamily(Font(R.font.poppins_semibold)),
                        color = Color.Black
                    )
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = "Silahkan mengisi data untuk membuat akun",
                    style = TextStyle(
                        fontSize = 14.sp,
                        fontFamily = FontFamily(Font(R.font.poppins_regular)),
                        color = Primary09
                    )
                )
            }

            Spacer(modifier = Modifier.height(6.dp))

            // Name input
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.Start
            ) {
                Text(
                    text = "Nama",
                    style = TextStyle(
                        fontSize = 14.sp,
                        fontFamily = FontFamily(Font(R.font.poppins_medium)),
                        color = Primary09
                    ),
                    modifier = Modifier.padding(bottom = 8.dp)
                )

                InputFormField(
                    value = name,
                    onValueChange = { userViewModel.setName(it) },
                    placeholder = "Masukkan Nama Anda",
                    leadingIcon = R.drawable.ic_profile
                )
            }

            Spacer(modifier = Modifier.height(14.dp))

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
                    onValueChange = { userViewModel.setEmail(it) },
                    placeholder = "Masukkan Email Anda",
                    leadingIcon = R.drawable.ic_email
                )
            }

            Spacer(modifier = Modifier.height(14.dp))

            // Password input
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.Start
            ) {
                Text(
                    text = "Kata Sandi",
                    style = TextStyle(
                        fontSize = 14.sp,
                        fontFamily = FontFamily(Font(R.font.poppins_medium)),
                        color = Primary09
                    ),
                    modifier = Modifier.padding(bottom = 8.dp)
                )

                InputFormField(
                    value = password,
                    onValueChange = { userViewModel.setPassword(it) },
                    placeholder = "Masukkan Kata Sandi",
                    leadingIcon = R.drawable.ic_lock,
                    isPassword = true
                )
            }

            Spacer(modifier = Modifier.height(14.dp))

            // Confirm Password input
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.Start
            ) {
                Text(
                    text = "Konfirmasi Kata Sandi",
                    style = TextStyle(
                        fontSize = 14.sp,
                        fontFamily = FontFamily(Font(R.font.poppins_medium)),
                        color = Primary09
                    ),
                    modifier = Modifier.padding(bottom = 8.dp)
                )

                InputFormField(
                    value = confirmPassword,
                    onValueChange = { userViewModel.setPasswordKonfirmation(it) },
                    placeholder = "Masukkan Kata Sandi",
                    leadingIcon = R.drawable.ic_lock,
                    isPassword = true
                )
            }

            Spacer(modifier = Modifier.height(30.dp))

            // Register button
            AuthActionButton(
                text = "Daftar",
                onClick = {
                    userViewModel.register { success ->
                        if (success) {
                            onRegisterClick()
                        }
                    }
                },
                isLoading = isLoading
            )


            Spacer(modifier = Modifier.height(12.dp))

            // Divider with text
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Divider(
                    modifier = Modifier.weight(1f),
                    color = Primary04,
                    thickness = 1.dp
                )

                Text(
                    text = "atau daftar dengan",
                    modifier = Modifier.padding(horizontal = 8.dp),
                    style = TextStyle(
                        fontSize = 14.sp,
                        fontFamily = FontFamily(Font(R.font.poppins_medium)),
                        color = Primary09
                    )
                )

                Divider(
                    modifier = Modifier.weight(1f),
                    color = Primary04,
                    thickness = 1.dp
                )
            }

            Spacer(modifier = Modifier.height(12.dp))

            // Social register buttons
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                AuthActionButton(
                    text = "Google",
                    onClick = onGoogleRegisterClick,
                    leadingIcon = R.drawable.ic_google,
                    modifier = Modifier.weight(1f)
                )

                AuthActionButton(
                    text = "Facebook",
                    onClick = onFacebookRegisterClick,
                    leadingIcon = R.drawable.ic_facebook,
                    modifier = Modifier.weight(1f)
                )
            }

            Spacer(modifier = Modifier.weight(1f))

            // Login prompt
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 12.dp),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Sudah memiliki akun?",
                    style = TextStyle(
                        fontSize = 14.sp,
                        fontFamily = FontFamily(Font(R.font.poppins_regular)),
                        color = Color.Black
                    )
                )

                TextButton(
                    onClick = onLoginClick,
                    modifier = Modifier.offset(x = (-8).dp)) {
                    Text(
                        text = "Masuk",
                        style = TextStyle(
                            fontSize = 14.sp,
                            fontFamily = FontFamily(Font(R.font.poppins_semibold)),
                            color = Color.Black
                        )
                    )
                }
            }
        }
    }
}
