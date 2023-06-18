package com.example.myapplication.util.devicesvm

import androidx.lifecycle.ViewModel
import com.example.myapplication.data.RoutineUiState
import com.example.myapplication.data.network.models.Routine
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

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


}