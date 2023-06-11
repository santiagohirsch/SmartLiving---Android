package com.example.myapplication.data

import com.example.myapplication.R

data class VacuumUiState(
    val id: String = "",
    val name: String = "",
    val type: VacuumType = VacuumType(),
    val state: VacuumState = VacuumState(),
    val img: Int = R.drawable.aspiradora
)

data class VacuumType(
    val id: String = "ofglvd9gqx8yfl3l",
    val name: String = "vacuum",
    val powerUsage: Int = 300
)

data class VacuumState(
    val status: String = "inactive",
    val mode: String = "vacuum",
    val batteryLevel: Int = 4,
    val location: String = "",
)