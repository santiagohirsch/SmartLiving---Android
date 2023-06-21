package com.example.myapplication.ui.components.devices

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.myapplication.R
import com.example.myapplication.util.devicesvm.DeviceViewModel
import com.example.myapplication.util.devicesvm.VacuumViewModel

@Preview
@Composable
fun Vacuum(vacuumViewModel: VacuumViewModel = viewModel()){
    val uiState by vacuumViewModel.uiState.collectAsState()
    var selectMode by remember {
        mutableStateOf(false)
    }
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
                .padding(top = 20.dp),
            colors = ButtonDefaults.buttonColors(
                backgroundColor = Color.Red,
            ),
        ) {
            Text( text = "Encender/\n  Apagar",
                color = Color.White)
        }
        Text(
            text = uiState.state.mode,
            modifier = Modifier
                .offset(y = 168.dp),
            color = Color.Black,
            fontSize = 18.sp
        )
        Text(
            text = uiState.state.status,
            modifier = Modifier
                .offset(y = 250.dp),
            color = Color.Black,
            fontSize = 18.sp
        )
        Image(
            painter = if (uiState.state.status == "active") painterResource(id = R.drawable.vacuumon) else if(uiState.state.status == "inactive") painterResource( id = R.drawable.vacuum) else painterResource(id = R.drawable.vacuumcharging),
            contentDescription = "lamp",
            modifier = Modifier
                .size(400.dp)
                .padding(top = 0.dp)
        )
        Button(
            onClick = {
                selectMode = true
            }
        ) {
            Text(text = "Seleccionar modo")
        }
        if(selectMode){
            Dialog(onDismissRequest = { selectMode = false }) {
                Box(modifier = Modifier
                    .background(color = Color.Black)
                    .height(150.dp)
                    .fillMaxWidth()
                ){
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Button(onClick = {
                            vacuumViewModel.setMode(vacuumViewModel.id.toString(), "vacuum")
                            selectMode = false
                        }) {
                            Text(text = "Aspirar")
                        }
                        Button(onClick = {
                            vacuumViewModel.setMode(vacuumViewModel.id.toString(), "mop")
                            selectMode = false
                        }) {
                            Text(text = "Trapear")
                        }
                    }
                }
            }
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