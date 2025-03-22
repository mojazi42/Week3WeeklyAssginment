package com.example.testapi

// UserViewModel.kt
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import androidx.compose.runtime.mutableStateListOf

class UserViewModel : ViewModel() {

    // Holds the list of users
    val users = mutableStateListOf<User>()

    // Call this to fetch users from the API
    fun fetchUsers() {
        viewModelScope.launch {
            try {
                val result = RetrofitClient.apiService.getUsers()
                users.clear()
                users.addAll(result)
            } catch (e: Exception) {
                // You can log the error or show it in the UI
                println("Error: ${e.message}")
            }
        }
    }
}
