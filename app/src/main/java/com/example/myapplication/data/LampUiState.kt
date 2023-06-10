package com.example.myapplication.data

data class LampUiState(
    val id: String,
    val name: String,
    val type: LampType,
    val state: LampState
)

data class LampType(
    val id: String = "go46xmbqeomjrsjr",
    val name: String = "lamp",
    val powerUsage: Int = 15
)

data class LampState(
    val status: String,
    val color: String,
    val brightness: Double
)