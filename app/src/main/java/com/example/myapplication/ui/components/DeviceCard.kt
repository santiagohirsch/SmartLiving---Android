package com.example.myapplication.ui.components

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Clear
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material.icons.outlined.KeyboardArrowLeft
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.example.myapplication.R
import com.example.myapplication.ui.components.devices.Ac
import com.example.myapplication.ui.components.devices.Door
import com.example.myapplication.ui.components.devices.Fridge
import com.example.myapplication.ui.components.devices.Lamp
import com.example.myapplication.ui.components.devices.Vacuum
import com.example.myapplication.ui.theme.SmartLivingTheme
import com.example.myapplication.util.devicesvm.AcViewModel
import com.example.myapplication.util.devicesvm.DeviceViewModel
import com.example.myapplication.util.devicesvm.DoorViewModel
import com.example.myapplication.util.devicesvm.LampViewModel
import com.example.myapplication.util.devicesvm.RefrigeratorViewModel
import com.example.myapplication.util.devicesvm.VacuumViewModel

@Composable
fun DeviceCard(
    device: DeviceViewModel,
    modifier: Modifier = Modifier,
) {
    var showDialog by remember { mutableStateOf(false) }

    Card(
        modifier = modifier
            .padding(5.dp)
            .width(140.dp)
            .clickable { showDialog = true }
            .height(200.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 5.dp)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .clip(RoundedCornerShape(5.dp))
        ) {
            Image(
                painter = painterResource(device.img),
                contentDescription = null,
                modifier = Modifier
                    .padding(top = 12.dp, bottom = 6.dp)
                    .clip(RoundedCornerShape(5.dp))
                    .size(112.dp),
                contentScale = ContentScale.Crop
            )
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f) // Allocate remaining space to the second element
            ) {
                Text(
                    text = device.name.toString(),
                    textAlign = TextAlign.Center,
                    fontSize = 20.sp,
                    modifier = modifier
                        .clip(RoundedCornerShape(5.dp))
                    //style = MaterialTheme.typography.headlineSmall
                )
            }
        }
    }

    if (showDialog) {
        val landscape = LocalConfiguration.current.orientation == Configuration.ORIENTATION_LANDSCAPE
        CustomDialog(device = device, onDismiss = {showDialog = false}, landscape = landscape)
    }
}



@Composable
fun CustomDialog(device: DeviceViewModel,onDismiss: ()-> Unit, landscape: Boolean) {
    var showConfirmation by remember {
        mutableStateOf(false)
    }
    if(!landscape) {
        Dialog(
            onDismissRequest = { onDismiss() },
            content = {
                Box(
                    modifier = Modifier
                        .background(colorResource(R.color.white))
                        .height(680.dp)
                ) {
                    IconButton(
                        onClick = {
                            onDismiss()
                        }
                    ) {
                        Icon(imageVector = Icons.Outlined.Clear, contentDescription = "")
                    }
                    IconButton(
                        onClick = {
                            showConfirmation = true
                        },
                        modifier = Modifier.padding(start = 265.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Outlined.Delete,
                            contentDescription = "",
                            modifier = Modifier.size(30.dp)
                        )
                    }
                    ViewDevice(device, landscape)
                }
            }
        )
    }
    else{
        Dialog(
            onDismissRequest = { onDismiss() },
            properties = DialogProperties(usePlatformDefaultWidth = false),
            content = {
                Box(
                    modifier = Modifier
                        .background(colorResource(R.color.primary_button))
                        .width(650.dp)
                        .fillMaxHeight()
                ) {
                    IconButton(
                        onClick = {
                            onDismiss()
                        }
                    ) {
                        Icon(imageVector = Icons.Outlined.Clear, contentDescription = "")
                    }
                    IconButton(
                        onClick = {
                            device.delete(device.id.toString())
                            onDismiss()
                        },
                        modifier = Modifier.padding(start = 605.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Outlined.Delete,
                            contentDescription = "",
                            modifier = Modifier.size(30.dp)
                        )
                    }
                    ViewDevice(device, landscape)
                }
            }
        )
    }

    if(showConfirmation) {
        Dialog(onDismissRequest = { showConfirmation = false }) {
            Box(
                modifier = Modifier
                    .width(300.dp)
                    .height(100.dp)
                    .background(color = Color.White)
            )
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(text = "Estas seguro que deseas borrar este dispositivo?")
                Row() {

                    Button(onClick = { showConfirmation = false
                    }, modifier = Modifier
                        .padding(all = 5.dp)
                    ) {
                        Text(text = "Cancelar", color = Color.White)
                    }
                    Button(onClick = {
                        showConfirmation = false
                        device.delete(device.id.toString())
                        onDismiss()
                    },
                        modifier = Modifier
                            .padding(all = 5.dp)
                    ) {
                        Text(text = "Confirmar", color = Color.White)
                    }
                }
            }
        }
    }
}

@Composable
fun ViewDevice(device: DeviceViewModel, landscape: Boolean) {
    when(device.type) {
        "ac" -> Ac(acViewModel = device as AcViewModel, landscape = landscape)
        "lamp" -> Lamp(lampViewModel = device as LampViewModel, landscape = landscape)
        "refrigerator" -> Fridge(fridgeViewModel = device as RefrigeratorViewModel, landscape = landscape)
        "door" -> Door(doorViewModel = device as DoorViewModel, landscape = landscape)
        "vacuum" -> Vacuum(vacuumViewModel = device as VacuumViewModel, landscape = landscape)
        else -> throw IllegalArgumentException("Unknown device type")
    }
}
