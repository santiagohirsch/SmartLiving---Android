package com.example.myapplication.util.devicesvm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.data.network.RetrofitClient
import com.example.myapplication.data.RoutinesUiState
import com.example.myapplication.data.network.models.Routine
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class RoutinesViewModel: ViewModel() {

    private val _uiState = MutableStateFlow(RoutinesUiState())
    val uiState: StateFlow<RoutinesUiState> = _uiState.asStateFlow()

    private var fetchJob: Job? = null

    private var currentRoutines: MutableList<Routine> = mutableListOf()

    fun getAllRoutines() {
        fetchJob?.cancel()
        fetchJob = viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true) }
            runCatching {
                val apiService =  RetrofitClient.getApiService()
                apiService.getRoutines()
            }.onSuccess { response ->
                _uiState.update { it.copy(routines = response.body(),
                    isLoading = false)
                }
                //uiState.value.routines?.routines?.forEach{ println(it.name)
                response.body()?.routines?.let { devices ->
                    currentRoutines = devices.toMutableList()
                }
            }.onFailure { e->
                _uiState.update { it.copy(
                    message = e.message,
                    isLoading = false
                ) }
            }
        }
    }

    fun getCurrentRoutines() : MutableList<Routine> {
        return currentRoutines
    }


}