package com.example.myapplication

import DevicesScreen
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.myapplication.ui.screens.LandingScreen
import com.example.myapplication.ui.screens.RoutinesScreen
import com.example.myapplication.util.devicesvm.DevicesViewModel

@Composable
fun AppNavGraph(navController: NavHostController, devicesViewModel: DevicesViewModel) {
    NavHost(
        navController = navController,
        startDestination = Screen.FirstScreen.route
    ) {
        composable(Screen.FirstScreen.route) {
            LandingScreen(navController)
        }
        composable(Screen.SecondScreen.route) {
            DevicesScreen(devicesViewModel)
        }
        composable(Screen.ThirdScreen.route) {
            RoutinesScreen()
        }
    }
}
