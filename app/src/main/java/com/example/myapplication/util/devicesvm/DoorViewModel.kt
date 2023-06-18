package com.example.myapplication.util.devicesvm

import androidx.lifecycle.viewModelScope
import com.example.myapplication.R
import com.example.myapplication.data.DoorUiState
import com.example.myapplication.data.network.RetrofitClient
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class DoorViewModel(name: String?, id: String?) : DeviceViewModel("door", R.drawable.puerta, name,id) {
    private val _uiState = MutableStateFlow(DoorUiState())
    val uiState: StateFlow<DoorUiState> = _uiState.asStateFlow()
    private var fetchJob: Job? = null

    fun open(deviceId: String) {
        fetchJob?.cancel()
        fetchJob = viewModelScope.launch {
            runCatching {
                val apiService = RetrofitClient.getApiService()
                apiService.execute(deviceId, "open")
            }.onSuccess {
                _uiState.update { currentState ->
                    currentState.copy(
                        state = currentState.state.copy(status = "open")
                    )
                }
            }.onFailure {
            }
        }
    }

    fun close(deviceId: String) {
        fetchJob?.cancel()
        fetchJob = viewModelScope.launch {
            runCatching {
                val apiService = RetrofitClient.getApiService()
                apiService.execute(deviceId, "close")
            }.onSuccess {
                _uiState.update { currentState ->
                    currentState.copy(
                        state = currentState.state.copy(status = "close")
                    )
                }
            }.onFailure {
            }
        }
    }

    fun lock(deviceId: String) {
        fetchJob?.cancel()
        fetchJob = viewModelScope.launch {
            runCatching {
                val apiService = RetrofitClient.getApiService()
                apiService.execute(deviceId, "lock")
            }.onSuccess {
                _uiState.update { currentState ->
                    currentState.copy(
                        state = currentState.state.copy(status = "lock")
                    )
                }
            }.onFailure {
            }
        }
    }

    fun unlock(deviceId: String) {
        fetchJob?.cancel()
        fetchJob = viewModelScope.launch {
            runCatching {
                val apiService = RetrofitClient.getApiService()
                apiService.execute(deviceId, "unlock")
            }.onSuccess {
                _uiState.update { currentState ->
                    currentState.copy(
                        state = currentState.state.copy(status = "unlock")
                    )
                }
            }.onFailure {
            }
        }
    }

}