package com.example.hology.ui.auth

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.hology.ui.theme.Base
import com.example.hology.R
import com.example.hology.ui.common.ActionButton
import com.example.hology.ui.common.ActionResultDialog
import com.example.hology.ui.common.InputFormField
import com.example.hology.ui.theme.NeutralBlack
import com.example.hology.ui.theme.Primary09

@Composable
fun ForgotPasswordScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
    viewModel: AuthViewModel
) {

    val forgotPasswordState by viewModel.forgotPasswordState.collectAsState()
    var email by remember { mutableStateOf("") }

    var showDialog by remember { mutableStateOf(false) }
    var isSuccess by remember { mutableStateOf(false) }
    var dialogTitle by remember { mutableStateOf("") }
    var dialogMessage by remember { mutableStateOf("") }
    var dialogButtonText by remember { mutableStateOf("") }

    when (forgotPasswordState) {
        is State.Success -> {
            isSuccess = true
            showDialog = true
            dialogTitle = "Berhasil mengirim link reset kata sandi ke email!"
            dialogMessage = "Silahkan masuk kembali"
            dialogButtonText = "Masuk"
        }

        is State.Error -> {
            isSuccess = false
            showDialog = true
            dialogTitle = "Gagal mengirim link reset kata sandi ke email!"
            dialogMessage = "Silahkan coba kembali"
            dialogButtonText = "Kembali"
        }

        else -> Unit
    }

    if (showDialog) {
        ActionResultDialog(
            isSuccess = isSuccess,
            title = dialogTitle,
            message = dialogMessage,
            buttonText = dialogButtonText,
            onDismissRequest = {
                viewModel.resetForgotPasswordState()
                isSuccess = false
                dialogTitle = ""
                dialogMessage = ""
                dialogButtonText = ""
                showDialog = false
                navController.navigate("login")
            },
            onButtonClick = {
                viewModel.resetForgotPasswordState()
                isSuccess = false
                dialogTitle = ""
                dialogMessage = ""
                dialogButtonText = ""
                showDialog = false
                navController.navigate("login")
            }
        )
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Base)
    ) {

        // background
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
                onClick = { navController.popBackStack() },
                modifier = Modifier
                    .offset(x = (-12).dp)
                    .align(Alignment.Start)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_back),
                    contentDescription = "Back",
                    tint = NeutralBlack
                )
            }

            // header
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
                        color = NeutralBlack
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

            // email input
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

            // send button
            ActionButton(
                text = "Kirim",
                onClick = { viewModel.forgotPassword(email) },
                isLoading = forgotPasswordState is State.Loading
            )

            Spacer(modifier = Modifier.weight(1f))
        }
    }
}