package com.example.myapplication.ui.components

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.KeyboardArrowLeft
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
                        Icon(imageVector = Icons.Outlined.KeyboardArrowLeft, contentDescription = "")
                    }
                    RoutineScreen(routineViewModel = routine, onDismiss = { showDialog = false })
                }
            }
        )
    }

}