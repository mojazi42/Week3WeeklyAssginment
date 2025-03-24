package com.example.countrycityexplorer.presentation.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.example.countrycityexplorer.presentation.viewmodel.StateViewModel
import com.example.countrycityexplorer.presentation.ui.state.StateUiState


@Composable
fun StateListScreen(
    navController: NavHostController,
    countryCode: String,
    viewModel: StateViewModel
) {
    val uiState by viewModel.uiState.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.getStates(countryCode)
    }

    Box(modifier = Modifier.fillMaxSize()) {
        when (uiState) {
            is StateUiState.Loading -> {
                CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
            }

            is StateUiState.Error -> {
                Text(
                    text = "Error: ${(uiState as StateUiState.Error).message}",
                    modifier = Modifier.align(Alignment.Center)
                )
            }

            is StateUiState.Success -> {
                val states = (uiState as StateUiState.Success).states
                LazyColumn {
                    items(states) { state ->
                        Card(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(8.dp)
                                .clickable {
                                    navController.navigate("cities/${countryCode}/${state.state_code}")
                                }
                        ) {
                            Column(modifier = Modifier.padding(16.dp)) {
                                Text(text = state.name)
                                Text(text = "Code: ${state.state_code}")
                            }
                        }
                    }
                }
            }
        }
    }
}
