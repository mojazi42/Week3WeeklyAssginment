package com.example.countrycityexplorer.presentation.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.countrycityexplorer.domain.model.CountryItem
import com.example.countrycityexplorer.presentation.navigation.NavRoutes
import com.example.countrycityexplorer.presentation.ui.state.CountryUiState
import com.example.countrycityexplorer.presentation.viewmodel.CountryViewModel
import com.example.countrycityexplorer.ui.theme.ThemeViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CountryListScreen(
    navController: NavHostController,
    viewModel: CountryViewModel,
    themeViewModel: ThemeViewModel
) {
    val uiState by viewModel.uiState.collectAsState()

   Scaffold(
      topBar = {
         TopAppBar(
             title = { Text("Countries") },
            actions = {
                val isDark = themeViewModel.isDarkTheme
                Switch(
                    checked = isDark,
                    onCheckedChange = { themeViewModel.toggleTheme() }
                )

            }
          )
       }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            when (uiState) {
                is CountryUiState.Loading -> {
                    CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
                }

                is CountryUiState.Error -> {
                    val message = (uiState as CountryUiState.Error).message
                    Text(text = message, modifier = Modifier.align(Alignment.Center))
                }

                is CountryUiState.Success -> {
                    val countriesList = (uiState as CountryUiState.Success).countries
                    LazyColumn(modifier = Modifier.fillMaxSize()) {
                        items(countriesList) { countryItem ->
                            CountryItemCard(
                                countryItem = countryItem,
                                onClick = {
                                    navController.navigate(
                                        NavRoutes.StateList.withArgs(countryItem.iso2)
                                    )
                                }
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun CountryItemCard(countryItem: CountryItem, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable { onClick() }
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = "Country: ${countryItem.country}", style = MaterialTheme.typography.titleMedium)
            Text(text = "ISO2: ${countryItem.iso2}", style = MaterialTheme.typography.bodySmall)
            Text(text = "ISO3: ${countryItem.iso3}", style = MaterialTheme.typography.bodySmall)
        }
    }
}
