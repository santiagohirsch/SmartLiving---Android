package com.example.myapplication.ui.components.devices

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Button
import androidx.compose.material.Slider
import androidx.compose.material.Switch
import androidx.compose.material.SwitchDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.myapplication.R
import com.example.myapplication.util.devicesvm.DeviceViewModel
import com.example.myapplication.util.devicesvm.LampViewModel

@Preview
@Composable
fun Lamp(
    modifier: Modifier = Modifier,
    lampViewModel: LampViewModel = viewModel()
){
    val uiState by lampViewModel.uiState.collectAsState()
    var switchOn by remember {
        mutableStateOf(uiState.state.status == "on")
    }
        Column(
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxSize()
        ) {
            Image(
                painter = if (uiState.state.status == "off") painterResource(id = R.drawable.apagada) else painterResource( id = R.drawable.prendida),
                contentDescription = "lamp",
                modifier = Modifier
                    .size(300.dp)
                    .padding(top = 40.dp)
            )
            Switch(
                checked = switchOn,
                onCheckedChange = { switchOn_ ->
                    switchOn = switchOn_
                    if (switchOn) {
                        lampViewModel.turnOn(lampViewModel.id.toString())
                    } else {
                        lampViewModel.turnOff(lampViewModel.id.toString())
                    }
                },
                modifier = Modifier
                    .scale(scale = 2.2f),
                colors = SwitchDefaults.colors(
                    checkedThumbColor = Color.Green,
                    checkedTrackColor = Color.Gray
                )
            )
            Slider(
                value = uiState.state.brightness.toFloat(),
                modifier = Modifier
                    .scale(scale = 0.8f),
                onValueChange = { newVal ->
                                lampViewModel.setBrightness(lampViewModel.id.toString(),newVal.toDouble())
                },
                /*onValueChangeFinished = {
                Log.d("MainActivity", "sliderVale = $sliderValue")
            },*/
                valueRange = 0f..100f
            )
            Text(text = uiState.state.brightness.toString())
            Button(
                onClick = {
                    lampViewModel.setColor(lampViewModel.id.toString(),"FEFEFE")
                }
            ) {
                Text(text = "Elegir color")
            }
            /*Button(onClick = { /*TODO*/ }) {
            Text(text = "Confirmar brillo")
        }*/
        }
}