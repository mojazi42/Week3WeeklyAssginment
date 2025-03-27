package com.example.countrycityexplorer.data.api

import com.example.countrycityexplorer.data.model.ApiResponse
import com.example.countrycityexplorer.domain.model.StatesWrapper
import com.example.countrycityexplorer.domain.model.City
import com.example.countrycityexplorer.domain.model.CountryItem
import com.google.gson.annotations.SerializedName
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST


interface CountryApiService {

    @GET("countries")
    suspend fun getCountries(): ApiResponse<List<CountryItem>>



    @POST("countries/states")
    suspend fun getStates(@Body request: StateRequest):  ApiResponse<StatesWrapper>

    @POST("countries/states/cities")
    suspend fun getCities(@Body request: CityRequest): ApiResponse<List<City>>
}
data class StateRequest(
    @SerializedName("country") val country: String
)
data class CityRequest(
    val country: String,
    val state: String
)


