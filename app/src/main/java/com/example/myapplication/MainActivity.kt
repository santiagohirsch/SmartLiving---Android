package com.example.myapplication


import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.content.IntentFilter
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.*
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.myapplication.MyIntent.Companion.DEVICE_ID
import com.example.myapplication.ui.theme.SmartLivingTheme
import com.example.myapplication.util.devicesvm.DevicesViewModel
import com.example.myapplication.util.devicesvm.RoutinesViewModel
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.PermissionRequired
import com.google.accompanist.permissions.PermissionState
import com.google.accompanist.permissions.rememberPermissionState

class MainActivity : ComponentActivity() {

    private lateinit var receiver: SkipNotificationReceiver
    @OptIn(ExperimentalMaterial3WindowSizeClassApi::class, ExperimentalPermissionsApi::class)
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
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                        val permissionState =
                            rememberPermissionState(permission = Manifest.permission.POST_NOTIFICATIONS)
                        if(!permissionState.hasPermission) {
                            NotificationPermission(permissionState = permissionState)
                            LaunchedEffect(true) {
                                permissionState.launchPermissionRequest()
                            }
                        }
                    }
                    AppNavGraph(windowSizeClass = windowSizeClass, navController = navController, devicesViewModel, routinesViewModel)
                }
            }

        }
    }

    override fun onStart() {
        super.onStart()

        receiver = SkipNotificationReceiver(DEVICE_ID)
        IntentFilter(MyIntent.SHOW_NOTIFICATION)
            .apply { priority = 1 }
            .also {
                var flags = 0
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU)
                    flags = Context.RECEIVER_NOT_EXPORTED

                registerReceiver(receiver, it, flags)
            }
    }

    override fun onStop() {
        super.onStop()

        unregisterReceiver(receiver)
    }

    @OptIn(ExperimentalPermissionsApi::class)
    @Composable
    fun NotificationPermission(
        permissionState: PermissionState,
    ) {
        PermissionRequired(
            permissionState = permissionState,
            permissionNotGrantedContent = { /* TODO: función para infromarle al usuario de la necesidad de otrogar el permiso */ },
            permissionNotAvailableContent = { /* TODO: función hacer las adecuaciones a la App debido a que el permiso no fue otorgado  */ }
        ) {
            /* Hacer uso del recurso porque el permiso fue otorgado */
        }
    }

    companion object {
        // TODO: valor fijo, cambiar por un valor de dispositivo válido.
        private const val DEVICE_ID = "21e9ef5348c462ef"
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


