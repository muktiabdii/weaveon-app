package com.example.weaveon

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import com.example.weaveon.presentation.ui.screens.LoginScreen
import com.example.weaveon.presentation.ui.theme.WeaveOnTheme
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.weaveon.presentation.ui.screens.ForgotPasswordScreen
import com.example.weaveon.presentation.ui.screens.RegisterScreen
import com.example.weaveon.presentation.ui.screens.ResetPasswordScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WeaveOnTheme {
                val navController = rememberNavController()
                NavHost(navController = navController, startDestination = "login") {

                    composable("login") {
                        LoginScreen(
                            onForgotPasswordClick = { navController.navigate("forgot-password") },
                            onLoginClick = { /* Handle login */ },
                            onRegisterClick = { navController.navigate("register") },
                            onGoogleLoginClick = { /* Handle Google login */ },
                            onFacebookLoginClick = { /* Handle Facebook login */ },
                            onBackClick = { navController.popBackStack() }
                        )
                    }

                    composable("register") {
                        RegisterScreen(
                            onRegisterClick = { navController.navigate("login") },
                            onLoginClick = { navController.navigate("login") },
                            onGoogleRegisterClick = { /* Handle Google register */ },
                            onFacebookRegisterClick = { /* Handle Facebook register */ },
                            onBackClick = { navController.popBackStack() }
                        )
                    }

                    composable("forgot-password") {
                        ForgotPasswordScreen(
                            onSubmitClick = { navController.navigate("reset-password") },
                            onBackClick = { navController.popBackStack() }
                        )
                    }

                    composable("reset-password") {
                        ResetPasswordScreen(
                            onResetClick = { navController.navigate("login") },
                            onBackClick = { navController.popBackStack() }
                        )
                    }
                }
            }
        }
    }
}

