package com.example.myapplication.ui.components.devices

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp


@Preview
@Composable
fun Ac(){
    Column() {
        OutlinedButton(
            onClick = {  },
            shape = RoundedCornerShape(30.dp),
            modifier = Modifier
                .width(100.dp)
                .height(50.dp),
            colors = ButtonDefaults.buttonColors(
                backgroundColor = Color.Red,
            ),
        ) {
            Text( text = "Encender\n  Apagar")
        }
        Box(
            modifier = Modifier
                .width(300.dp)
                .height(200.dp)
                .clip(shape = RoundedCornerShape(size = 22.dp))
                .background(color = Color.Black)
        )
        Row() {
            Text( text = "Modo:")
            Button(
                onClick = {
                    /*TODO*/
                }
            ) {
                Text(text = "Seleccionar modo")
            }
        }
        Row() {
            Text( text = "Velocidad:")
            Button(
                onClick = {
                    /*TODO*/
                }
            ) {
                Text(text = "Elegir velocidad")
            }
        }
        Button(
            onClick = {
                /*TODO*/
            }
        ) {
            Text(text = "Subir")
        }
        Text(text = "Temp")
        Button(
                onClick = {
                    /*TODO*/
                }
                ) {
            Text(text = "Bajar")
        }
        Button(
            onClick = {
                /*TODO*/
            }
        ) {
            Text(text = "Desplazamiento de aspas")
        }
    }
}