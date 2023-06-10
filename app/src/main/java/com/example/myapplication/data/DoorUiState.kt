package com.example.myapplication.data

data class DoorUiState(
    val id: String,
    val name: String,
    val type: DoorType,
    val state: DoorState
)

data class DoorType(
    val id: String = "lsf78ly0eqrjbz91",
    val name: String = "door",
    val powerUsage: Int = 350
)

data class DoorState(
    val status: String,
    val lock: String
)