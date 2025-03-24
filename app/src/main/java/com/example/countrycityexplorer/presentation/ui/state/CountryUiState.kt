package com.example.countrycityexplorer.presentation.ui.state

import com.example.countrycityexplorer.domain.model.CountryItem

sealed class CountryUiState {
    object Loading : CountryUiState()
    data class Success(val countries: List<CountryItem>) : CountryUiState()
    data class Error(val message: String) : CountryUiState()
}
