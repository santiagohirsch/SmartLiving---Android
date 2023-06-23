package com.example.myapplication.util.devicesvm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.data.RoutineUiState
import com.example.myapplication.data.network.RetrofitClient
import com.example.myapplication.data.network.models.Routine
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

open class RoutineViewModel(routine: Routine) : ViewModel() {
    private val _uiState = MutableStateFlow(RoutineUiState())
    val uiState: StateFlow<RoutineUiState> = _uiState.asStateFlow()
    private var fetchJob: Job? = null

    init {
        _uiState.value = RoutineUiState(
            id = routine.id,
            name =  routine.name,
            actions = routine.actions
        )
    }

    fun executeRoutine() {
        fetchJob?.cancel()
        fetchJob = viewModelScope.launch {
            runCatching {
                val apiService = RetrofitClient.getApiService()
                apiService.executeRoutine(uiState.value.id.toString())
            }
        }

    }

    fun deleteRoutine(routineId: String) {
        fetchJob?.cancel()
        fetchJob = viewModelScope.launch {
            runCatching {
                val apiService = RetrofitClient.getApiService()
                apiService.deleteRoutine(routineId)
            }
        }

    }

}