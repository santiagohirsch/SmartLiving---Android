package com.example.myapplication.data

data class VacuumUiState(
    val id: String,
    val name: String,
    val type: VacuumType,
    val state: VacuumState
)

data class VacuumType(
    val id: String = "ofglvd9gqx8yfl3l",
    val name: String = "vacuum",
    val powerUsage: Int = 300
)

data class VacuumState(
    val status: String,
    val mode: String,
    val batteryLevel: Int,
    val location: String,
)