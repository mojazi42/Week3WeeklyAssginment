package com.example.countrycityexplorer.data.repositoryImpls

import com.example.countrycityexplorer.data.api.CityRequest
import com.example.countrycityexplorer.data.api.CountryApiService
import com.example.countrycityexplorer.data.api.StateRequest
import com.example.countrycityexplorer.domain.model.City
import com.example.countrycityexplorer.domain.model.CountryItem
import com.example.countrycityexplorer.domain.model.State
import com.example.countrycityexplorer.domain.repository.CountryRepository

class CountryRepositoryImpl(
    private val api: CountryApiService
) : CountryRepository {

    override suspend fun getCountries(): Result<List<CountryItem>> {
        return try {
            val response = api.getCountries()
            if (!response.error) {
                Result.success(response.data)
            } else {
                Result.failure(Exception("API Error: ${response.msg}"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun getStates(countryCode: String): Result<List<State>> {
        return try {
            val response = api.getStates(StateRequest(country = countryCode))
            if (!response.error) {
                Result.success(response.data.states) // ✅ Access inner list
            } else {
                Result.failure(Exception("API Error: ${response.msg}"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }



    override suspend fun getCities(countryCode: String, stateCode: String): Result<List<City>> {
        return try {
            val response = api.getCities(CityRequest(country = countryCode, state = stateCode))
            if (!response.error) {
                Result.success(response.data)
            } else {
                Result.failure(Exception("API Error: ${response.msg}"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }


}

