package com.example.hology

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.hology.data.datastore.ExercisePreferencesManager
import com.example.hology.data.datastore.UserPreferencesManager
import com.example.hology.data.datastore.WevyPreferencesManager
import com.example.hology.data.repository.AuthRepositoryImpl
import com.example.hology.data.repository.UserRepositoryImpl
import com.example.hology.domain.usecase.AuthUseCase
import com.example.hology.domain.usecase.OnBoardingUseCase
import com.example.hology.domain.usecase.UserUseCase
import com.example.hology.ui.auth.AuthViewModel
import com.example.hology.ui.auth.ForgotPasswordScreen
import com.example.hology.ui.auth.LoginScreen
import com.example.hology.ui.auth.RegisterScreen
import com.example.hology.ui.home.MainScreen
import com.example.hology.ui.onboarding.OnBoardingScreen
import com.example.hology.ui.onboarding.WelcomeScreen
import com.example.hology.ui.splash.SplashScreen
import com.example.hology.ui.splash.SplashViewModel
import com.example.hology.ui.theme.HologyTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            HologyTheme {

                // insiate user
                val userRepo = UserRepositoryImpl(
                    UserPreferencesManager(this),
                    ExercisePreferencesManager(this),
                    WevyPreferencesManager(this),
                    this
                )
                val userUseCase = UserUseCase(userRepo)

                // insiate on boarding
                val onBoardingUseCase = OnBoardingUseCase(UserPreferencesManager(this))
                val splashViewModel: SplashViewModel = viewModel(factory = SplashViewModel.Factory(userUseCase, onBoardingUseCase))

                // insiate auth
                val authRepo = AuthRepositoryImpl()
                val authUseCase = AuthUseCase(authRepo)
                val authViewModel: AuthViewModel = viewModel(factory = AuthViewModel.Factory(authUseCase, userUseCase))


                Scaffold(modifier = Modifier.fillMaxSize()) { innerpadding ->
                    NavHost(
                        navController = navController,
                        startDestination = "splash"
                    ) {
                        composable("splash") {
                            SplashScreen(navController = navController, viewModel = splashViewModel)
                        }

                        composable("onboarding") {
                            OnBoardingScreen(navController = navController, viewModel = splashViewModel)
                        }

                        composable("welcome") {
                            WelcomeScreen(navController = navController)
                        }

                        composable("login") {
                            LoginScreen(viewModel = authViewModel, navController = navController)
                        }

                        composable("register") {
                            RegisterScreen(viewModel = authViewModel, navController = navController)
                        }

                        composable("forgot_password") {
                            ForgotPasswordScreen(viewModel = authViewModel, navController = navController)
                        }

                        composable("main") {
                            MainScreen(rootNavController = navController)
                        }
                    }
                }
            }
        }
    }
}
