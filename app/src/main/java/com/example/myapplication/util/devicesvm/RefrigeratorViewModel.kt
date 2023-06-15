package com.example.myapplication.util.devicesvm

import com.example.myapplication.R
import com.example.myapplication.data.RefrigeratorUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class RefrigeratorViewModel( name: String) : DeviceViewModel("refrigerator", R.drawable.heladera, name){
    private val _uiState = MutableStateFlow(RefrigeratorUiState())
    val uiState: StateFlow<RefrigeratorUiState> = _uiState.asStateFlow()

}