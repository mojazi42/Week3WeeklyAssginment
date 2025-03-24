package com.example.countrycityexplorer.domain.usecase

import com.example.countrycityexplorer.domain.model.City
import com.example.countrycityexplorer.domain.repository.CountryRepository

class GetCitiesUseCase(
    private val repository: CountryRepository
) {
    suspend operator fun invoke(countryCode: String, stateCode: String): Result<List<City>> {
        return repository.getCities(countryCode, stateCode)
    }
}
