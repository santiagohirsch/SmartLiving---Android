package com.example.myapplication.util.devicesvm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.myapplication.data.DevicesUiState
import com.example.myapplication.data.LogsUiState
import com.example.myapplication.data.network.RetrofitClient
import com.example.myapplication.data.network.models.Device
import com.example.myapplication.data.network.models.DeviceToAdd
import com.example.myapplication.data.network.models.Log
import com.example.myapplication.data.network.models.Meta
import com.example.myapplication.data.network.models.Type
import com.example.myapplication.data.network.models.Type2
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

import kotlinx.coroutines.launch
import java.time.Instant

class DevicesViewModel: ViewModel() {
    private val _uiState = MutableStateFlow(DevicesUiState())
    val uiState: StateFlow<DevicesUiState> = _uiState.asStateFlow()

    private var currentDevices: MutableList<Device> = mutableListOf()

    private val _logUiState = MutableStateFlow(LogsUiState())
    val logUiState: StateFlow<LogsUiState> = _logUiState.asStateFlow()

    private var recents: MutableList<Log> = mutableListOf()

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
                //uiState.value.devices?.devices?.forEach {println(it.name)}
                response.body()?.devices?.let { devices ->
                    currentDevices = devices.toMutableList()
                }
            }.onFailure { e->
                _uiState.update { it.copy(
                    message = e.message,
                    isLoading = false
                ) }
            }
        }
    }

    fun getCurrentDevices() : MutableList<Device> {
        return currentDevices
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

    fun getRecents() : MutableList<Log> {
        return recents
    }
    fun getLogs() {
        fetchJob?.cancel()
        fetchJob = viewModelScope.launch {
            runCatching {
                val apiService =  RetrofitClient.getApiService()
                apiService.getLogs("{limit}", "{offset}")
            }.onSuccess { response ->
                _logUiState.update {
                    it.copy(logs = response.body())
                }
                fillRecents()
            }
        }

    }

    /*
    * < 0 -> 1 before 2
    * > 0 -> 2 before 1
    * */
    private fun compareTimestamps(timestamp1: String, timestamp2: String): Int {
        val instant1 = Instant.parse(timestamp1)
        val instant2 = Instant.parse(timestamp2)

        // Compare the timestamps using Instant.compareTo()
        return instant1.compareTo(instant2)
    }

    private fun fillRecents() {
        val logs = logUiState.value.logs?.logs

        if (logs != null) {
            for (log in logs) {
                for (i in 0 until 3) {
                    if (recents.size < 3) {
                        recents.add(log)
                        break
                    }
                    val currentTimestamp = log.timestamp.toString()

                    val comparisonResult = compareTimestamps(currentTimestamp, recents[i].timestamp.toString())

                    if (comparisonResult < 0) {
                        // Shift the existing timestamps to the right
                        for (j in recents.size - 1 downTo i + 1) {
                            recents[j] = recents[j - 1]
                        }

                        // Insert the current timestamp at the appropriate position
                        recents[i] = log

                        break // Exit the inner loop
                    }
                }
            }
        }
    }
}