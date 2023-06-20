package com.example.myapplication.data.network.models

import com.example.myapplication.data.LogsUiState
import com.google.gson.annotations.SerializedName

data class LogList (
    @SerializedName("result" ) var logs : List<Log> = listOf()
)