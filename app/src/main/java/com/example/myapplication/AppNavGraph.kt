package com.example.myapplication

import DevicesScreen
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.myapplication.ui.screens.LandingScreen
import com.example.myapplication.ui.screens.RoutinesScreen

@Composable
fun AppNavGraph(
    windowSizeClass: WindowSizeClass,
    navController: NavHostController
) {
    NavHost(
        navController = navController,
        startDestination = Screen.FirstScreen.route
    ) {
        val isPhone =
        when (windowSizeClass.widthSizeClass) {
            WindowWidthSizeClass.Compact -> {
                true
            }
            else -> {
                false
            }
        }

        composable(Screen.FirstScreen.route) {
            LandingScreen(navController, isPhone = isPhone)
        }
        composable(Screen.SecondScreen.route) {
            DevicesScreen()
        }
        composable(Screen.ThirdScreen.route) {
            RoutinesScreen()
        }
    }
}
