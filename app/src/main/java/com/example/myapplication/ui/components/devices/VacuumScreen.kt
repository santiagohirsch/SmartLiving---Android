package com.example.myapplication.ui.components.devices

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.myapplication.R
import com.example.myapplication.util.devicesvm.DeviceViewModel
import com.example.myapplication.util.devicesvm.VacuumViewModel

@Preview
@Composable
fun Vacuum(vacuumViewModel: VacuumViewModel = viewModel()){
    val uiState by vacuumViewModel.uiState.collectAsState()
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        OutlinedButton(
            onClick = {
                if(uiState.state.status == "inactive") {
                    vacuumViewModel.start(vacuumViewModel.id.toString())
                }
                else {
                    vacuumViewModel.pause(vacuumViewModel.id.toString())
                }
            },
            shape = RoundedCornerShape(30.dp),
            modifier = Modifier
                .width(130.dp)
                .height(80.dp)
                .offset(x = 90.dp, y = 0.dp) // Mueve la caja 50.dp hacia la derecha
                .padding(all = 10.dp),
            colors = ButtonDefaults.buttonColors(
                backgroundColor = Color.Red,
            ),
        ) {
            Text( text = "Encender/\n  Apagar",
                color = Color.White)
        }
        Image(
            painter = painterResource(id = R.drawable.aspiradora), contentDescription = "vacuum",
            modifier = Modifier
                .size(100.dp)
                .padding(top = 40.dp)
        )
        Button(
            onClick = {

            }
        ) {
            Text(text = "Seleccionar modo")
        }
        Button(
            onClick = {
                vacuumViewModel.dock(vacuumViewModel.id.toString())
            }
        ) {
            Text(text = "Regresar a base de carga")
        }
    }
}