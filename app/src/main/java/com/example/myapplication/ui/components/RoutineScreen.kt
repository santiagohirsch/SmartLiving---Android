package com.example.myapplication.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapplication.util.devicesvm.RoutineViewModel

@Composable
fun RoutineScreen(routineViewModel: RoutineViewModel){
    val uiState by routineViewModel.uiState.collectAsState()
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize()
    ) {
        Text(
            text = uiState.name.toString(),
            fontSize = 40.sp
        )
        Spacer(modifier = Modifier.height(15.dp))
        uiState.actions.forEach{ action ->
            Text(
                text = "${action.device?.name} -> ${action.actionName}",
                fontSize = 25.sp
            )
        }
        Spacer(modifier = Modifier.height(20.dp))
        Button(
            onClick = { routineViewModel.executeRoutine() },
            modifier = Modifier
                .height(80.dp)
                .width(130.dp),
        ) {
            Text(
                text = "Ejecutar",
                fontSize = 20.sp
            )
        }
    }
}