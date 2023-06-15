package com.example.myapplication.util.DevicesViewModels

import androidx.lifecycle.ViewModel
import com.example.myapplication.R
import com.example.myapplication.data.DoorUiState
import com.example.myapplication.data.VacuumUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class VacuumViewModel(name: String) : DeviceViewModel("vacuum", R.drawable.aspiradora, name){
    private val _uiState = MutableStateFlow(VacuumUiState())
    val uiState: StateFlow<VacuumUiState> = _uiState.asStateFlow()

}