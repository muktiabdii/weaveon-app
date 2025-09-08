package com.example.hology.ui.home

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.hology.data.datastore.PreferencesManager
import com.example.hology.data.remote.api.CloudinaryConfig
import com.example.hology.data.remote.api.CloudinaryService
import com.example.hology.data.repository.AuthRepositoryImpl
import com.example.hology.data.repository.ExerciseRepositoryImpl
import com.example.hology.data.repository.UserRepositoryImpl
import com.example.hology.domain.usecase.AuthUseCase
import com.example.hology.domain.usecase.ExerciseUseCase
import com.example.hology.domain.usecase.UserUseCase
import com.example.hology.ui.auth.AuthViewModel
import com.example.hology.ui.auth.ForgotPasswordScreen
import com.example.hology.ui.auth.LoginScreen
import com.example.hology.ui.common.BottomNavBar
import com.example.hology.ui.exercise.ExerciseActivityScreen
import com.example.hology.ui.exercise.ExerciseDetailScreen
import com.example.hology.ui.exercise.ExerciseScreen
import com.example.hology.ui.exercise.ExerciseViewModel
import com.example.hology.ui.profile.EditProfileScreen
import com.example.hology.ui.profile.ProfileScreen
import com.example.hology.ui.profile.UserViewModel
import com.example.hology.ui.wevy.WevyActivityScreen
import com.example.hology.ui.wevy.WevyDetailScreen
import com.example.hology.ui.wevy.WevyScreen

@Composable
fun MainScreen(rootNavController: NavController) {
    val navController = rememberNavController()
    val navBackStackEntry = navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry.value?.destination?.route

    val context = LocalContext.current

    // initiate user
    val userRepo = UserRepositoryImpl(PreferencesManager(context))
    val userUseCase = UserUseCase(userRepo)
    val userViewModel: UserViewModel = viewModel(factory = UserViewModel.Factory(userUseCase))

    val exerciseRepo = ExerciseRepositoryImpl(CloudinaryService.instance, context)
    val exerciseUseCase = ExerciseUseCase(exerciseRepo)
    val exerciseViewModel: ExerciseViewModel = viewModel(factory = ExerciseViewModel.Factory(exerciseUseCase))

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
                HomeScreen(navController = navController)
            }

            composable("report") {
                ReportScreen()
            }

            composable("profile") {
                ProfileScreen(viewModel = userViewModel, navController = navController, rootNavController = rootNavController)
            }

            composable("edit_profile") {
                EditProfileScreen(navController = navController, viewModel = userViewModel)
            }

            composable("exercise") {
                ExerciseScreen(navController = navController)
            }

            composable("exercise") {
                ExerciseScreen(navController = navController)
            }

            composable("exercise_detail/{exerciseId}") { backStackEntry ->
                val exerciseId = backStackEntry.arguments?.getString("exerciseId") ?: ""
                ExerciseDetailScreen(navController, exerciseId)
            }

            composable("exercise_activity/{exerciseId}/{activityId}") { backStackEntry ->
                val exerciseId = backStackEntry.arguments?.getString("exerciseId") ?: ""
                val activityId = backStackEntry.arguments?.getString("activityId") ?: ""
                ExerciseActivityScreen(navController, exerciseId, activityId, exerciseViewModel)
            }

            composable("wevy") {
                WevyScreen(navController = navController)
            }

            composable ("wevy_detail/{wevyId}") { backStackEntry ->
                val wevyId = backStackEntry.arguments?.getString("wevyId") ?: ""
                WevyDetailScreen(navController, wevyId)
            }

            composable("wevy_activity/{wevyId}/{activityId}") {
                val wevyId = it.arguments?.getString("wevyId") ?: ""
                val activityId = it.arguments?.getString("activityId") ?: ""
                WevyActivityScreen(navController, wevyId, activityId)
            }
        }
    }
}
