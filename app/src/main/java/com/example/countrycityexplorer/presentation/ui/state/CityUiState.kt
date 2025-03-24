package com.example.countrycityexplorer.presentation.ui.state

import com.example.countrycityexplorer.domain.model.City

sealed class CityUiState {
    object Loading : CityUiState()
    data class Success(val cities: List<City>) : CityUiState()
    data class Error(val message: String) : CityUiState()
}
