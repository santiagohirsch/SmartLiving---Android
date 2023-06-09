package com.example.myapplication.ui.components.devices

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Preview
@Composable
fun Fridge(){
    Column() {
        Box(
            modifier = Modifier
                .width(150.dp)
                .height(200.dp)
                .clip(shape = RoundedCornerShape(size = 22.dp))
                .background(color = Color.Black)
        )
        Button(
            onClick = {
                /*TODO*/
            }
        ) {
            Text(text = "Cambiar modo")
        }
        Row() {
            Text(text = "Temp. freezer")
            Text(text = "Temp. heladera")
        }
        Text(text = "Modo:")
        Row() {
            Column() {
                Button(
                    onClick = {
                        /*TODO*/
                    }
                ) {
                    Text(text = "Subir")
                }
                Text(text = "Temp freezer")
                Button(
                    onClick = {
                        /*TODO*/
                    }
                ) {
                    Text(text = "Bajar")
                }
            }
            Column() {
                Button(
                    onClick = {
                        /*TODO*/
                    }
                ) {
                    Text(text = "Subir")
                }
                Text(text = "Temp heladera")
                Button(
                    onClick = {
                        /*TODO*/
                    }
                ) {
                    Text(text = "Bajar")
                }
            }
        }
    }
}