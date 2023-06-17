package com.example.myapplication.util.devicesvm

import androidx.lifecycle.viewModelScope
import com.example.myapplication.R
import com.example.myapplication.data.RefrigeratorUiState
import com.example.myapplication.data.network.RetrofitClient
import com.example.myapplication.data.network.models.Params
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class RefrigeratorViewModel( name: String) : DeviceViewModel("refrigerator", R.drawable.heladera, name){
    private val _uiState = MutableStateFlow(RefrigeratorUiState())
    val uiState: StateFlow<RefrigeratorUiState> = _uiState.asStateFlow()

    private var fetchJob: Job? = null

    fun setFreezerTemperature(deviceId: String,temp: Int) {
        fetchJob?.cancel()
        fetchJob = viewModelScope.launch {
            runCatching {
                val apiService = RetrofitClient.getApiService()
                apiService.executePI( deviceId, "setFreezerTemperature", listOf(temp))
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