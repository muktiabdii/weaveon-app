package com.example.hology.ui.home

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.hology.ui.common.BottomNavBar
import com.example.hology.ui.profile.ProfileScreen

@Composable
fun MainScreen() {
    val navController = rememberNavController()
    val navBackStackEntry = navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry.value?.destination?.route

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
            composable("home") { HomeScreen() }
            composable("report") { ReportScreen() }
            composable("profile") { ProfileScreen() }
        }
    }
}
