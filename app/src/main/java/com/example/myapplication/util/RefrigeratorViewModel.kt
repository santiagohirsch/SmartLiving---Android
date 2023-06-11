package com.example.myapplication.util

import androidx.lifecycle.ViewModel
import com.example.myapplication.data.DoorUiState
import com.example.myapplication.data.RefrigeratorUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class RefrigeratorViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(RefrigeratorUiState())
    val uiState: StateFlow<RefrigeratorUiState> = _uiState.asStateFlow()

}