package com.example.weaveon.presentation.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.weaveon.R
import com.example.weaveon.presentation.ui.components.ProfileHeader
import com.example.weaveon.presentation.ui.components.SettingsCard
import com.example.weaveon.presentation.ui.components.SettingsItem
import com.example.weaveon.presentation.ui.theme.Base
import com.example.weaveon.presentation.ui.theme.Primary04
import com.example.weaveon.presentation.ui.theme.Primary06

@Composable
fun ProfileScreen() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                color = Base,
        )
    ) {
        // Profile header with circular avatar
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

            // Account settings section
            Text(
                text = "Pengaturan akun",
                fontSize = 13.sp,
                fontFamily = FontFamily(Font(R.font.poppins_regular)),
                color = Primary06,
                modifier = Modifier.padding(start = 14.dp, bottom = 8.dp)
            )

            SettingsCard {
                SettingsItem(
                    icon = painterResource(id = R.drawable.ic_pencil),
                    title = "Edit Profil",
                    onClick = { /* Handle click */ }
                )
                Divider(modifier = Modifier.background(Primary04))
                SettingsItem(
                    icon = painterResource(id = R.drawable.ic_baby),
                    title = "Edit Data Anak",
                    onClick = { /* Handle click */ }
                )
                Divider(modifier = Modifier.background(Primary04))
                SettingsItem(
                    icon = painterResource(id = R.drawable.ic_archive),
                    title = "XXXXXX",
                    onClick = { /* Handle click */ }
                )
                Divider(modifier = Modifier.background(Primary04))
                SettingsItem(
                    icon = painterResource(id = R.drawable.ic_lock_2),
                    title = "Ganti Kata Sandi",
                    onClick = { /* Handle click */ }
                )
            }

            Spacer(modifier = Modifier.height(20.dp))

            // Other settings section
            Text(
                text = "Lainnya",
                fontSize = 13.sp,
                fontFamily = FontFamily(Font(R.font.poppins_regular)),
                color = Primary06,
                modifier = Modifier.padding(start = 14.dp, bottom = 8.dp)
            )

            SettingsCard {
                SettingsItem(
                    icon = painterResource(id = R.drawable.ic_info),
                    title = "Tentang Aplikasi",
                    onClick = { /* Handle click */ }
                )
                Divider(modifier = Modifier.background(Primary04))
                SettingsItem(
                    icon = painterResource(id = R.drawable.ic_privacy),
                    title = "Kebijakan Privasi",
                    onClick = { /* Handle click */ }
                )
                Divider(modifier = Modifier.background(Primary04))
                SettingsItem(
                    icon = painterResource(id = R.drawable.ic_sign_out),
                    title = "Keluar",
                    onClick = { /* Handle click */ }
                )
                Divider(modifier = Modifier.background(Primary04))
                SettingsItem(
                    icon = painterResource(id = R.drawable.ic_trash),
                    title = "Hapus Akun",
                    onClick = { /* Handle click */ }
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AccountSettingsScreenPreview() {
    MaterialTheme {
        ProfileScreen()
    }
}