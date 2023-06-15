package com.example.myapplication.ui.components

import DevicesScreen
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.KeyboardArrowLeft
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.navigation.NavHostController
import com.example.myapplication.Screen
import com.example.myapplication.ui.components.devices.Ac
import com.example.myapplication.ui.components.devices.Door
import com.example.myapplication.ui.components.devices.Fridge
import com.example.myapplication.ui.components.devices.Lamp
import com.example.myapplication.ui.components.devices.Vacuum
import com.example.myapplication.util.DevicesViewModels.DeviceViewModel
@Composable
fun DeviceCard(
    device: DeviceViewModel,
    navController: NavHostController
) {
    var showDialog by remember { mutableStateOf(false) }

    Card(
        modifier = Modifier
            .width(140.dp)
            .height(180.dp)
    ) {
        Column(modifier = Modifier.fillMaxSize()) {
                Image(
                    painter = painterResource(device.img),
                    contentDescription = null,
                    modifier = Modifier
                        .clickable { showDialog = true }
                        .width(140.dp)
                        .height(120.dp),
                    contentScale = ContentScale.Crop
                )

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)
                    .padding(horizontal = 16.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxSize(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = device.name,
                        fontSize = 18.sp,
                        modifier = Modifier.weight(1f)
                    )
                }
            }
        }
    }

    if (showDialog) {
        FullscreenDialog(type = device.type, navController = navController)
    }
}

@Composable
fun FullscreenDialog(type: String, navController: NavHostController) {
    var back by remember { mutableStateOf(false) }
    IconButton(
        onClick = {
            back = true
        }
    ) {
        Icon(imageVector = Icons.Outlined.KeyboardArrowLeft, contentDescription = "")
    }
    if(type == "lamp")
        navController.navigate("d_screen"){
            navController.graph.startDestinationRoute?.let { screenRoute ->
                popUpTo(screenRoute) {
                    saveState = true
                }
                launchSingleTop = true
                restoreState = true
            }
        }
    else if ( type == "ac")
        Ac()
    else if ( type == "refrigerator")
        navController.navigate("d_screen"){
            navController.graph.startDestinationRoute?.let { screenRoute ->
                popUpTo(screenRoute) {
                    saveState = true
                }
                launchSingleTop = true
                restoreState = true
            }
        }
    else if (type == "vacuum")
        Vacuum()
    else if (type == "door")
        Door()
    if(back){
        DevicesScreen(navController = navController)
        navController.navigate("devices_screen")
    }
}

