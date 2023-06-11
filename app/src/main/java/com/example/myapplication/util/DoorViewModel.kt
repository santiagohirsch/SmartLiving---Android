package com.example.myapplication.util

import androidx.lifecycle.ViewModel
import com.example.myapplication.data.AcUiState
import com.example.myapplication.data.DoorUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class DoorViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(DoorUiState())
    val uiState: StateFlow<DoorUiState> = _uiState.asStateFlow()

}