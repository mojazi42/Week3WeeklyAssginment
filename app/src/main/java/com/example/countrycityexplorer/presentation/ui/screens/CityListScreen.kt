package com.example.countrycityexplorer.presentation.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.countrycityexplorer.presentation.ui.state.CityUiState
import com.example.countrycityexplorer.presentation.viewmodel.CityViewModel

@Composable
fun CityListScreen(
    navController: NavHostController,
    countryCode: String,
    stateCode: String,
    viewModel: CityViewModel
) {
    val uiState by viewModel.uiState.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.getCities(countryCode, stateCode)
    }

    Box(modifier = Modifier.fillMaxSize()) {
        when (uiState) {
            is CityUiState.Loading -> {
                CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
            }

            is CityUiState.Error -> {
                val errorMessage = (uiState as CityUiState.Error).message
                Text(
                    text = "Error: $errorMessage",
                    modifier = Modifier.align(Alignment.Center)
                )
            }

            is CityUiState.Success -> {
                val cities = (uiState as CityUiState.Success).cities
                LazyColumn(modifier = Modifier.padding(8.dp)) {
                    items(cities) { city ->
                        Card(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 4.dp)
                        ) {
                            Text(
                                text = city.name,
                                modifier = Modifier.padding(16.dp),
                                style = MaterialTheme.typography.bodyLarge
                            )
                        }
                    }
                }
            }
        }
    }
}
