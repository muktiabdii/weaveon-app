package com.example.hology.ui.auth

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
import androidx.navigation.NavController
import com.example.hology.R
import com.example.hology.ui.theme.Base
import com.example.hology.ui.theme.NeutralBlack
import com.example.hology.ui.theme.Primary04
import com.example.hology.ui.theme.Primary09
import com.example.hology.ui.common.InputFormField
import com.example.hology.ui.common.ActionButton
import com.example.hology.ui.common.AuthSocialButton

@Composable
fun LoginScreen(
    modifier: Modifier = Modifier,
    viewModel: AuthViewModel,
    navController: NavController
) {

    val context = LocalContext.current
    val loginState by viewModel.loginState.collectAsState()
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    when (loginState) {
        is State.Success -> {
            LaunchedEffect(Unit) {
                navController.navigate("main") {
                    popUpTo("login") { inclusive = true }
                }

                viewModel.resetLoginState()
            }
        }

        is State.Error -> {
            val message = (loginState as State.Error).message
            LaunchedEffect(message) {
                Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
                viewModel.resetLoginState()
            }
        }

        else -> Unit
    }

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(
                color = Base
            )
    ) {

        // background
        Image(
            painter = painterResource(id = R.drawable.bg_1),
            contentDescription = null,
            modifier = modifier
                .fillMaxSize()
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
            modifier = modifier
                .fillMaxSize()
                .padding(horizontal = 24.dp)
                .padding(top = 6.dp)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            // header
            Column(
                modifier = modifier
                    .fillMaxWidth()
                    .padding(top = 42.dp, bottom = 24.dp),
                horizontalAlignment = Alignment.Start
            ) {
                Text(
                    text = "Selamat Datang",
                    style = TextStyle(
                        fontSize = 30.sp,
                        fontFamily = FontFamily(Font(R.font.poppins_semibold)),
                        color = NeutralBlack
                    )
                )

                Spacer(modifier = modifier.height(8.dp))

                Text(
                    text = "Silahkan mengisi data dengan akun yang sudah didaftarkan sebelumnya",
                    style = TextStyle(
                        fontSize = 14.sp,
                        fontFamily = FontFamily(Font(R.font.poppins_regular)),
                        color = Primary09
                    )
                )

                Spacer(modifier = modifier.height(16.dp))

                // email input
                Column(
                    modifier = modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.Start
                ) {
                    Text(
                        text = "Email",
                        style = TextStyle(
                            fontSize = 14.sp,
                            fontFamily = FontFamily(Font(R.font.poppins_medium)),
                            color = Primary09
                        ),
                        modifier = modifier.padding(bottom = 8.dp)
                    )

                    InputFormField(
                        value = email,
                        onValueChange = { email = it },
                        placeholder = "Masukkan Email Anda",
                        leadingIcon = R.drawable.ic_email,
                    )

                    Spacer(modifier = modifier.height(16.dp))

                    // password input
                    Column(
                        modifier = modifier.fillMaxWidth(),
                        horizontalAlignment = Alignment.Start
                    ) {
                        Text(
                            text = "Kata Sandi",
                            style = TextStyle(
                                fontSize = 14.sp,
                                fontFamily = FontFamily(Font(R.font.poppins_medium)),
                                color = Primary09
                            ),
                        )

                        Spacer(modifier = modifier.height(4.dp))

                        InputFormField(
                            value = password,
                            onValueChange = { password = it },
                            placeholder = "Masukkan Kata Sandi",
                            leadingIcon = R.drawable.ic_lock,
                            isPassword = true
                        )

                        // lupa kata sandi
                        TextButton(
                            onClick = { navController.navigate("forgot_password") },
                            modifier = modifier.align(Alignment.End)
                        ) {
                            Text(
                                text = "Lupa Kata Sandi?",
                                style = TextStyle(
                                    fontSize = 12.sp,
                                    fontFamily = FontFamily(Font(R.font.poppins_medium)),
                                    color = Color.Red
                                )
                            )
                        }
                    }

                    Spacer(modifier = modifier.height(7.dp))

                    // login button
                    ActionButton(
                        text = "Masuk",
                        onClick = { viewModel.login(email, password) },
                        isLoading = loginState is State.Loading
                    )

                    Spacer(modifier = modifier.height(12.dp))

                    // divider
                    Row(
                        modifier = modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Divider(
                            modifier = modifier.weight(1f),
                            color = Primary04,
                            thickness = 1.dp
                        )

                        Text(
                            text = "atau masuk dengan",
                            modifier = modifier.padding(horizontal = 8.dp),
                            style = TextStyle(
                                fontSize = 14.sp,
                                fontFamily = FontFamily(Font(R.font.poppins_medium)),
                                color = Primary09
                            )
                        )

                        Divider(
                            modifier = modifier.weight(1f),
                            color = Primary04,
                            thickness = 1.dp
                        )
                    }

                    Spacer(modifier = modifier.height(12.dp))

                    // social media button
                    Row(
                        modifier = modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(16.dp)
                    ) {
                        AuthSocialButton(
                            text = "Google",
                            onClick = {},
                            leadingIcon = R.drawable.ic_google,
                            modifier = modifier.weight(1f)
                        )

                        AuthSocialButton(
                            text = "Facebook",
                            onClick = {},
                            leadingIcon = R.drawable.ic_facebook,
                            modifier = modifier.weight(1f)
                        )
                    }

                    Spacer(modifier = modifier.weight(1f))

                    // register button
                    Row(
                        modifier = modifier
                            .fillMaxSize()
                            .padding(vertical = 16.dp),
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "Belum punya akun?",
                            style = TextStyle(
                                fontSize = 14.sp,
                                fontFamily = FontFamily(Font(R.font.poppins_regular)),
                                color = NeutralBlack
                            )
                        )

                        TextButton(
                            onClick = { navController.navigate("register") },
                            modifier = modifier.offset(x = (-8).dp)
                        ) {
                            Text(
                                text = "Daftar",
                                style = TextStyle(
                                    fontSize = 14.sp,
                                    fontFamily = FontFamily(Font(R.font.poppins_semibold)),
                                    color = NeutralBlack
                                )
                            )
                        }
                    }
                }
            }
        }
    }
}