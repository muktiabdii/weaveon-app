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
import com.example.hology.data.datastore.ExercisePreferencesManager
import com.example.hology.data.datastore.UserPreferencesManager
import com.example.hology.data.datastore.WevyPreferencesManager
import com.example.hology.data.remote.api.CloudinaryService
import com.example.hology.data.repository.ExerciseRepositoryImpl
import com.example.hology.data.repository.UserRepositoryImpl
import com.example.hology.data.repository.WevyRepositoryImpl
import com.example.hology.domain.usecase.ExerciseUseCase
import com.example.hology.domain.usecase.UserUseCase
import com.example.hology.domain.usecase.WevyUseCase
import com.example.hology.ui.common.BottomNavBar
import com.example.hology.ui.exercise.ExerciseActivityScreen
import com.example.hology.ui.exercise.ExerciseDetailScreen
import com.example.hology.ui.exercise.ExerciseScreen
import com.example.hology.ui.exercise.ExerciseViewModel
import com.example.hology.ui.exercise.JejakExerciseScreen
import com.example.hology.ui.profile.EditProfileScreen
import com.example.hology.ui.profile.ProfileScreen
import com.example.hology.ui.profile.UserViewModel
import com.example.hology.ui.report.ReportScreen
import com.example.hology.ui.report.ReportViewModel
import com.example.hology.ui.wevy.WevyActivityScreen
import com.example.hology.ui.wevy.WevyDetailScreen
import com.example.hology.ui.wevy.WevyRecordScreen
import com.example.hology.ui.wevy.WevyResultScreen
import com.example.hology.ui.wevy.WevyScreen
import com.example.hology.ui.wevy.WevyViewModel

@Composable
fun MainScreen(rootNavController: NavController) {
    val navController = rememberNavController()
    val navBackStackEntry = navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry.value?.destination?.route

    val context = LocalContext.current

    // initiate user
    val userRepo = UserRepositoryImpl(
        preferencesManager = UserPreferencesManager(context)
    )
    val userUseCase = UserUseCase(
        userRepository = userRepo
    )
    val userViewModel: UserViewModel = viewModel(
        factory = UserViewModel.Factory(userUseCase)
    )

    // initiate exercise
    val exerciseRepo = ExerciseRepositoryImpl(
        service = CloudinaryService.instance,
        context = context,
        preferences = ExercisePreferencesManager(context)
    )
    val exerciseUseCase = ExerciseUseCase(
        repository = exerciseRepo
    )
    val exerciseViewModel: ExerciseViewModel = viewModel(
        factory = ExerciseViewModel.Factory(exerciseUseCase)
    )

    // initiate wevy
    val wevyRepo = WevyRepositoryImpl(
        preferences = WevyPreferencesManager(context),
    )
    val wevyUseCase = WevyUseCase(
        repository = wevyRepo
    )
    val wevyViewModel: WevyViewModel = viewModel(
        factory = WevyViewModel.Factory(wevyUseCase)
    )

    // initiate report
    val reportViewModel: ReportViewModel = viewModel(
        factory = ReportViewModel.Factory(wevyUseCase)
    )

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
                HomeScreen(navController = navController, viewModel = exerciseViewModel)
            }

            composable("report") {
                ReportScreen(navController = navController, viewModel = reportViewModel)
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
                ExerciseDetailScreen(navController, exerciseId, exerciseViewModel)
            }

            composable("exercise_activity/{exerciseId}/{activityId}") { backStackEntry ->
                val exerciseId = backStackEntry.arguments?.getString("exerciseId") ?: ""
                val activityId = backStackEntry.arguments?.getString("activityId") ?: ""
                ExerciseActivityScreen(navController, exerciseId, activityId, exerciseViewModel)
            }

            composable("jejak-exercise") {
                JejakExerciseScreen(navController = navController, viewModel = exerciseViewModel)
            }

            composable("wevy") {
                WevyScreen(navController = navController)
            }

            composable ("wevy_detail/{wevyId}") { backStackEntry ->
                val wevyId = backStackEntry.arguments?.getString("wevyId") ?: ""
                WevyDetailScreen(navController, wevyId, wevyViewModel)
            }

            composable("wevy_activity/{wevyId}/{activityId}") {
                val wevyId = it.arguments?.getString("wevyId") ?: ""
                val activityId = it.arguments?.getString("activityId") ?: ""
                WevyActivityScreen(navController, wevyId, activityId)
            }

            composable("wevy_record/{wevyId}/{activityId}") {
                val wevyId = it.arguments?.getString("wevyId") ?: ""
                val activityId = it.arguments?.getString("activityId") ?: ""
                WevyRecordScreen(viewModel = wevyViewModel, navController = navController, wevyId, activityId)
            }

            composable("wevy_result/{wevyId}/{activityId}") {
                val wevyId = it.arguments?.getString("wevyId") ?: ""
                val activityId = it.arguments?.getString("activityId") ?: ""
                WevyResultScreen(navController = navController, wevyId, activityId, wevyViewModel)
            }
        }
    }
}
