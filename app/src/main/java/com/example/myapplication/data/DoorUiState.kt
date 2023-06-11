package com.example.myapplication.data

import com.example.myapplication.R

data class DoorUiState(
    val id: String = "",
    val name: String = "",
    val type: DoorType = DoorType(),
    val state: DoorState = DoorState(),
    val img: Int = R.drawable.puerta
)

data class DoorType(
    val id: String = "lsf78ly0eqrjbz91",
    val name: String = "door",
    val powerUsage: Int = 350
)

data class DoorState(
    val status: String = "closed",
    val lock: String = "unlocked"
)