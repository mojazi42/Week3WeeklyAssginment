package com.example.countrycityexplorer.data.api

import com.example.countrycityexplorer.data.model.ApiResponse
import com.example.countrycityexplorer.domain.model.City
import com.example.countrycityexplorer.domain.model.Country
import com.example.countrycityexplorer.domain.model.CountryItem
import com.example.countrycityexplorer.domain.model.State
import retrofit2.http.GET
import retrofit2.http.Path


interface CountryApiService {

    @GET("countries")
    suspend fun getCountries(): ApiResponse<List<CountryItem>>



    @GET("countries/{country_code}/states")
    suspend fun getStates(
        @Path("country_code") countryCode: String
    ):  ApiResponse<List<State>>

    @GET("countries/{country_code}/states/{state_code}/cities")
    suspend fun getCities(
        @Path("country_code") countryCode: String,
        @Path("state_code") stateCode: String
    ): ApiResponse<List<City>>
}
