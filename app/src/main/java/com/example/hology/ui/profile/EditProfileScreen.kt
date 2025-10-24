package com.example.hology.ui.profile

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.hology.R
import com.example.hology.ui.common.ActionButton
import com.example.hology.ui.common.InputFormField
import com.example.hology.ui.common.TopNavbar
import com.example.hology.ui.theme.NeutralBlack
import com.example.hology.ui.theme.NeutralWhite
import com.example.hology.ui.theme.Primary08
import com.example.hology.ui.theme.Secondary06

@Composable
fun EditProfileScreen(
    navController: NavController,
    viewModel: UserViewModel
) {
    val userState by viewModel.userState.collectAsState()

    Scaffold(
        topBar = {
            TopNavbar(
                title = "Profil",
                navController = navController
            )
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .background(color = NeutralWhite)
        ) {

            // background
            Image(
                painter = painterResource(id = R.drawable.bg_5),
                contentDescription = null,
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 24.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(modifier = Modifier.height(16.dp))

                // profile picture
                Box(
                    modifier = Modifier
                        .size(100.dp)
                        .clip(CircleShape)
                        .background(NeutralBlack),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = Icons.Default.Person,
                        contentDescription = "Profile Picture",
                        tint = Color.White,
                        modifier = Modifier.size(80.dp)
                    )
                }

                // edit profile picture
                TextButton(
                    onClick = { /* Handle edit photo */ },
                    colors = ButtonDefaults.textButtonColors(
                        contentColor = Secondary06
                    )
                ) {
                    Text(
                        text = "Edit",
                        fontSize = 13.sp,
                        fontFamily = FontFamily(Font(R.font.poppins_medium)),
                    )
                }

                Spacer(modifier = Modifier.height(15.dp))

                Column(
                    modifier = Modifier.fillMaxWidth(),
                    verticalArrangement = Arrangement.spacedBy(10.dp)
                ) {

                    // name
                    Column {
                        Text(
                            text = "Nama",
                            fontSize = 14.sp,
                            fontFamily = FontFamily(Font(R.font.poppins_medium)),
                            color = Primary08,
                            modifier = Modifier.padding(bottom = 8.dp)
                        )
                        InputFormField(
                            value = userState.name,
                            onValueChange = { viewModel.setName(it) },
                            placeholder = "Masukkan Nama",
                            leadingIcon = R.drawable.ic_profile,
                            modifier = Modifier.fillMaxWidth()
                        )
                    }

                    // email
                    Column {
                        Text(
                            text = "Email",
                            fontSize = 14.sp,
                            fontFamily = FontFamily(Font(R.font.poppins_medium)),
                            color = Primary08,
                            modifier = Modifier.padding(bottom = 8.dp)
                        )
                        InputFormField(
                            value = userState.email,
                            onValueChange = { viewModel.setEmail(it) },
                            placeholder = "Masukkan Email",
                            leadingIcon = R.drawable.ic_email,
                            modifier = Modifier.fillMaxWidth()
                        )
                    }
                }

                Spacer(modifier = Modifier.height(160.dp))

                // save button
                ActionButton(
                    text = "Simpan",
                    onClick = {
                        viewModel.editProfile(userState.name, userState.email)
                        navController.navigate("profile")
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 32.dp),
                    enabled = true,
                    isLoading = false
                )
            }
        }
    }
}