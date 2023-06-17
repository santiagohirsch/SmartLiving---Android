package com.example.myapplication.data.network.models

data class Params(
    val color: String? = "",
    val temperature: Int? = 0,
    val verticalSwing: String? = "",
    val horizontalSwing: String? = "",
    val fanSpeed: String? = "",
    val mode: String? = "",
    val freezerTemperature: Int? = 0,
    val brightness: Double? = 0.0
)