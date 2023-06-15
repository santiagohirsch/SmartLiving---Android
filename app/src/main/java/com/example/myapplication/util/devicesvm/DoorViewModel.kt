package com.example.myapplication.util.devicesvm

import com.example.myapplication.R
import com.example.myapplication.data.DoorUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class DoorViewModel(name: String) : DeviceViewModel("door", R.drawable.puerta, name) {
    private val _uiState = MutableStateFlow(DoorUiState())
    val uiState: StateFlow<DoorUiState> = _uiState.asStateFlow()

}