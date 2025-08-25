package com.example.hology

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.hology.data.repository.AuthRepositoryImpl
import com.example.hology.domain.usecase.AuthUseCase
import com.example.hology.ui.auth.AuthViewModel
import com.example.hology.ui.auth.LoginScreen
import com.example.hology.ui.auth.RegisterScreen
import com.example.hology.ui.theme.HologyTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            HologyTheme {

                // insiate auth
                val authRepo = AuthRepositoryImpl()
                val authUseCase = AuthUseCase(authRepo)
                val authViewModel: AuthViewModel = viewModel(factory = AuthViewModel.Factory(authUseCase))

                Scaffold(modifier = Modifier.fillMaxSize()) { innerpadding ->
                    NavHost(
                        navController = navController,
                        startDestination = "login"
                    ) {
                        composable("login") {
                            LoginScreen(viewModel = authViewModel, navController = navController)
                        }

                        composable("register") {
                            RegisterScreen(viewModel = authViewModel, navController = navController)
                        }
                    }
                }
            }
        }
    }
}
