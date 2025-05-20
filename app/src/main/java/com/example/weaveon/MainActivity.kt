package com.example.weaveon

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.weaveon.presentation.ui.components.BottomBar
import com.example.weaveon.presentation.ui.components.BottomBarItem
import com.example.weaveon.presentation.ui.screens.*
import com.example.weaveon.presentation.ui.theme.WeaveOnTheme
import com.example.weaveon.data.repoimpl.*
import com.example.weaveon.domain.usecase.*
import com.example.weaveon.presentation.viewmodel.*

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WeaveOnTheme {

                // Inisiasi repo, usecase, dan viewmodel (sama seperti kode kamu)
                val userRepo = UserRepoImpl()
                val userUseCase = UserUseCase(userRepo)
                val userViewModel : UserViewModel = viewModel(factory = UserViewModel.Factory(userUseCase))

                val chatbotRepo = ChatbotRepoImpl(applicationContext)
                val chatbotUseCase = ChatbotUseCase(chatbotRepo)
                val chatbotViewModel: ChatbotViewModel = viewModel(factory = ChatbotViewModel.ChatbotViewModelFactory(chatbotUseCase))

                val kidscoverRepo = KidscoverRepoImpl()
                val kidscoverUseCase = KidscoverUseCase(kidscoverRepo)
                val kidscoverViewModel : KidscoverViewModel = viewModel(factory = KidscoverViewModel.Factory(kidscoverUseCase))

                val navController = rememberNavController()

                val bottomBarScreens = listOf("home", "chatbot-landing", "profile")

                Scaffold(
                    bottomBar = {
                        val navBackStackEntry by navController.currentBackStackEntryAsState()
                        val currentRoute = navBackStackEntry?.destination?.route

                        if (currentRoute in bottomBarScreens) {
                            BottomBar(
                                selectedItem = when(currentRoute) {
                                    "home" -> BottomBarItem.Home
                                    "chatbot-landing" -> BottomBarItem.Aibu
                                    "profile" -> BottomBarItem.Profile
                                    else -> BottomBarItem.Aibu
                                },
                                onItemSelected = { item ->
                                    val route = when(item) {
                                        BottomBarItem.Home -> "home"
                                        BottomBarItem.Aibu -> "chatbot-landing"
                                        BottomBarItem.Profile -> "profile"
                                    }
                                    navController.navigate(route) {
                                        popUpTo(navController.graph.findStartDestination().id) {
                                            saveState = true
                                        }
                                        launchSingleTop = true
                                        restoreState = true
                                    }
                                }
                            )
                        }
                    }
                ) { innerPadding ->

                    NavHost(
                        navController = navController,
                        startDestination = "chatbot-landing",
                        modifier = Modifier.padding(innerPadding)
                    ) {

                        // BottomBar destinations
                        composable("home") {
                            HomeScreen()
                        }

                        composable("chatbot") {
                            ChatbotScreen(chatbotViewModel = chatbotViewModel)
                        }

                        composable("profile") {
                            ProfileScreen()
                        }

                        // Other screens tanpa BottomBar
                        composable("login") {
                            LoginScreen(
                                userViewModel = userViewModel,
                                onForgotPasswordClick = { navController.navigate("forgot-password") },
                                onLoginClick = { navController.navigate("chatbot") },
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

                        composable("chatbot-landing") {
                            LandingPageChatbot(
                                onAccessKidscover = { navController.navigate("kidscover-form") },
                                onSkipKidscover = { navController.navigate("chatbot") }
                            )
                        }

                        composable("kidscover-form") {
                            FormAnakScreen(
                                kidscoverViewModel = kidscoverViewModel,
                                onBackClick = { navController.popBackStack() },
                                onNextClick = { navController.navigate("chatbot") }
                            )
                        }
                    }
                }
            }
        }
    }
}
