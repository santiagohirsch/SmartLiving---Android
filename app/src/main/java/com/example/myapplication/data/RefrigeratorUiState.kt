package com.example.myapplication.data

data class RefrigeratorUiState(
    val id: String,
    val name: String,
    val type: RefrigeratorType,
    val state: RefrigeratorState
)

data class RefrigeratorType(
    val id: String = "rnizejqr2di0okho",
    val name: String = "refrigerator",
    val powerUsage: Int = 90
)

data class RefrigeratorState(
    val status: String,
    val mode: String,
    val freezerTemperature: Int,
    val temperature: Int
)