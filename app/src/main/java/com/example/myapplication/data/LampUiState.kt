package com.example.myapplication.data

import com.example.myapplication.R

data class LampUiState(
    val id: String = "",
    val name: String = "",
    val type: LampType = LampType(),
    val state: LampState = LampState(),
    val img: Int = R.drawable.lampara
)

data class LampType(
    val id: String = "go46xmbqeomjrsjr",
    val name: String = "lamp",
    val powerUsage: Int = 15
)

data class LampState(
    val status: String = "off",
    val color: String = "FFFFFF",
    val brightness: Double = 100.0
)