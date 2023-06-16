package com.example.myapplication.util.devicesvm

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.myapplication.data.DevicesUiState
import com.example.myapplication.data.network.RetrofitClient
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

import kotlinx.coroutines.launch

class DevicesViewModel: ViewModel() {
    private val _uiState = MutableStateFlow(DevicesUiState())
    val uiState: StateFlow<DevicesUiState> = _uiState.asStateFlow()

    private var fetchJob: Job? = null

    fun getAllDevices() {
        Log.d("GetAllDevices","Entre a getAllDev")
        fetchJob?.cancel()
        fetchJob = viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true) }
            runCatching {
                Log.d("RunCatch","Corriendo")
                val apiService =  RetrofitClient.getApiService()
                apiService.getDevices()
            }.onSuccess { response ->
                _uiState.update { it.copy(devices = response.body(),
                isLoading = false) }
            }.onFailure { e->
                _uiState.update { it.copy(
                    message = e.message,
                    isLoading = false
                ) }
            }
        }
    }
}