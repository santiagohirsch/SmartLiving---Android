package com.example.myapplication

import DevicesScreen
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.myapplication.ui.components.DeviceCard
import com.example.myapplication.ui.components.devices.Lamp
import com.example.myapplication.ui.screens.LandingScreen
import com.example.myapplication.ui.screens.RoutinesScreen

@Composable
fun AppNavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Screen.FirstScreen.route
    ) {
        composable(Screen.FirstScreen.route) {
            LandingScreen(navController)
        }
        composable(Screen.SecondScreen.route) {
            DevicesScreen(navController = navController)
        }
        composable(Screen.ThirdScreen.route) {
            RoutinesScreen()
        }
        composable(Screen.DeviceScreen.route){
            Lamp()
        }
    }
}
