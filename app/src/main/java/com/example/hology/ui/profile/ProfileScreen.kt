package com.example.hology.ui.profile

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.hology.ui.common.ProfileHeader
import com.example.hology.ui.common.SettingsCard
import com.example.hology.ui.common.SettingsItem
import com.example.hology.ui.theme.Base
import com.example.hology.ui.theme.Primary04
import com.example.hology.ui.theme.Primary06
import com.example.hology.R

@Composable
fun ProfileScreen() {

    val scrollState = rememberScrollState()

    Scaffold(
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(color = Base,)
                .padding(innerPadding)
                .verticalScroll(scrollState)
        ) {

            // header
            ProfileHeader(
                username = "Username",
                email = "email@gmail.com"
            )
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = 190.dp, start = 16.dp, end = 16.dp)
            ) {
                Spacer(modifier = Modifier.height(24.dp))

                Text(
                    text = "Pengaturan akun",
                    fontSize = 13.sp,
                    fontFamily = FontFamily(Font(R.font.poppins_regular)),
                    color = Primary06,
                    modifier = Modifier.padding(start = 14.dp, bottom = 8.dp)
                )

                // account settings section
                SettingsCard {
                    SettingsItem(
                        icon = painterResource(id = R.drawable.ic_pencil),
                        title = "Edit Profil",
                        onClick = {  }
                    )
                    Divider(modifier = Modifier.background(Primary04))
                    SettingsItem(
                        icon = painterResource(id = R.drawable.ic_lock_2),
                        title = "Ganti Kata Sandi",
                        onClick = {  }
                    )
                }

                Spacer(modifier = Modifier.height(20.dp))

                // other account settings section
                Text(
                    text = "Lainnya",
                    fontSize = 13.sp,
                    fontFamily = FontFamily(Font(R.font.poppins_regular)),
                    color = Primary06,
                    modifier = Modifier.padding(start = 14.dp, bottom = 8.dp)
                )

                SettingsCard {
                    SettingsItem(
                        icon = painterResource(id = R.drawable.ic_sign_out),
                        title = "Keluar",
                        onClick = {  }
                    )
                    Divider(modifier = Modifier.background(Primary04))
                    SettingsItem(
                        icon = painterResource(id = R.drawable.ic_trash),
                        title = "Hapus Akun",
                        onClick = {  }
                    )
                }

                Spacer(modifier = Modifier.height(30.dp))

            }
        }
    }
}
