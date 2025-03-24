package com.example.countrycityexplorer.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.countrycityexplorer.data.api.CountryApiService
import com.example.countrycityexplorer.data.repositoryImpls.CountryRepositoryImpl
import com.example.countrycityexplorer.domain.usecase.GetCountriesUseCase
import com.example.countrycityexplorer.domain.usecase.GetStatesUseCase
import com.example.countrycityexplorer.presentation.ui.screens.CountryListScreen
import com.example.countrycityexplorer.presentation.ui.screens.StateListScreen
import com.example.countrycityexplorer.presentation.viewmodel.*
import com.example.countrycityexplorer.ui.theme.ThemeViewModel
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Composable
fun NavGraph(
    navController: NavHostController,
    themeViewModel: ThemeViewModel
) {
    val api = Retrofit.Builder()
        .baseUrl("https://countriesnow.space/api/v0.1/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(CountryApiService::class.java)

    NavHost(
        navController = navController,
        startDestination = NavRoutes.CountryList.route
    ) {

        composable(NavRoutes.CountryList.route) {
            val repo = CountryRepositoryImpl(api)
            val useCase = GetCountriesUseCase(repo)
            val factory = CountryViewModelFactory(useCase)
            val countryViewModel: CountryViewModel = viewModel(factory = factory)

            CountryListScreen(
                navController = navController,
                viewModel = countryViewModel,
                themeViewModel = themeViewModel
            )
        }

        composable(NavRoutes.StateList.route) { backStackEntry ->
            val countryCode = backStackEntry.arguments?.getString("countryCode") ?: ""

            val repo = CountryRepositoryImpl(api)
            val useCase = GetStatesUseCase(repo)
            val factory = StateViewModelFactory(useCase)
            val stateViewModel: StateViewModel = viewModel(factory = factory)

            StateListScreen(
                navController = navController,
                countryCode = countryCode,
                viewModel = stateViewModel
            )
        }

        composable(NavRoutes.CityList.route) { backStackEntry ->
            val countryCode = backStackEntry.arguments?.getString("countryCode") ?: ""
            val stateCode = backStackEntry.arguments?.getString("stateCode") ?: ""

            // Add CityViewModel and screen if needed
        }
    }
}
