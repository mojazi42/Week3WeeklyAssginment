package com.example.testapi

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
import com.example.testapi.ui.theme.TestAPITheme
import retrofit2.http.GET
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import android.util.Log



interface ApiService{
    @GET("users")
    suspend fun getUsers():List<User>
}

object RetrofitClient{
    private const val BASE_URL = "https://jsonplaceholder.typicode.com/"
    val apiService: ApiService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)
    }
}

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TestAPITheme {
                UserListScreen()
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    TestAPITheme {
        Greeting("Android")
    }
}

data class User(
    val id: Int,
    val name: String,
    val email: String
)

class ExampleViewModel : ViewModel() {
    fun fetchUsers() {
        viewModelScope.launch {
            try {
                val users = RetrofitClient.apiService.getUsers()
                Log.d("API", "Users: $users")
            } catch (e: Exception) {
                Log.e("API_ERROR", "Error fetching users", e)
            }
        }
    }
}