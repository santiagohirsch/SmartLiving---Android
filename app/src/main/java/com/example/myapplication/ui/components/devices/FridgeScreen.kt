package com.example.myapplication.ui.components.devices

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.myapplication.R
import com.example.myapplication.util.devicesvm.DeviceViewModel
import com.example.myapplication.util.devicesvm.RefrigeratorViewModel

@Preview
@Composable
fun Fridge(fridgeViewModel: RefrigeratorViewModel = viewModel()){
    var changeMode by remember {
        mutableStateOf(false)
    }
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
                Text(text = stringResource(R.string.mode) + uiState.state.mode, modifier = Modifier.padding(start = 15.dp,top = 5.dp), fontSize = 23.sp)
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
                Text(text = stringResource(R.string.temp_freezer), modifier = Modifier.padding(start = 10.dp), fontSize = 20.sp)
                Text(text = uiState.state.freezerTemperature.toString() + "°C", modifier = Modifier.padding(start = 15.dp,top = 50.dp), fontSize = 56.sp)
            }
            Spacer(modifier = Modifier.width(5.dp))
            Box(
                modifier = Modifier
                    .width(172.dp)
                    .height(200.dp)
                    .background(color = Color.Gray)
            ) {
                Text(text = stringResource(R.string.temp_fridge), modifier = Modifier.padding(start = 15.dp), fontSize = 20.sp)
                Text(text = uiState.state.temperature.toString() + "°C", modifier = Modifier.padding(start = 21.dp,top = 50.dp), fontSize = 56.sp)
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
                        changeMode = true
                    },
                    shape = RoundedCornerShape(
                        size = 20.dp
                    )
                ) {
                    Text(text = stringResource(R.string.change_mode_msg),color = Color.White)
                }

                if (changeMode) {
                    Dialog(onDismissRequest = { changeMode = false }) {
                        Box(modifier = Modifier
                            .background(color = Color.Black)
                            .height(200.dp)
                            .fillMaxWidth()
                        ) {
                            Column(
                                modifier = Modifier.fillMaxSize(),
                                verticalArrangement = Arrangement.Center,
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                Button(onClick = {
                                    fridgeViewModel.setMode(fridgeViewModel.id.toString(), "default")
                                    changeMode = false
                                }) {
                                    Text(text = "Seleccionar modo default", color = Color.White)
                                }
                                Button(onClick = {
                                    fridgeViewModel.setMode(fridgeViewModel.id.toString(), "vacation")
                                    changeMode = false
                                }) {
                                    Text(text = "Seleccionar modo vacaciones", color = Color.White)
                                }
                                Button(onClick = {
                                    fridgeViewModel.setMode(fridgeViewModel.id.toString(), "party")
                                    changeMode = false
                                }) {
                                    Text(text = "Seleccionar modo fiesta", color = Color.White)
                                }
                            }
                        }

                    }
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
                        if (uiState.state.freezerTemperature < -8) {
                            fridgeViewModel.setFreezerTemperature(fridgeViewModel.id.toString(),uiState.state.freezerTemperature + 1)
                        }
                    },
                    shape = RoundedCornerShape(
                        topStart = 20.dp,
                        topEnd = 20.dp
                    )
                ) {
                    Text(text = stringResource(R.string.increase),color = Color.White)
                }
                Text(text = stringResource(R.string.temp_freezer))
                Button(
                    onClick = {
                        if (uiState.state.freezerTemperature > -20) {
                            fridgeViewModel.setFreezerTemperature(fridgeViewModel.id.toString(),uiState.state.freezerTemperature - 1)
                        }                    },
                    shape = RoundedCornerShape(
                        bottomStart = 20.dp,
                        bottomEnd = 20.dp
                    )
                ) {
                    Text(text = stringResource(R.string.decrease),color = Color.White)
                }
            }
            Spacer(modifier = Modifier.width(65.dp))
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Button(
                    onClick = {
                        if (uiState.state.temperature < 8) {
                            fridgeViewModel.setTemperature(fridgeViewModel.id.toString(),uiState.state.temperature + 1)
                        }
                    },
                    shape = RoundedCornerShape(
                        topStart = 20.dp,
                        topEnd = 20.dp
                    )
                ) {
                    Text(text = stringResource(R.string.increase),color = Color.White)
                }
                Text(text = stringResource(R.string.temp_fridge))
                Button(
                    onClick = {
                        if (uiState.state.temperature > 2) {
                            fridgeViewModel.setTemperature(fridgeViewModel.id.toString(),uiState.state.temperature - 1)
                        }                    },
                    shape = RoundedCornerShape(
                        bottomStart = 20.dp,
                        bottomEnd = 20.dp
                    )
                ) {
                    Text(text = stringResource(R.string.decrease),color = Color.White)
                }
            }
        }
    }
}