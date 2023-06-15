package com.example.myapplication.ui.components

import DevicesScreen
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
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
import androidx.navigation.NavHostController
import com.example.myapplication.ui.components.devices.Ac
import com.example.myapplication.ui.components.devices.Door
import com.example.myapplication.ui.components.devices.Lamp
import com.example.myapplication.ui.components.devices.Vacuum
import com.example.myapplication.util.devicesrep.CurrentDevices
import com.example.myapplication.util.devicesvm.DeviceViewModel
import com.example.myapplication.util.devicesvm.LampViewModel

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
        FullscreenDialog(device = device, navController = navController)
    }
}

@Composable
fun FullscreenDialog(device: DeviceViewModel, navController: NavHostController) {
    var back by remember { mutableStateOf(false) }
    val currentDevices: CurrentDevices = CurrentDevices()
    IconButton(
        onClick = {
            back = true
        }
    ) {
        Icon(imageVector = Icons.Outlined.KeyboardArrowLeft, contentDescription = "")
    }
    //navController.navigate("d_screen")
    currentDevices.ViewDevice(device)
    if(back) {
        DevicesScreen(navController = navController)
        navController.navigate("devices_screen")
    }
}

