package com.example.countrycityexplorer.domain.repository

import com.example.countrycityexplorer.domain.model.City
import com.example.countrycityexplorer.domain.model.Country
import com.example.countrycityexplorer.domain.model.CountryItem
import com.example.countrycityexplorer.domain.model.State
interface CountryRepository {
    suspend fun getCountries(): Result<List<CountryItem>>
    suspend fun getStates(countryCode: String): Result<List<State>>
    suspend fun getCities(countryCode: String, stateCode: String): Result<List<City>>
}
