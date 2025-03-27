package com.example.countrycityexplorer.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.countrycityexplorer.domain.usecase.GetCountriesUseCase

class CountryViewModelFactory(
    private val useCase: GetCountriesUseCase
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST") // ✅ Suppress the unchecked cast warning
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CountryViewModel::class.java)) {
            return CountryViewModel(useCase) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}")
    }
}
