package com.example.myapplication.util.devicesvm

import androidx.lifecycle.viewModelScope
import com.example.myapplication.R
import com.example.myapplication.data.VacuumState
import com.example.myapplication.data.VacuumType
import com.example.myapplication.data.VacuumUiState
import com.example.myapplication.data.network.RetrofitClient
import com.example.myapplication.data.network.models.Device
import com.example.myapplication.data.network.models.Location
import com.example.myapplication.data.network.models.Params
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class VacuumViewModel(device: Device) : DeviceViewModel("vacuum", R.drawable.aspiradora, device.name,device.id){
    private val _uiState = MutableStateFlow(VacuumUiState())
    val uiState: StateFlow<VacuumUiState> = _uiState.asStateFlow()




    private var fetchJob: Job? = null

    init {
        _uiState.value = VacuumUiState(
            id = device.id ?: "",
            name = device.name ?: "",
            type = VacuumType(
                id = device.type?.id ?: "ofglvd9gqx8yfl3l",
                name = device.type?.name ?: "vacuum",
                powerUsage = device.type?.powerUsage ?: 300
            ),
            state = VacuumState(
                status = device.state?.status ?: "inactive",
                mode = device.state?.mode ?: "vacuum",
                batteryLevel = device.state?.batteryLevel ?: 4,
                location = Location(
                    id = device.state?.location?.id ?: "",
                    name = device.state?.location?.name ?: ""
                ),
            ),
            img = R.drawable.aspiradora
        )
    }

    fun start(deviceId: String) {
        fetchJob?.cancel()
        fetchJob = viewModelScope.launch {
            runCatching {
                val apiService = RetrofitClient.getApiService()
                apiService.execute( deviceId, "start")
            }.onSuccess {
                _uiState.update { currentState ->
                    currentState.copy(
                        state = currentState.state.copy(status = "active")
                    )
                }
            }.onFailure {
            }
        }
    }

    fun pause(deviceId: String) {
        fetchJob?.cancel()
        fetchJob = viewModelScope.launch {
            runCatching {
                val apiService = RetrofitClient.getApiService()
                apiService.execute( deviceId, "pause")
            }.onSuccess {
                _uiState.update { currentState ->
                    currentState.copy(
                        state = currentState.state.copy(status = "inactive")
                    )
                }
            }.onFailure {
            }
        }
    }

    fun dock(deviceId: String) {
        fetchJob?.cancel()
        fetchJob = viewModelScope.launch {
            runCatching {
                val apiService = RetrofitClient.getApiService()
                apiService.execute( deviceId, "dock")
            }.onSuccess {
                _uiState.update { currentState ->
                    currentState.copy(
                        state = currentState.state.copy(status = "docked")
                    )
                }
            }.onFailure {
            }
        }
    }

    fun setMode(deviceId: String,mode: String) {
        fetchJob?.cancel()
        fetchJob = viewModelScope.launch {
            runCatching {
                val apiService = RetrofitClient.getApiService()
                apiService.executePS( deviceId, "setMode", listOf(mode))
            }.onSuccess {
                _uiState.update { currentState ->
                    currentState.copy(
                        state = currentState.state.copy(mode = mode)
                    )
                }
            }.onFailure {
            }
        }
    }
}