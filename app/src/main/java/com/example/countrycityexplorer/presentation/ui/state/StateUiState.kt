package com.example.countrycityexplorer.presentation.ui.state


import com.example.countrycityexplorer.domain.model.State

sealed class StateUiState {
    object Loading : StateUiState()
    data class Success(val states: List<State>) : StateUiState()
    data class Error(val message: String) : StateUiState()
}
