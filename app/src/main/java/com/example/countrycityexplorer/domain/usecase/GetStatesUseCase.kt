package com.example.countrycityexplorer.domain.usecase

import com.example.countrycityexplorer.domain.model.State
import com.example.countrycityexplorer.domain.repository.CountryRepository

class GetStatesUseCase(
    private val repository: CountryRepository
) {
    suspend operator fun invoke(countryCode: String): Result<List<State>> {
        return repository.getStates(countryCode)
    }
}
