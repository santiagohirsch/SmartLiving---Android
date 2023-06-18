package com.example.myapplication.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import com.example.myapplication.util.devicesvm.RoutineViewModel

@Composable
fun RoutineScreen(routineViewModel: RoutineViewModel){
    val uiState by routineViewModel.uiState.collectAsState()
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = uiState.name.toString())
        uiState.actions.forEach{ action ->
            Text(text = "${action.device?.name} -> ${action.actionName}")
        }
    }
}