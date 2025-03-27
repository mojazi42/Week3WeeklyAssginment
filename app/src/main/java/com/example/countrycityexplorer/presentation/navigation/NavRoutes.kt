package com.example.countrycityexplorer.presentation.navigation

sealed class NavRoutes(val route: String) {
    object CountryList : NavRoutes("countries")

    object StateList : NavRoutes("states/{countryCode}") {
        fun withArgs(countryCode: String): String = "states/$countryCode"
    }


    object CityList : NavRoutes("cities/{country}/{state}") {
        fun withArgs(countryCode: String, stateCode: String): String =
            "cities/$countryCode/$stateCode"
    }
}
