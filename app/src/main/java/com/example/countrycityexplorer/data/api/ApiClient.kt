package com.example.countrycityexplorer.data.api

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiClient {
   val api: CountryApiService by lazy{
       Retrofit.Builder()
           .baseUrl("https://countriesnow.space/api/v0.1/")
           .addConverterFactory(GsonConverterFactory.create())
           .build()
           .create(CountryApiService::class.java)
   }
}
