package com.example.countrycityexplorer

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.example.countrycityexplorer.presentation.navigation.NavGraph
import com.example.countrycityexplorer.ui.theme.CountryCityExplorerTheme
import com.example.countrycityexplorer.ui.theme.ThemeViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CountryCityExplorerTheme {
                val themeViewModel = ThemeViewModel()

                setContent {
                    CountryCityExplorerTheme(darkTheme = themeViewModel.isDarkTheme) {
                        val navController = rememberNavController()
                        NavGraph(navController = navController, themeViewModel = themeViewModel)
                    }
                }
            }
        }
    }
}


