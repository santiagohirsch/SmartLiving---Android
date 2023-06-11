package com.example.myapplication.data

import com.example.myapplication.R

data class RefrigeratorUiState(
    val id: String = "",
    val name: String = "",
    val type: RefrigeratorType = RefrigeratorType(),
    val state: RefrigeratorState = RefrigeratorState(),
    val img: Int = R.drawable.heladera
)

data class RefrigeratorType(
    val id: String = "rnizejqr2di0okho",
    val name: String = "refrigerator",
    val powerUsage: Int = 90
)

data class RefrigeratorState(
    val mode: String = "default",
    val freezerTemperature: Int = -8,
    val temperature: Int = 2
)