package com.example.myapplication.data

import com.example.myapplication.data.network.models.DevicesList
import com.example.myapplication.data.network.models.LogList

data class LogsUiState (
    val logs: LogList? = null,
)