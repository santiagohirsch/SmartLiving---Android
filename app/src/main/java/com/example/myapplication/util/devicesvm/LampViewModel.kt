package com.example.myapplication.util.devicesvm

import androidx.lifecycle.viewModelScope
import com.example.myapplication.R
import com.example.myapplication.data.LampState
import com.example.myapplication.data.LampType
import com.example.myapplication.data.LampUiState
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

class LampViewModel(device: Device) : DeviceViewModel("lamp", R.drawable.lampara, device.name,device.id){
    private val _uiState = MutableStateFlow(LampUiState())
    val uiState: StateFlow<LampUiState> = _uiState.asStateFlow()

    private var fetchJob: Job? = null

    init {
        _uiState.value = LampUiState(
            id = device.id ?: "",
            name = device.name ?: "",
            type = LampType(
                id = device.type?.id ?: "ofglvd9gqx8yfl3l",
                name = device.type?.name ?: "vacuum",
                powerUsage = device.type?.powerUsage ?: 300
            ),
            state = LampState(
                status = device.state?.status ?: "off",
                brightness = device.state?.brightness ?: 100,
                color = device.state?.color ?: "FFFFFF",
            ),
            img = R.drawable.aspiradora
        )
    }

    fun turnOn(deviceId: String) {
        fetchJob?.cancel()
        fetchJob = viewModelScope.launch {
            runCatching {
                val apiService = RetrofitClient.getApiService()
                apiService.execute(deviceId = deviceId, actionName =  "turnOn")
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
                apiService.execute(deviceId = deviceId, actionName = "turnOff")
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

    fun setColor(deviceId: String,color: String) {
        fetchJob?.cancel()
        fetchJob = viewModelScope.launch {
            runCatching {
                val apiService = RetrofitClient.getApiService()
                apiService.executePS( deviceId, "setColor", listOf(color))
            }.onSuccess {
                _uiState.update { currentState ->
                    currentState.copy(
                        state = currentState.state.copy(color = color)
                    )
                }
            }.onFailure {
            }
        }
    }

    fun setBrightness(deviceId: String,bright: Int) {
        fetchJob?.cancel()
        fetchJob = viewModelScope.launch {
            runCatching {
                val apiService = RetrofitClient.getApiService()
                apiService.executePI( deviceId, "setBrightness", listOf(bright))
            }.onSuccess {
                _uiState.update { currentState ->
                    currentState.copy(
                        state = currentState.state.copy(brightness = bright)
                    )
                }
            }.onFailure {
            }
        }
    }
}