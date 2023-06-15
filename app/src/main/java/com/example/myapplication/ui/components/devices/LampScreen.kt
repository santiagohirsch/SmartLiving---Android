package com.example.myapplication.ui.components.devices

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Button
import androidx.compose.material.Slider
import androidx.compose.material.Surface
import androidx.compose.material.Switch
import androidx.compose.material.SwitchDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
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
import androidx.compose.ui.zIndex
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.myapplication.R
import com.example.myapplication.util.devicesvm.LampViewModel

@Preview
@Composable
fun Lamp(lampViewModel: LampViewModel = viewModel()){
    var sliderValue by remember {
        mutableFloatStateOf(0f)
    }
    var switchOn by remember {
        mutableStateOf(false)
    }
    Surface(modifier = Modifier.fillMaxSize().zIndex(1f), color = Color.White) {
        Column(
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxSize()
        ) {
            Image(
                painter = painterResource(id = R.drawable.apagada), contentDescription = "lamp",
                modifier = Modifier
                    .size(300.dp)
                    .padding(top = 40.dp)
            )
            Switch(
                checked = switchOn, onCheckedChange = { switchOn_ ->
                    switchOn = switchOn_
                },
                modifier = Modifier
                    .scale(scale = 2.2f),
                colors = SwitchDefaults.colors(
                    checkedThumbColor = Color.Green,
                    checkedTrackColor = Color.Gray
                )
            )
            Text(text = if (switchOn) "On" else "Off")
            Slider(
                value = sliderValue /*uiState.state.brightness*/,
                modifier = Modifier
                    .scale(scale = 0.8f),
                onValueChange = { sliderValue_ ->
                    sliderValue /*uiState.state.brightness*/ = sliderValue_
                },
                /*onValueChangeFinished = {
                Log.d("MainActivity", "sliderVale = $sliderValue")
            },*/
                valueRange = 0f..100f
            )
            Text(text = sliderValue.toString())
            Button(
                onClick = {
                    /*TODO*/
                }
            ) {
                Text(text = "Elegir color")
            }
            /*Button(onClick = { /*TODO*/ }) {
            Text(text = "Confirmar brillo")
        }*/
        }
    }
}