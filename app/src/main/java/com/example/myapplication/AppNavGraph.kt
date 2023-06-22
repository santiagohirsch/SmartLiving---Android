package com.example.myapplication

import DevicesScreen
import android.content.res.Configuration
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalConfiguration
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.myapplication.ui.screens.LandingScreen
import com.example.myapplication.ui.screens.RoutinesScreen
import com.example.myapplication.util.devicesvm.DevicesViewModel
import com.example.myapplication.util.devicesvm.RoutinesViewModel

@Composable
fun AppNavGraph(
    windowSizeClass: WindowSizeClass,
    navController: NavHostController, devicesViewModel: DevicesViewModel, routinesViewModel: RoutinesViewModel
) {
    NavHost(
        navController = navController,
        startDestination = Screen.FirstScreen.route
    ) {
        @Composable
        fun isTablet(): Boolean {
            val configuration = LocalConfiguration.current
            return if (configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) {

                configuration.screenWidthDp > 900
            } else {
                configuration.screenWidthDp > 600
            }
        }

        composable(Screen.FirstScreen.route) {
            LandingScreen(navController, isPhone = !isTablet())
        }
        composable(Screen.SecondScreen.route) {
            DevicesScreen(devicesViewModel, isPhone = !isTablet())
        }
        composable(Screen.ThirdScreen.route) {
            RoutinesScreen(routinesViewModel)
        }
    }
}
