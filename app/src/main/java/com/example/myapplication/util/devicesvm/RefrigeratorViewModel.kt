package com.example.myapplication.util.devicesvm

import androidx.lifecycle.viewModelScope
import com.example.myapplication.R
import com.example.myapplication.data.AcState
import com.example.myapplication.data.AcType
import com.example.myapplication.data.AcUiState
import com.example.myapplication.data.RefrigeratorState
import com.example.myapplication.data.RefrigeratorType
import com.example.myapplication.data.RefrigeratorUiState
import com.example.myapplication.data.network.RetrofitClient
import com.example.myapplication.data.network.models.Device
import com.example.myapplication.data.network.models.Params
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class RefrigeratorViewModel(device: Device) : DeviceViewModel("refrigerator", R.drawable.heladera, device.name,device.id){
    private val _uiState = MutableStateFlow(RefrigeratorUiState())
    val uiState: StateFlow<RefrigeratorUiState> = _uiState.asStateFlow()

    private var fetchJob: Job? = null

    init {
        _uiState.value = RefrigeratorUiState(
            id = device.id ?: "",
            name = device.name ?: "",
            type = RefrigeratorType(
                id = device.type?.id ?: "ofglvd9gqx8yfl3l",
                name = device.type?.name ?: "vacuum",
                powerUsage = device.type?.powerUsage ?: 300
            ),
            state = RefrigeratorState(
                freezerTemperature = device.state?.freezerTemperature ?: 0,
                temperature = device.state?.temperature ?: 8,
                mode = device.state?.mode ?: "",
            ),
            img = R.drawable.aspiradora
        )
    }

    fun setFreezerTemperature(deviceId: String,temp: Int) {
        fetchJob?.cancel()
        fetchJob = viewModelScope.launch {
            runCatching {
                val apiService = RetrofitClient.getApiService()
                apiService.executePI( deviceId, "setFreezerTemperature", listOf(temp))
            }.onSuccess {
                _uiState.update { currentState ->
                    currentState.copy(
                        state = currentState.state.copy(freezerTemperature = temp)
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

}