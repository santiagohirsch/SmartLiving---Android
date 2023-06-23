package com.example.myapplication.ui.components

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Clear
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material.icons.outlined.KeyboardArrowLeft
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.example.myapplication.R
import com.example.myapplication.util.devicesrep.CurrentDevices
import com.example.myapplication.util.devicesvm.RoutineViewModel

@SuppressLint("StateFlowValueCalledInComposition")
@Composable
fun RoutineCard(routine : RoutineViewModel){
    var showDialog by remember { mutableStateOf(false) }
    var showConfirmation by remember { mutableStateOf(false) }
    val uiState by routine.uiState.collectAsState()
    Card(
        modifier = Modifier
            .padding(8.dp)
            .width(140.dp)
            .height(200.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 5.dp)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .clip(RoundedCornerShape(5.dp))
        ) {
            Image(
                painter = painterResource(R.drawable.rutina),
                contentDescription = null,
                modifier = Modifier
                    .padding(top = 12.dp, bottom = 6.dp)
                    .clip(RoundedCornerShape(5.dp))
                    .clickable { showDialog = true }
                    .size(112.dp),
                contentScale = ContentScale.Crop
            )
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f) // Allocate remaining space to the second element
            ) {
                Text(
                    text = routine.uiState.value.name.toString(),
                    textAlign = TextAlign.Center,
                    fontSize = 20.sp,
                    modifier = Modifier
                        .clip(RoundedCornerShape(5.dp))
                    //style = MaterialTheme.typography.headlineSmall
                )
            }
        }
    }

    if (showDialog) {
        Dialog(
            onDismissRequest = { showDialog = false },
            content = {
                Box(
                    modifier = Modifier
                        .background(Color.White)
                        .height(450.dp)
                ) {
                    IconButton(
                        onClick = {
                            showDialog = false
                        }
                    ) {
                        Icon(imageVector = Icons.Outlined.Clear, contentDescription = "")
                    }
                    IconButton(
                        onClick = {
                            showConfirmation = true
                        },
                        modifier = Modifier.padding(start = 270.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Outlined.Delete,
                            contentDescription = "",
                            modifier = Modifier.size(30.dp)
                        )
                    }
                    RoutineScreen(routineViewModel = routine, onDismiss = { showDialog = false })
                }
            }
        )
    }

    if(showConfirmation) {
        Dialog(onDismissRequest = { showConfirmation = false }) {
            Box(
                modifier = Modifier
                    .width(300.dp)
                    .height(100.dp)
                    .background(color = Color.White)
            )
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(text = stringResource(R.string.delete_confir_2))
                Row() {

                    Button(onClick = { showConfirmation = false
                    }, modifier = Modifier
                        .padding(all = 5.dp)
                    ) {
                        Text(text = stringResource(R.string.cancel), color = Color.White)
                    }
                    Button(onClick = {
                        showConfirmation = false
                        routine.deleteRoutine(uiState.id.toString())
                        showDialog = false
                    },
                        modifier = Modifier
                            .padding(all = 5.dp)
                    ) {
                        Text(text = stringResource(R.string.confirm), color = Color.White)
                    }
                }
            }
        }
    }

}