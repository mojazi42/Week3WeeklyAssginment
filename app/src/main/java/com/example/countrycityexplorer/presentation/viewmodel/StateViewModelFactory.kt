package com.example.countrycityexplorer.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.countrycityexplorer.domain.usecase.GetStatesUseCase

class StateViewModelFactory(
    private val getStatesUseCase: GetStatesUseCase
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(StateViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return StateViewModel(getStatesUseCase) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
