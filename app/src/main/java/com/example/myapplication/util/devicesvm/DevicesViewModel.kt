package com.example.myapplication.util.devicesvm

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.myapplication.data.DevicesUiState
import com.example.myapplication.data.network.RetrofitClient
import com.example.myapplication.data.network.models.Device
import com.example.myapplication.data.network.models.DeviceToAdd
import com.example.myapplication.data.network.models.Meta
import com.example.myapplication.data.network.models.Type
import com.example.myapplication.data.network.models.Type2
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
        fetchJob?.cancel()
        fetchJob = viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true) }
            runCatching {
                val apiService =  RetrofitClient.getApiService()
                apiService.getDevices()
            }.onSuccess { response ->
                _uiState.update { it.copy(devices = response.body(),
                isLoading = false)
                }
                //uiState.value.devices?.devices?.forEach {println(it.state)}
            }.onFailure { e->
                _uiState.update { it.copy(
                    message = e.message,
                    isLoading = false
                ) }
            }
        }
    }

    fun addNewDevice(name: String, id: String) {
        fetchJob?.cancel()
        fetchJob = viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true) }
            runCatching {
                val apiService =  RetrofitClient.getApiService()
                val deviceToAdd = DeviceToAdd(
                    Type2("go46xmbqeomjrsjr"),
                    "elwey",
                    Meta("", "")
                )
                apiService.addNewDevice(deviceToAdd)
            }.onSuccess { //response ->
                /*_uiState.update { it.copy(devices = response.body(),
                    isLoading = false
                }*/
                uiState.value.devices?.devices?.forEach {println(it.name)}
            }.onFailure { e->
                _uiState.update { it.copy(
                    message = e.message,
                    isLoading = false
                ) }
            }
        }
    }
}