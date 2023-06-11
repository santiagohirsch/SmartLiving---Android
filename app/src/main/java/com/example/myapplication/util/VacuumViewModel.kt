package com.example.myapplication.util

import androidx.lifecycle.ViewModel
import com.example.myapplication.data.DoorUiState
import com.example.myapplication.data.VacuumUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class VacuumViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(VacuumUiState())
    val uiState: StateFlow<VacuumUiState> = _uiState.asStateFlow()

}