package com.example.myapplication.data

import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import com.example.myapplication.R

data class AcUiState(
    val id: String? = "",
    val name: String? = "",
    val type: AcType = AcType(),
    val state: AcState = AcState(),
    val img: Int = R.drawable.ac
)

data class AcType(
    val id: String = "li6cbv5sdlatti0j",
    val name: String = "ac",
    val powerUsage: Int = 1600
)

data class AcState(
    val status: String = "",
    val temperature: Int = 24,
    val mode: String = "cool",
    val verticalSwing: String = "auto",
    val horizontalSwing: String = "auto",
    val fanSpeed: String = "auto",
)