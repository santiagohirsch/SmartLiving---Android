package com.example.myapplication.data

data class AcUiState(
    val id: String,
    val name: String,
    val type: AcType,
    val state: AcState
)

data class AcType(
    val id: String = "li6cbv5sdlatti0j",
    val name: String = "ac",
    val powerUsage: Int = 1600
)

data class AcState(
    val status: String,
    val temperature: Int,
    val mode: String,
    val verticalSwing: String,
    val horizontalSwing: String,
    val fanSpeed: String,
)