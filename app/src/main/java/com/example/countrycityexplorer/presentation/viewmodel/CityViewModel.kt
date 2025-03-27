package com.example.countrycityexplorer.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.countrycityexplorer.domain.usecase.GetCitiesUseCase
import com.example.countrycityexplorer.presentation.ui.state.CityUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class CityViewModel(
    private val getCitiesUseCase: GetCitiesUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow<CityUiState>(CityUiState.Loading)
    val uiState: StateFlow<CityUiState> = _uiState

    fun getCities(countryCode: String, stateCode: String) {
        viewModelScope.launch {
            _uiState.value = CityUiState.Loading
            val result = getCitiesUseCase(countryCode, stateCode)
            result.onSuccess {
                _uiState.value = CityUiState.Success(it)
            }.onFailure {
                _uiState.value = CityUiState.Error(it.message ?: "Unknown Error")
            }
        }
    }
}
