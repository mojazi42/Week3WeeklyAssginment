package com.example.countrycityexplorer.domain.model

data class Country(
    val list: List<CountryItem>
)


data class CountryItem(
    val iso2: String,
    val iso3: String,
    val country: String,
    val cities: List<String>
)
