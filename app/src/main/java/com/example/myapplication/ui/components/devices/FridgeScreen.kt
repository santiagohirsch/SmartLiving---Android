package com.example.myapplication.ui.components.devices

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.myapplication.util.devicesvm.DeviceViewModel
import com.example.myapplication.util.devicesvm.RefrigeratorViewModel

@Preview
@Composable
fun Fridge(fridgeViewModel: RefrigeratorViewModel = viewModel()){
    val uiState by fridgeViewModel.uiState.collectAsState()
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .padding(top = 90.dp),
        //verticalArrangement = Arrangement.Bottom,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .height(50.dp)
                .width(350.dp)
                .clip(
                    shape = RoundedCornerShape(
                        topStart = 22.dp,
                        topEnd = 22.dp
                    )
                )
                .background(color = Color.Gray)
        ) {
            Row() {
                Text(text = "Modo: " + uiState.state.mode, modifier = Modifier.padding(start = 15.dp))
            }
        }
        Spacer(modifier = Modifier.height(5.dp))
        Row() {
            Box(
                modifier = Modifier
                    .width(172.dp)
                    .height(200.dp)
                    .background(color = Color.Gray)
            ) {
                Text(text = "Temp. freezer " + uiState.state.freezerTemperature, modifier = Modifier.padding(start = 15.dp))
            }
            Spacer(modifier = Modifier.width(5.dp))
            Box(
                modifier = Modifier
                    .width(172.dp)
                    .height(200.dp)
                    .background(color = Color.Gray)
            ) {
                Text(text = "Temp. heladera " + uiState.state.temperature, modifier = Modifier.padding(start = 15.dp))
            }
        }
        Spacer(modifier = Modifier.height(5.dp))
        Box(
            modifier = Modifier
                .width(350.dp)
                .height(50.dp)
                .clip(
                    shape = RoundedCornerShape(
                        bottomStart = 22.dp,
                        bottomEnd = 22.dp
                    )
                )
                .background(color = Color.Gray)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Button(
                    onClick = {
                        fridgeViewModel.setMode(fridgeViewModel.id.toString(), "vacation")
                    },
                    shape = RoundedCornerShape(
                        size = 20.dp
                    )
                ) {
                    Text(text = "Cambiar modo")
                }
            }
        }
        Spacer(modifier = Modifier.height(100.dp))
        Row() {
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Button(
                    onClick = {
                        fridgeViewModel.setFreezerTemperature(fridgeViewModel.id.toString(),uiState.state.freezerTemperature + 1)
                    },
                    shape = RoundedCornerShape(
                        topStart = 20.dp,
                        topEnd = 20.dp
                    )
                ) {
                    Text(text = "Subir")
                }
                Text(text = "Temp freezer")
                Button(
                    onClick = {
                        fridgeViewModel.setFreezerTemperature(fridgeViewModel.id.toString(),uiState.state.freezerTemperature - 1)
                    },
                    shape = RoundedCornerShape(
                        bottomStart = 20.dp,
                        bottomEnd = 20.dp
                    )
                ) {
                    Text(text = "Bajar")
                }
            }
            Spacer(modifier = Modifier.width(65.dp))
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Button(
                    onClick = {
                        fridgeViewModel.setTemperature(fridgeViewModel.id.toString(),uiState.state.temperature + 1)
                    },
                    shape = RoundedCornerShape(
                        topStart = 20.dp,
                        topEnd = 20.dp
                    )
                ) {
                    Text(text = "Subir")
                }
                Text(text = "Temp heladera")
                Button(
                    onClick = {
                        fridgeViewModel.setTemperature(fridgeViewModel.id.toString(),uiState.state.temperature - 1)
                    },
                    shape = RoundedCornerShape(
                        bottomStart = 20.dp,
                        bottomEnd = 20.dp
                    )
                ) {
                    Text(text = "Bajar")
                }
            }
        }
    }
}