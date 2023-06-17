package com.example.myapplication.data.network

import com.example.myapplication.data.DevicesUiState
import com.example.myapplication.data.network.models.RoutineList

data class RoutinesUiState(
    val routines: RoutineList? = null,
    val isLoading: Boolean = false,
    val message: String? = null
)
val DevicesUiState.hasrError: Boolean get() = message != null
