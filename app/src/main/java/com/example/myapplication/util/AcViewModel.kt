package com.example.myapplication.util

import androidx.lifecycle.ViewModel
import com.example.myapplication.data.AcUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class AcViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(AcUiState())
    val uiState: StateFlow<AcUiState> = _uiState.asStateFlow()

}