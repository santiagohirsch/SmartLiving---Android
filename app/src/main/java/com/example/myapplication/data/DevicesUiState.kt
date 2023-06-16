package com.example.myapplication.data

import com.example.myapplication.data.network.models.DevicesList

data class DevicesUiState(
    val devices: DevicesList? = null,
    val isLoading: Boolean = false,
    val message: String? = null
)
val DevicesUiState.hasrError: Boolean get() = message != null