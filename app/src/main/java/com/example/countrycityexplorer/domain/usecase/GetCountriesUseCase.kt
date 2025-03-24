package com.example.countrycityexplorer.domain.usecase

import com.example.countrycityexplorer.domain.model.CountryItem
import com.example.countrycityexplorer.domain.repository.CountryRepository

class GetCountriesUseCase(
    private val repository: CountryRepository
) {
    suspend operator fun invoke(): Result<List<CountryItem>> {
        return repository.getCountries()
    }
}
