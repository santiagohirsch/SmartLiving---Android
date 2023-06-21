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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.myapplication.R
import com.example.myapplication.util.devicesvm.DeviceViewModel
import com.example.myapplication.util.devicesvm.LampViewModel
import android.os.Bundle
import android.widget.Button
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog


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
    var showDialog by remember {
        mutableStateOf(false)
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
                                lampViewModel.setBrightness(lampViewModel.id.toString(),newVal.toInt())
                },
                /*onValueChangeFinished = {
                Log.d("MainActivity", "sliderVale = $sliderValue")
            },*/
                valueRange = 0f..100f
            )
            Text(text = "Intensidad : "  + uiState.state.brightness.toString(), fontSize = 20.sp)
            Button(
                onClick = {
                    showDialog = true
                    //lampViewModel.setColor(lampViewModel.id.toString(),"FEFEFE")
                }
            ) {
                Text(text = "Elegir color")
            }
            /*Button(onClick = { /*TODO*/ }) {
            Text(text = "Confirmar brillo")
        }*/
        }
    if(showDialog) {
        Dialog(
            onDismissRequest = { showDialog = false }
        ) {
            Box(
                modifier = Modifier
                    .background(color = Color.Black)
                    .height(212.dp)
                    .fillMaxWidth()
            ) {
                Column() {
                    Row {
                        ColorSquare(color = Color.White,onDismiss = {showDialog = false},lampViewModel = lampViewModel)
                        ColorSquare(color = Color(0xFFFFF59D),onDismiss = {showDialog = false},lampViewModel = lampViewModel) // Amarillo claro
                        ColorSquare(color = Color(0xFFFFEB3B),onDismiss = {showDialog = false},lampViewModel = lampViewModel) // Amarillo
                        ColorSquare(color = Color(0xFFFFC107),onDismiss = {showDialog = false},lampViewModel = lampViewModel) // Amarillo oscuro
                        ColorSquare(color = Color(0xFFFFA000),onDismiss = {showDialog = false},lampViewModel = lampViewModel) // Naranja
                        ColorSquare(color = Color(0xFFFF5722),onDismiss = {showDialog = false},lampViewModel = lampViewModel) // Naranja oscuro
                    }
                    Row {
                        ColorSquare(color = Color(0xFFFFCCBC),onDismiss = {showDialog = false},lampViewModel = lampViewModel) // Rosa claro
                        ColorSquare(color = Color(0xFFFFAB91),onDismiss = {showDialog = false},lampViewModel = lampViewModel) // Rosa naranja claro
                        ColorSquare(color = Color(0xFFFF8A65),onDismiss = {showDialog = false},lampViewModel = lampViewModel) // Naranja claro
                        ColorSquare(color = Color(0xFFFF7043),onDismiss = {showDialog = false},lampViewModel = lampViewModel) // Naranja
                        ColorSquare(color = Color(0xFFFF5722),onDismiss = {showDialog = false},lampViewModel = lampViewModel) // Naranja oscuro
                        ColorSquare(color = Color.Red,onDismiss = {showDialog = false},lampViewModel = lampViewModel) // Rojo brillante
                    }
                    Row {
                        ColorSquare(color = Color(0xFFC8E6C9),onDismiss = {showDialog = false},lampViewModel = lampViewModel) // Verde claro
                        ColorSquare(color = Color(0xFF4CAF50),onDismiss = {showDialog = false},lampViewModel = lampViewModel) // Verde
                        ColorSquare(color = Color(0xFF388E3C),onDismiss = {showDialog = false},lampViewModel = lampViewModel) // Verde oscuro
                        ColorSquare(color = Color(0xFF00796B),onDismiss = {showDialog = false},lampViewModel = lampViewModel) // Verde azulado
                        ColorSquare(color = Color(0xFF006064),onDismiss = {showDialog = false},lampViewModel = lampViewModel) // Azul verdoso
                        ColorSquare(color = Color(0xFF004D40),onDismiss = {showDialog = false},lampViewModel = lampViewModel) // Verde azulado oscuro
                    }
                    Row {
                        ColorSquare(color = Color(0xFFB2EBF2),onDismiss = {showDialog = false},lampViewModel = lampViewModel) // Azul claro
                        ColorSquare(color = Color(0xFF03A9F4),onDismiss = {showDialog = false},lampViewModel = lampViewModel) // Azul
                        ColorSquare(color = Color(0xFF0277BD),onDismiss = {showDialog = false},lampViewModel = lampViewModel) // Azul oscuro
                        ColorSquare(color = Color(0xFF01579B),onDismiss = {showDialog = false},lampViewModel = lampViewModel) // Azul profundo
                        ColorSquare(color = Color(0xFF0D47A1),onDismiss = {showDialog = false},lampViewModel = lampViewModel) // Azul intenso
                        ColorSquare(color = Color(0xFF1A237E),onDismiss = {showDialog = false},lampViewModel = lampViewModel) // Azul eléctrico
                    }


                }
            }
        }
    }
}



@Composable
fun ColorSquare(color: Color, padding: Dp = 2.dp, onDismiss: ()-> Unit,lampViewModel: LampViewModel) {
    Box(
        modifier = Modifier
            .padding(padding)
            .clickable {
                onDismiss()
                lampViewModel.setColor(lampViewModel.id.toString(), colorToHexString(color))
            }
            .background(color)
            .size(48.6.dp)
    ) {
        // Puedes agregar contenido adicional dentro del cuadrado si lo deseas
    }
}

fun colorToHexString(color: Color): String {
    val red = (color.red * 255).toInt()
    val green = (color.green * 255).toInt()
    val blue = (color.blue * 255).toInt()
    return String.format("%02X%02X%02X", red, green, blue)
}