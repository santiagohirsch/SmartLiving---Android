package com.example.myapplication.ui.screens

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.myapplication.R
import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.ui.platform.LocalContext
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Switch
import androidx.compose.material.SwitchDefaults
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.navigation.NavHostController


//@Preview
@Composable
fun LandingScreen(navController: NavHostController) {
    val openDialog = remember {
        mutableStateOf(false)
    }
    var switchOn by remember {
        mutableStateOf(false)
    }
    Surface(modifier = Modifier.fillMaxSize(), color = Color(android.graphics.Color.parseColor("#2f2f4d"))) {
        //Image(painter = , contentDescription = null)
        Column(
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxSize()
        ) {
            Image(
                painter = painterResource(id = R.drawable.logo), contentDescription = "Logo",
                modifier = Modifier
                    .size(180.dp)
                    .padding(top = 20.dp)
            )
            Text(
                text = stringResource(R.string.supported_msg)
            )
            Row {
                Image(
                    painter = painterResource(id = R.drawable.ac), contentDescription = "ac",
                    modifier = Modifier
                        .size(100.dp)
                        .padding(top = 40.dp)
                )
                Image(
                    painter = painterResource(id = R.drawable.puerta), contentDescription = "door",
                    modifier = Modifier
                        .size(100.dp)
                        .padding(top = 40.dp)
                )
            }
            Row {
                Image(
                    painter = painterResource(id = R.drawable.aspiradora),
                    contentDescription = "vacuum",
                    modifier = Modifier
                        .size(100.dp)
                        .padding(top = 40.dp)
                )
                Image(
                    painter = painterResource(id = R.drawable.lampara), contentDescription = "lamp",
                    modifier = Modifier
                        .size(100.dp)
                        .padding(top = 40.dp)
                )
            }
            Image(painter = painterResource(id = R.drawable.heladera), contentDescription = "fridge" ,
                modifier = Modifier
                    .size(100.dp)
                    .padding(top = 40.dp)
                )
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 70.dp),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                NormalButton(navController)
                Button(onClick = {openDialog.value = true}) {
                    Text(text = "Dialog")
                }
                if (openDialog.value) {

                    AlertDialog(
                        onDismissRequest = {
                            openDialog.value = false
                        },
                        title = {
                            Text(text = "Dialog Title Will Show Here")
                        },
                        text = {
                            Text("Here is a description text of the dialog")
                        },
                        confirmButton = {
                            Button(

                                onClick = {
                                    openDialog.value = false
                                }) {
                                Text("Confirm Button")
                            }
                        },
                        dismissButton = {
                            Button(

                                onClick = {
                                    openDialog.value = false
                                }) {
                                Text("Dismiss Button")
                            }
                        }
                    )
                }
            }
            Switch(checked = switchOn, onCheckedChange = {
                switchOn_->switchOn=switchOn_
                },
                colors = SwitchDefaults.colors(
                    checkedThumbColor = Color.Green,
                    checkedTrackColor = Color.Magenta
                )
            )
        }
    }
}

@Composable
fun NormalButton(navController: NavHostController){
    val context = LocalContext.current
    OutlinedButton(
        onClick = { navController.navigate("devices_screen")
        {
            navController.graph.startDestinationRoute?.let { screenRoute ->
                popUpTo(screenRoute) {
                    saveState = true
                }
                launchSingleTop = true
                restoreState = true
            }
        }
                  },
        shape = RoundedCornerShape(30.dp),
        modifier = Modifier
            .width(180.dp)
            .height(50.dp),
        colors = ButtonDefaults.buttonColors(
            backgroundColor = Color.White,
        ),
        border = BorderStroke(5.dp, Color.Gray)
    ){ Text(text = "Empieza ya!")}
}
