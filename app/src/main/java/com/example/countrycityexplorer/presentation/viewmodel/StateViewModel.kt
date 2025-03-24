package com.example.countrycityexplorer.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.countrycityexplorer.domain.usecase.GetStatesUseCase
import com.example.countrycityexplorer.presentation.ui.state.StateUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class StateViewModel(
    private val getStatesUseCase: GetStatesUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow<StateUiState>(StateUiState.Loading)
    val uiState: StateFlow<StateUiState> = _uiState

    fun getStates(countryCode: String) {
        viewModelScope.launch {
            _uiState.value = StateUiState.Loading
            try {
                val result = getStatesUseCase(countryCode)
                result.onSuccess { states ->
                    _uiState.value = StateUiState.Success(states)
                }.onFailure { throwable ->
                    _uiState.value = StateUiState.Error(throwable.message ?: "Unknown Error")
                }
            } catch (e: Exception) {
                _uiState.value = StateUiState.Error(e.message ?: "Unexpected Error")
            }
        }
    }
}
