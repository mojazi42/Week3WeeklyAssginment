package com.example.countrycityexplorer.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.countrycityexplorer.domain.usecase.GetCountriesUseCase
import com.example.countrycityexplorer.presentation.ui.state.CountryUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import android.util.Log // ✅ for logging

class CountryViewModel(
    private val getCountriesUseCase: GetCountriesUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow<CountryUiState>(CountryUiState.Loading)
    val uiState: StateFlow<CountryUiState> = _uiState

    init {
        fetchCountries()
    }

    private fun fetchCountries() {
        viewModelScope.launch {
            _uiState.value = CountryUiState.Loading
            try {
                val result = getCountriesUseCase()
                result.onSuccess { countries ->
                    Log.d("CountryViewModel", "Countries fetched: ${countries.size}")
                    _uiState.value = CountryUiState.Success(countries)
                }.onFailure { throwable ->
                    Log.e("CountryViewModel", "Fetch failed: ${throwable.message}")
                    _uiState.value = CountryUiState.Error(throwable.message ?: "Unknown Error")
                }
            } catch (e: Exception) {
                Log.e("CountryViewModel", "Exception: ${e.message}")
                _uiState.value = CountryUiState.Error(e.message ?: "Unexpected Error")
            }
        }
    }
}
