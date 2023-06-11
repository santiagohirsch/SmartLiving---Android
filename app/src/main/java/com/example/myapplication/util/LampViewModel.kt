package com.example.myapplication.util

import androidx.lifecycle.ViewModel
import com.example.myapplication.data.DoorUiState
import com.example.myapplication.data.LampUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class LampViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(LampUiState())
    val uiState: StateFlow<LampUiState> = _uiState.asStateFlow()

}