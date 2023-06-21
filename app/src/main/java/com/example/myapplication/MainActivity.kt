package com.example.myapplication


import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.*
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.myapplication.ui.theme.SmartLivingTheme
import com.example.myapplication.util.devicesvm.DevicesViewModel
import com.example.myapplication.util.devicesvm.RoutinesViewModel

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
    @SuppressLint("UnusedMaterialScaffoldPaddingParameter")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val deviceId = intent.getIntExtra("deviceid", -1)

        setContent {
            val devicesViewModel: DevicesViewModel = viewModel()
            val routinesViewModel: RoutinesViewModel = viewModel()
            val windowSizeClass = calculateWindowSizeClass(this)
            SmartLivingTheme (
                windowSizeClass = windowSizeClass
            ){
                val navController = rememberNavController()
                Scaffold(
                    bottomBar = { BottomBar(navController = navController) }
                ) {
                    AppNavGraph(windowSizeClass = windowSizeClass, navController = navController, devicesViewModel, routinesViewModel)
                }
            }
        }
    }
}
}

@Composable
fun BottomBar(navController: NavController) {
    val items = listOf(
        Screen.FirstScreen,
        Screen.SecondScreen,
        Screen.ThirdScreen
    )

    BottomNavigation {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route
        items.forEach { item ->
            BottomNavigationItem(
                //unselectedContentColor = MaterialTheme.colors.onPrimary,
                //selectedContentColor = MaterialTheme.colors.onPrimary,
                icon = { Icon(imageVector = item.icon, contentDescription = item.title) },
                label = { Text(text = item.title) },
                alwaysShowLabel = true,
                selected = currentRoute == item.route,
                onClick = {
                    navController.navigate(item.route) {
                        navController.graph.startDestinationRoute?.let { screenRoute ->
                            popUpTo(screenRoute) {
                                saveState = true
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    }
                }
            )
        }
    }
}


