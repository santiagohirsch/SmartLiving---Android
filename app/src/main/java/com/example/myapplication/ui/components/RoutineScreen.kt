package com.example.myapplication.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.example.myapplication.util.devicesvm.RoutineViewModel

@Composable
fun RoutineScreen(routine: RoutineViewModel){
    Column() {
        Text(text = routine.name.toString())
    }
}