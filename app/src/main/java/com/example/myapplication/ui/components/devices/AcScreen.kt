package com.example.myapplication.ui.components.devices

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.myapplication.util.DevicesViewModels.AcViewModel


@Preview
@Composable
fun Ac(acViewModel: AcViewModel = viewModel()){
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        OutlinedButton(
            onClick = {  },
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
                    .padding( top = 5.dp)
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
                            text = "Modo: "/* + uiState.state.mode*/,
                            color = Color.Black,
                            modifier = Modifier
                                .padding(start = 10.dp),
                            //fontSize = 10.dp
                        )

                    }
                }
                Spacer(modifier = Modifier
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
                                text = "ON" /*uiState.state.status*/,
                                color = Color.Black,
                                modifier = Modifier
                                    .padding(start = 10.dp),
                                //fontSize = 10.dp
                            )
                        }
                        Spacer(modifier = Modifier
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
                        Text(text = "Temperature: "/* + uiState.state.temperature*/,
                            color = Color.Black,
                            modifier = Modifier
                                .padding(start = 10.dp),
                            //fontSize = 8.dp
                            )
                        }
                    }
                Spacer(modifier = Modifier
                    .height(5.dp)
                )
                Row() {
                    Box(
                        modifier = Modifier
                            .height(30.dp)
                            .fillMaxWidth()
                            .background(color = Color.Gray)
                    ) {
                        Text(text = "Velocidad: "/* + uiState.state.fanSpeed*/,
                            color = Color.Black,
                            modifier = Modifier
                                .padding(start = 10.dp),
                            //fontSize = 8.dp
                        )
                    }
                }

            }
        }
        }
        Row(
            modifier = Modifier
                .padding(all = 25.dp)
        ) {
            Button(
                onClick = {
                    /*TODO*/
                },
                modifier = Modifier
                    .padding(start = 5.dp)

            ) {
                Text(text = "Seleccionar modo")
            }

            Button(
                onClick = {
                    /*TODO*/
                },
                modifier = Modifier
                    .padding(start = 15.dp)
            ) {
                Text(text = "Elegir velocidad")
            }
        }

        Button(
            modifier = Modifier
                .padding(all = 5.dp),
            onClick = {
                /*TODO*/
            }
        ) {
            Text(text = "Desplazamiento de aspas")
        }
        Button(
            shape = RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp),
            modifier = Modifier
                .padding(top = 25.dp, bottom = 5.dp),
            onClick = {
                /*TODO*/
            }
        ) {
            Text(text = "Subir")
        }
        Text(text = "Temp")
        Button(
            shape = RoundedCornerShape(bottomStart = 20.dp, bottomEnd = 20.dp),
            modifier = Modifier
                .padding(top = 5.dp),
            onClick = {
                /*TODO*/
            }
        ) {
            Text(text = "Bajar")
        }
    }
}