package com.example.myapplication.util.devicesvm

import androidx.lifecycle.viewModelScope
import com.example.myapplication.R
import com.example.myapplication.data.DoorState
import com.example.myapplication.data.DoorType
import com.example.myapplication.data.DoorUiState
import com.example.myapplication.data.LampState
import com.example.myapplication.data.LampType
import com.example.myapplication.data.LampUiState
import com.example.myapplication.data.network.RetrofitClient
import com.example.myapplication.data.network.models.Device
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class DoorViewModel(device: Device) : DeviceViewModel("door", R.drawable.puerta, device.name,device.id) {
    private val _uiState = MutableStateFlow(DoorUiState())
    val uiState: StateFlow<DoorUiState> = _uiState.asStateFlow()
    private var fetchJob: Job? = null

    init {
        _uiState.value = DoorUiState(
            id = device.id ?: "",
            name = device.name ?: "",
            type = DoorType(
                id = device.type?.id ?: "lsf78ly0eqrjbz91",
                name = device.type?.name ?: "door",
                powerUsage = device.type?.powerUsage ?: 350
            ),
            state = DoorState(
                status = device.state?.status ?: "closed",
                lock = device.state?.lock ?: "unlock"
            ),
            img = R.drawable.aspiradora
        )
    }

    fun open(deviceId: String) {
        fetchJob?.cancel()
        fetchJob = viewModelScope.launch {
            runCatching {
                val apiService = RetrofitClient.getApiService()
                apiService.execute(deviceId, "open")
            }.onSuccess {
                _uiState.update { currentState ->
                    currentState.copy(
                        state = currentState.state.copy(status = "opened")
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
                        state = currentState.state.copy(status = "closed")
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
                        state = currentState.state.copy(lock = "locked")
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
                        state = currentState.state.copy(lock = "unlocked")
                    )
                }
            }.onFailure {
            }
        }
    }

}