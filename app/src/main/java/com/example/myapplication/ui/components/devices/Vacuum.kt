package com.example.myapplication.ui.components.devices

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.myapplication.R

@Preview
@Composable
fun Vacuum(){
    Column() {
        Image(
            painter = painterResource(id = R.drawable.aspiradora), contentDescription = "vacuum",
            modifier = Modifier
                .size(100.dp)
                .padding(top = 40.dp)
        )
        Button(
            onClick = {
                /*TODO*/
            }
        ) {
            Text(text = "Seleccionar modo")
        }
        Row() {
            Button(
                onClick = {
                    /*TODO*/
                }
            ) {
                Text(text = "Cambiar ubicacion de base de carga")
            }
            Button(
                onClick = {
                    /*TODO*/
                }
            ) {
                Text(text = "Regresar a base de carga")
            }
        }
    }
}