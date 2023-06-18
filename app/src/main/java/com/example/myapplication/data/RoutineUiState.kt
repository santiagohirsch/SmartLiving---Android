package com.example.myapplication.data

import com.example.myapplication.data.network.models.Actions

data class RoutineUiState(
    var id      : String?            = null,
    var name    : String?            = null,
    var actions : List<Actions> = listOf()
)
