package com.example.myapplication.util.devicesvm

import com.example.myapplication.R
import com.example.myapplication.data.LampUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class LampViewModel(name: String) : DeviceViewModel("lamp", R.drawable.lampara, name){
    private val _uiState = MutableStateFlow(LampUiState())
    val uiState: StateFlow<LampUiState> = _uiState.asStateFlow()

}