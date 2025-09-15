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
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import com.example.hology.ui.common.ProfileHeader
import com.example.hology.ui.common.SettingsCard
import com.example.hology.ui.common.SettingsItem
import com.example.hology.ui.theme.Base
import com.example.hology.ui.theme.Primary04
import com.example.hology.ui.theme.Primary06
import com.example.hology.R
import com.example.hology.ui.common.ProfileDialog

@Composable
fun ProfileScreen(
    modifier: Modifier = Modifier,
    viewModel: UserViewModel,
    navController: NavController,
    rootNavController: NavController
) {

    val scrollState = rememberScrollState()
    val userState by viewModel.userState.collectAsState()

    var showDialogLogout by remember { mutableStateOf(false) }
    var showDialogDelete by remember { mutableStateOf(false) }

    if (showDialogLogout) {
        ProfileDialog(
            title = "Apakah anda yakin ingin keluar?",
            image = R.drawable.logout_3d,
            action = "Ya",
            onConfirmDelete = {
                viewModel.logout {
                    rootNavController.navigate("login") {
                        popUpTo(0) { inclusive = true }
                    }
                }
                showDialogLogout = false
            },
            onCancel = { showDialogLogout = false },
            onDismissRequest = { showDialogLogout = false }
        )
    }

    if (showDialogDelete) {
        ProfileDialog(
            title = "Apakah anda yakin ingin menghapus akun?",
            image = R.drawable.trash_3d,
            action = "Ya",
            onConfirmDelete = {
                viewModel.deleteAccount {
                    rootNavController.navigate("login") {
                        popUpTo(0) { inclusive = true }
                    }
                }
                showDialogDelete = false
            },
            onCancel = { showDialogDelete = false },
            onDismissRequest = { showDialogDelete = false }
        )
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Base)
            .verticalScroll(scrollState)
    ) {

        // header
        ProfileHeader(
            username = userState.name,
            email = userState.email
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
                    onClick = { navController.navigate("edit_profile") }
                )
                Divider(modifier = Modifier.background(Primary04))
                SettingsItem(
                    icon = painterResource(id = R.drawable.ic_lock_2),
                    title = "Ganti Kata Sandi",
                    onClick = {
                        rootNavController.navigate("forgot-password") {
                            popUpTo(0) { inclusive = true }
                        }
                    }
                )
                Divider(modifier = Modifier.background(Primary04))
                SettingsItem(
                    icon = painterResource(id = R.drawable.ic_archive),
                    title = "Jejak Exercise",
                    onClick = { navController.navigate("jejak-exercise") }
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
                    onClick = { showDialogLogout = true }
                )
                Divider(modifier = Modifier.background(Primary04))
                SettingsItem(
                    icon = painterResource(id = R.drawable.ic_trash),
                    title = "Hapus Akun",
                    onClick = { showDialogDelete = true }
                )
            }

            Spacer(modifier = Modifier.height(30.dp))

        }
    }
}
