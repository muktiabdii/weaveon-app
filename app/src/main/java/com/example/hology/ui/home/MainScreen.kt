package com.example.hology.ui.home

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.hology.data.datastore.PreferencesManager
import com.example.hology.data.repository.AuthRepositoryImpl
import com.example.hology.data.repository.UserRepositoryImpl
import com.example.hology.domain.usecase.AuthUseCase
import com.example.hology.domain.usecase.UserUseCase
import com.example.hology.ui.auth.AuthViewModel
import com.example.hology.ui.auth.ForgotPasswordScreen
import com.example.hology.ui.common.BottomNavBar
import com.example.hology.ui.profile.EditProfileScreen
import com.example.hology.ui.profile.ProfileScreen
import com.example.hology.ui.profile.UserViewModel

@Composable
fun MainScreen() {
    val navController = rememberNavController()
    val navBackStackEntry = navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry.value?.destination?.route

    val context = LocalContext.current

    // initiate user
    val userRepo = UserRepositoryImpl(PreferencesManager(context))
    val userUseCase = UserUseCase(userRepo)
    val userViewModel: UserViewModel = viewModel(factory = UserViewModel.Factory(userUseCase))

    // insiate auth
    val authRepo = AuthRepositoryImpl()
    val authUseCase = AuthUseCase(authRepo)
    val authViewModel: AuthViewModel = viewModel(factory = AuthViewModel.Factory(authUseCase, userUseCase))

    // initiate BottomNavBar
    Scaffold(
        bottomBar = {
            if (currentRoute in listOf("home", "report", "profile")) {
                BottomNavBar(navController = navController, selected = currentRoute ?: "home")
            }
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = "home",
            modifier = Modifier.padding(innerPadding)
        ) {
            composable("home") {
                HomeScreen()
            }

            composable("report") {
                ReportScreen()
            }

            composable("profile") {
                ProfileScreen(viewModel = userViewModel, navController = navController)
            }

            composable("edit_profile") {
                EditProfileScreen(navController = navController, viewModel = userViewModel)
            }

            composable("forgot_password") {
                ForgotPasswordScreen(viewModel = authViewModel, navController = navController)
            }
        }
    }
}
