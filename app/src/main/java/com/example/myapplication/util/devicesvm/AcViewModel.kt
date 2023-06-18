package com.example.myapplication.util.devicesvm

import androidx.lifecycle.viewModelScope
import com.example.myapplication.R
import com.example.myapplication.data.AcState
import com.example.myapplication.data.AcType
import com.example.myapplication.data.AcUiState
import com.example.myapplication.data.LampState
import com.example.myapplication.data.LampType
import com.example.myapplication.data.LampUiState
import com.example.myapplication.data.network.RetrofitClient
import com.example.myapplication.data.network.models.Device
import com.example.myapplication.data.network.models.Params
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class AcViewModel(device: Device) : DeviceViewModel("ac", R.drawable.ac, device.name,device.id) {
    private val _uiState = MutableStateFlow(AcUiState())
    val uiState: StateFlow<AcUiState> = _uiState.asStateFlow()

    private var fetchJob: Job? = null

    init {
        _uiState.value = AcUiState(
            id = device.id ?: "",
            name = device.name ?: "",
            type = AcType(
                id = device.type?.id ?: "ofglvd9gqx8yfl3l",
                name = device.type?.name ?: "vacuum",
                powerUsage = device.type?.powerUsage ?: 300
            ),
            state = AcState(
                status = device.state?.status ?: "inactive",
                temperature = device.state?.temperature ?: 24,
                mode = device.state?.mode ?: "auto",
                verticalSwing = device.state?.verticalSwing ?: "auto",
                horizontalSwing = device.state?.horizontalSwing ?: "auto",
                fanSpeed = device.state?.fanSpeed ?: "auto",
            ),
            img = R.drawable.aspiradora
        )
    }
    fun turnOn(deviceId: String) {
        fetchJob?.cancel()
        fetchJob = viewModelScope.launch {
            runCatching {
                val apiService = RetrofitClient.getApiService()
                apiService.execute( deviceId, "turnOn")
            }.onSuccess {
                _uiState.update { currentState ->
                    currentState.copy(
                        state = currentState.state.copy(status = "on")
                    )
                }
            }.onFailure {
            }
        }
    }

    fun turnOff(deviceId: String) {
        fetchJob?.cancel()
        fetchJob = viewModelScope.launch {
            runCatching {
                val apiService = RetrofitClient.getApiService()
                apiService.execute( deviceId, "turnOff")
            }.onSuccess {
                _uiState.update { currentState ->
                    currentState.copy(
                        state = currentState.state.copy(status = "off")
                    )
                }
            }.onFailure {
            }
        }
    }

    fun setTemperature(deviceId: String,temp: Int) {
        fetchJob?.cancel()
        fetchJob = viewModelScope.launch {
            runCatching {
                val apiService = RetrofitClient.getApiService()
                apiService.executePI( deviceId, "setTemperature", listOf(temp))
            }.onSuccess {
                _uiState.update { currentState ->
                    currentState.copy(
                        state = currentState.state.copy(temperature = temp)
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

    fun setVerticalSwing(deviceId: String,swing: String) {
        fetchJob?.cancel()
        fetchJob = viewModelScope.launch {
            runCatching {
                val apiService = RetrofitClient.getApiService()
                apiService.executePS( deviceId, "setVerticalSwing", listOf(swing))
            }.onSuccess {
                _uiState.update { currentState ->
                    currentState.copy(
                        state = currentState.state.copy(verticalSwing = swing)
                    )
                }
            }.onFailure {
            }
        }
    }

    fun setHorizontalSwing(deviceId: String,swing: String) {
        fetchJob?.cancel()
        fetchJob = viewModelScope.launch {
            runCatching {
                val apiService = RetrofitClient.getApiService()
                apiService.executePS( deviceId, "setHorizontalSwing", listOf(swing))
            }.onSuccess {
                _uiState.update { currentState ->
                    currentState.copy(
                        state = currentState.state.copy(horizontalSwing = swing)
                    )
                }
            }.onFailure {
            }
        }
    }

    fun setFanSpeed(deviceId: String,speed: String) {
        fetchJob?.cancel()
        fetchJob = viewModelScope.launch {
            runCatching {
                val apiService = RetrofitClient.getApiService()
                apiService.executePS( deviceId, "setFanSpeed", listOf(speed))
            }.onSuccess {
                _uiState.update { currentState ->
                    currentState.copy(
                        state = currentState.state.copy(fanSpeed = speed)
                    )
                }
            }.onFailure {
            }
        }
    }
}