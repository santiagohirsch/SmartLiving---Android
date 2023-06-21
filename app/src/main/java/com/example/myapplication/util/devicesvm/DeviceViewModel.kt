package com.example.myapplication.util.devicesvm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.data.network.RetrofitClient
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

open class DeviceViewModel(var type: String, val img: Int, var name: String?, var id: String?) : ViewModel() {
    private var fetchJob: Job? = null

    fun delete(deviceId: String) {
        fetchJob?.cancel()
        fetchJob = viewModelScope.launch {
            runCatching {
                val apiService =  RetrofitClient.getApiService()
                apiService.delete(deviceId)
            }
        }
    }
}
