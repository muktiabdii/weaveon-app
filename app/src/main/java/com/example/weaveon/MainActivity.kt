package com.example.weaveon

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.weaveon.presentation.ui.screens.LoginScreen
import com.example.weaveon.presentation.ui.theme.WeaveOnTheme
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.weaveon.data.repoimpl.UserRepoImpl
import com.example.weaveon.domain.usecase.UserUseCase
import com.example.weaveon.presentation.ui.screens.ProfileScreen
import com.example.weaveon.presentation.ui.screens.ForgotPasswordScreen
import com.example.weaveon.presentation.ui.screens.RegisterScreen
import com.example.weaveon.presentation.viewmodel.UserViewModel
import com.example.weaveon.data.repoimpl.ChatbotRepoImpl
import com.example.weaveon.domain.usecase.ChatbotUseCase
import com.example.weaveon.presentation.ui.screens.ChatbotScreen
import com.example.weaveon.presentation.ui.screens.FormAnakScreen
import com.example.weaveon.presentation.viewmodel.ChatbotViewModel
import com.example.weaveon.presentation.ui.screens.LandingPageChatbot

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WeaveOnTheme {

                // inisiasi userRepo, userUseCase, dan userViewModel
                val userRepo = UserRepoImpl()
                val userUseCase = UserUseCase(userRepo)
                val userViewModel : UserViewModel = viewModel(factory = UserViewModel.Factory(userUseCase))

                // inisiasi chatbotRepo, chatbotUseCase, dan chatbotViewModel
                val chatbotRepo = ChatbotRepoImpl(applicationContext)
                val chatbotUseCase = ChatbotUseCase(chatbotRepo)
                val chatbotViewModel: ChatbotViewModel = viewModel(factory = ChatbotViewModel.ChatbotViewModelFactory(chatbotUseCase))

                val navController = rememberNavController()
                NavHost(navController = navController, startDestination = "chatbot-landing") {

                    composable("login") {
                        LoginScreen(
                            userViewModel = userViewModel,
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
                            userViewModel = userViewModel,
                            onRegisterClick = { navController.navigate("login") },
                            onLoginClick = { navController.navigate("login") },
                            onGoogleRegisterClick = { /* Handle Google register */ },
                            onFacebookRegisterClick = { /* Handle Facebook register */ },
                            onBackClick = { navController.popBackStack() }
                        )
                    }

                    composable("forgot-password") {
                        ForgotPasswordScreen(
                            userViewModel = userViewModel,
                            onSubmitClick = { navController.navigate("login") },
                            onBackClick = { navController.popBackStack() }
                        )
                    }

                    composable("profile") {
                        ProfileScreen()
                    }

                    composable("chatbot-landing") {
                        LandingPageChatbot(
                            onAccessKidscover = { navController.navigate("kidscover-form") },
                            onSkipKidscover = { navController.navigate("chatbot") }
                        )
                    }

                    composable("chatbot") {
                        ChatbotScreen(
                            chatbotViewModel = chatbotViewModel
                        )
                    }

                    composable("kidscover-form") {
                        FormAnakScreen()
                    }
                }
            }
        }
    }
}

