package com.example.myapplication.ui.components.devices

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Text
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
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.myapplication.R
import com.example.myapplication.util.devicesvm.AcViewModel
import com.example.myapplication.util.devicesvm.DeviceViewModel


@Preview
@Composable
fun Ac(acViewModel: AcViewModel = viewModel()) {
    var selectMode by remember {
        mutableStateOf(false)
    }
    var selectSpeed by remember {
        mutableStateOf(false)
    }
    var selectSwing by remember {
        mutableStateOf(false)
    }
    var selectVerticalSwing by remember {
        mutableStateOf(false)
    }
    var selectHorizontalSwing by remember {
        mutableStateOf(false)
    }
    val uiState by acViewModel.uiState.collectAsState()
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        OutlinedButton(
            onClick = {
                if (uiState.state.status == "on") {
                    acViewModel.turnOff(acViewModel.id.toString())
                } else {
                    acViewModel.turnOn(acViewModel.id.toString())
                }
            },
            shape = RoundedCornerShape(30.dp),
            modifier = Modifier
                .width(120.dp)
                .height(70.dp)
                //.offset(x = 90.dp, y = 0.dp) // Mueve la caja 50.dp hacia la derecha
                .padding(start = 10.dp, top = 5.dp, bottom = 5.dp),
            colors = ButtonDefaults.buttonColors(
                backgroundColor = Color.Red,
            ),
        ) {
            Text(
                text = stringResource(R.string.turn_on_or_off),
                color = Color.White
            )
        }
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White)
                .padding(all = 10.dp),
            contentAlignment = Alignment.Center
        ) {
            Box(
                modifier = Modifier
                    .width(300.dp)
                    .height(200.dp)
                    .clip(shape = RoundedCornerShape(size = 22.dp))
                    .background(color = Color.Black)
                    .padding(all = 10.dp)
            ) {
                Column(

                    modifier = Modifier
                        .padding(top = 5.dp),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Box(
                        modifier = Modifier
                            .background(color = Color.Gray)
                            .fillMaxWidth()
                            .height(30.dp)
                            .clip(shape = RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp))
                    ) {
                        Row() {
                            Text(
                                text = stringResource(R.string.mode) + uiState.state.mode,
                                color = Color.Black,
                                modifier = Modifier
                                    .padding(start = 10.dp),
                                fontSize = 23.sp
                            )
                        }
                    }
                    Spacer(
                        modifier = Modifier
                            .height(5.dp)
                    )
                    Row(
                        modifier = Modifier
                    ) {
                        Box(
                            modifier = Modifier
                                .background(color = Color.Gray)
                                .width(90.dp)
                                .height(95.dp)
                        ) {
                            Text(
                                text = uiState.state.status,
                                color = Color.Black,
                                modifier = Modifier
                                    .padding(start = 10.dp, top = 7.dp),
                                fontSize = 53.sp
                            )
                        }
                        Spacer(
                            modifier = Modifier
                                .width(5.dp)
                        )
                        Box(
                            modifier = Modifier
                                .background(color = Color.Gray)
                                .width(190.dp)
                                .height(95.dp)
                                .clip(
                                    shape = RoundedCornerShape(
                                        bottomStart = 20.dp,
                                        bottomEnd = 20.dp
                                    )
                                )
                        )
                        {
                            Text(
                                text = stringResource(R.string.temperature_msg),
                                color = Color.Black,
                                modifier = Modifier
                                    .padding(start = 30.dp, top = 5.dp),
                                fontSize = 20.sp,
                            )
                            Text(
                                text = uiState.state.temperature.toString() + "°C",
                                color = Color.Black,
                                modifier = Modifier
                                    .padding(start = 30.dp, top = 15.dp),
                                fontSize = 56.sp,
                            )
                        }
                    }
                    Spacer(
                        modifier = Modifier
                            .height(5.dp)
                    )
                    Row() {
                        Box(
                            modifier = Modifier
                                .height(30.dp)
                                .fillMaxWidth()
                                .background(color = Color.Gray)
                        ) {
                            Text(
                                text = stringResource(R.string.speed_msg) + uiState.state.fanSpeed,
                                color = Color.Black,
                                modifier = Modifier
                                    .padding(start = 10.dp),
                                fontSize = 23.sp
                            )
                        }
                    }
                }
            }
        }

        Button(
            onClick = {
                selectMode = true
            }
        ) {
            Text(text = stringResource(R.string.select_mode_msg))
        }
        if (selectMode) {
            Dialog(onDismissRequest = { selectMode = false }) {
                Box(
                    modifier = Modifier
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
                            acViewModel.setMode(acViewModel.id.toString(), "cool")
                            selectMode = false
                        }) {
                            Text(text = "Seleccionar modo frio")
                        }
                        Button(onClick = {
                            acViewModel.setMode(acViewModel.id.toString(), "heat")
                            selectMode = false
                        }) {
                            Text(text = "Seleccionar modo calor")
                        }
                        Button(onClick = {
                            acViewModel.setMode(acViewModel.id.toString(), "fan")
                            selectMode = false
                        }) {
                            Text(text = "Seleccionar modo ventilacion")
                        }
                    }
                }

            }
        }

        Button(
            onClick = {
                selectSpeed = true
            }
        ) {
            Text(text = stringResource(R.string.choose_speed))
        }
        if (selectSpeed) {
            Dialog(onDismissRequest = { selectSpeed = false }) {
                Box(
                    modifier = Modifier
                        .background(color = Color.Black)
                        .height(250.dp)
                        .fillMaxWidth()
                ) {
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Button(onClick = {
                            acViewModel.setFanSpeed(acViewModel.id.toString(), "auto")
                            selectSpeed = false
                        }) {
                            Text(text = "Velocidad automatica")
                        }
                        Button(onClick = {
                            acViewModel.setFanSpeed(acViewModel.id.toString(), "25")
                            selectSpeed = false
                        }) {
                            Text(text = "Velocidad baja")
                        }
                        Button(onClick = {
                            acViewModel.setFanSpeed(acViewModel.id.toString(), "50")
                            selectSpeed = false
                        }) {
                            Text(text = "Velocidad media")
                        }
                        Button(onClick = {
                            acViewModel.setFanSpeed(acViewModel.id.toString(), "75")
                            selectSpeed = false
                        }) {
                            Text(text = "Velocidad alta")
                        }
                        Button(onClick = {
                            acViewModel.setFanSpeed(acViewModel.id.toString(), "100")
                            selectSpeed = false
                        }) {
                            Text(text = "Velocidad maxima")
                        }
                    }
                }
            }
        }
        Text(
            text = "Vertical swing: " + uiState.state.verticalSwing,
            fontSize = 20.sp
        )
        Text(
            text = "Horizontal swing: " + uiState.state.horizontalSwing,
            fontSize = 20.sp
        )
        Button(
            onClick = {
                selectSwing = true
            }
        ) {
            Text(text = stringResource(R.string.blade_swing_msg))
        }

        if (selectSwing) {
            Dialog(onDismissRequest = { selectSwing = false }) {
                Box(
                    modifier = Modifier
                        .background(color = Color.Black)
                        .height(250.dp)
                        .fillMaxWidth()
                ) {
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Button(onClick = {
                            selectVerticalSwing = true
                        }) {
                            Text(text = "Aspas verticales")
                        }
                        if (selectVerticalSwing) {
                            Dialog(onDismissRequest = {
                                selectSwing = false
                                selectVerticalSwing = false
                            }) {
                                Box(
                                    modifier = Modifier
                                        .background(color = Color.Black)
                                        .height(250.dp)
                                        .fillMaxWidth()
                                ) {
                                    Column(
                                        modifier = Modifier.fillMaxSize(),
                                        verticalArrangement = Arrangement.Center,
                                        horizontalAlignment = Alignment.CenterHorizontally
                                    ) {
                                        Button(onClick = {
                                            acViewModel.setVerticalSwing(
                                                acViewModel.id.toString(),
                                                "auto"
                                            )
                                            selectVerticalSwing = false
                                            selectSwing = false
                                        }) {
                                            Text(text = "Desplazamiento automatico")
                                        }
                                        Button(onClick = {
                                            acViewModel.setVerticalSwing(
                                                acViewModel.id.toString(),
                                                "22"
                                            )
                                            selectVerticalSwing = false
                                            selectSwing = false
                                        }) {
                                            Text(text = "Desplazamiento 22º")
                                        }
                                        Button(onClick = {
                                            acViewModel.setVerticalSwing(
                                                acViewModel.id.toString(),
                                                "45"
                                            )
                                            selectVerticalSwing = false
                                            selectSwing = false
                                        }) {
                                            Text(text = "Desplazamiento 45º")
                                        }
                                        Button(onClick = {
                                            acViewModel.setVerticalSwing(
                                                acViewModel.id.toString(),
                                                "67"
                                            )
                                            selectVerticalSwing = false
                                            selectSwing = false
                                        }) {
                                            Text(text = "Desplazamiento 67º")
                                        }
                                        Button(onClick = {
                                            acViewModel.setVerticalSwing(
                                                acViewModel.id.toString(),
                                                "90"
                                            )
                                            selectVerticalSwing = false
                                            selectSwing = false
                                        }) {
                                            Text(text = "Desplazamiento 90º")
                                        }
                                    }
                                }
                            }
                        }
                        Button(onClick = {
                            selectHorizontalSwing = true
                        }) {
                            Text(text = "Aspas horizontales")
                        }
                        if (selectHorizontalSwing) {
                            Dialog(onDismissRequest = {
                                selectSwing = false
                                selectHorizontalSwing = false
                            }) {
                                Box(
                                    modifier = Modifier
                                        .background(color = Color.Black)
                                        .height(285.dp)
                                        .fillMaxWidth()
                                ) {
                                    Column(
                                        modifier = Modifier.fillMaxSize(),
                                        verticalArrangement = Arrangement.Center,
                                        horizontalAlignment = Alignment.CenterHorizontally
                                    ) {
                                        Button(onClick = {
                                            acViewModel.setHorizontalSwing(
                                                acViewModel.id.toString(),
                                                "auto"
                                            )
                                            selectHorizontalSwing = false
                                            selectSwing = false
                                        }) {
                                            Text(text = "Desplazamiento automatico")
                                        }
                                        Button(onClick = {
                                            acViewModel.setHorizontalSwing(
                                                acViewModel.id.toString(),
                                                "-90"
                                            )
                                            selectHorizontalSwing = false
                                            selectSwing = false
                                        }) {
                                            Text(text = "Desplazamiento -90º")
                                        }
                                        Button(onClick = {
                                            acViewModel.setHorizontalSwing(
                                                acViewModel.id.toString(),
                                                "-45"
                                            )
                                            selectHorizontalSwing = false
                                            selectSwing = false
                                        }) {
                                            Text(text = "Desplazamiento -45º")
                                        }
                                        Button(onClick = {
                                            acViewModel.setHorizontalSwing(
                                                acViewModel.id.toString(),
                                                "0"
                                            )
                                            selectHorizontalSwing = false
                                            selectSwing = false
                                        }) {
                                            Text(text = "Desplazamiento 0º")
                                        }
                                        Button(onClick = {
                                            acViewModel.setHorizontalSwing(
                                                acViewModel.id.toString(),
                                                "45"
                                            )
                                            selectHorizontalSwing = false
                                            selectSwing = false
                                        }) {
                                            Text(text = "Desplazamiento 45º")
                                        }
                                        Button(onClick = {
                                            acViewModel.setHorizontalSwing(
                                                acViewModel.id.toString(),
                                                "90"
                                            )
                                            selectHorizontalSwing = false
                                            selectSwing = false
                                        }) {
                                            Text(text = "Desplazamiento 90º")
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }

        Button(
            shape = RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp),
            modifier = Modifier
                .padding(top = 25.dp, bottom = 5.dp),
            onClick = {
                if (uiState.state.temperature < 38) {
                    acViewModel.setTemperature(
                        acViewModel.id.toString(),
                        uiState.state.temperature + 1
                    )
                }
            }
        ) {
            Text(text = stringResource(R.string.increase))
        }
        Text(text = stringResource(R.string.temp))
        Button(
            shape = RoundedCornerShape(bottomStart = 20.dp, bottomEnd = 20.dp),
            modifier = Modifier
                .padding(top = 5.dp),
            onClick = {
                if (uiState.state.temperature > 18) {
                    acViewModel.setTemperature(
                        acViewModel.id.toString(),
                        uiState.state.temperature - 1
                    )
                }
            }
        ) {
            Text(text = stringResource(R.string.decrease))
        }
    }
}
