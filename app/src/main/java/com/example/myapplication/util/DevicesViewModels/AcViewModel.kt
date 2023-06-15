package com.example.myapplication.util.DevicesViewModels

import androidx.lifecycle.ViewModel
import com.example.myapplication.R
import com.example.myapplication.data.AcUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class AcViewModel(name: String) : DeviceViewModel("ac", R.drawable.ac, name) {
    private val _uiState = MutableStateFlow(AcUiState())
    val uiState: StateFlow<AcUiState> = _uiState.asStateFlow()

}