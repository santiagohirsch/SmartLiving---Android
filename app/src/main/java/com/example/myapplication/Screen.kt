package com.example.myapplication

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.ui.graphics.vector.ImageVector

sealed class Screen(val title: String, val icon: ImageVector, val route: String) {
    object FirstScreen: Screen("Home", Icons.Filled.Home, "landing_screen")
    object SecondScreen: Screen("Devices", Icons.Filled.List, "devices_screen")
    object ThirdScreen: Screen("Routines", Icons.Filled.Face, "routines_screen")
    object DeviceScreen: Screen("Cualquier cosa", Icons.Filled.Add, "d_screen")
}
