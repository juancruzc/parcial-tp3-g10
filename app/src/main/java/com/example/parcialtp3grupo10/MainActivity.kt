package com.example.parcialtp3grupo10

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.parcialtp3grupo10.ui.BeverageScreen
import com.example.parcialtp3grupo10.ui.WelcomeScreen
import com.example.parcialtp3grupo10.ui.SecondScreen
import com.example.parcialtp3grupo10.ui.LoginScreen
import com.example.parcialtp3grupo10.ui.RegisterScreen
import com.example.parcialtp3grupo10.ui.FifthScreen
import com.example.parcialtp3grupo10.ui.FindProductsScreen
import com.example.parcialtp3grupo10.ui.theme.ParcialTP3Grupo10Theme
import kotlinx.coroutines.delay
import com.google.firebase.FirebaseApp
import com.google.firebase.ktx.Firebase
import com.google.firebase.ktx.initialize
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        try {
            if (FirebaseApp.getApps(this).isEmpty()) {
                FirebaseApp.initializeApp(this)
                Log.d("Firebase", "Firebase initialized successfully")
            } else {
                Log.d("Firebase", "Firebase was already initialized")
            }
        } catch (e: Exception) {
            Log.e("Firebase", "Error initializing Firebase: ${e.message}")
            e.printStackTrace()
        }

        setContent {
            var isDarkMode by remember { mutableStateOf(false) } // Estado del modo oscuro

            ParcialTP3Grupo10Theme(darkTheme = isDarkMode) { // Usar el estado del modo oscuro
                val navController: NavHostController = rememberNavController()

                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    NavHost(navController = navController, startDestination = "welcome") {
                        composable("welcome") {
                            WelcomeScreen()
                            LaunchedEffect(Unit) {
                                delay(2000)
                                navController.navigate("welcomeStore")
                            }
                        }
                        composable("welcomeStore") {
                            SecondScreen(navController)
                        }
                        composable("login") {
                            LoginScreen(navController)
                        }
                        composable("register") {
                            RegisterScreen(navController)
                        }
                        composable("lastScreen") {
                            FifthScreen(navController)
                        }
                        composable("home") {
                            HomeScreen(navController, { isDarkMode = !isDarkMode }, isDarkMode)
                        }
                        composable("explore") {
                            FindProductsScreen(navController, { isDarkMode = !isDarkMode }, isDarkMode)
                        }
                        composable("cart") {
                            CartScreen(navController, { isDarkMode = !isDarkMode }, isDarkMode)
                        }
                        composable("favourites") {
                            FavoritesScreen(navController, { isDarkMode = !isDarkMode }, isDarkMode)
                        }
                        composable("account") {
                            AccountScreen(navController, { isDarkMode = !isDarkMode }, isDarkMode)
                        }
                        composable("beverages") {
                            BeverageScreen(navController = navController)
                        }
                        composable("success") {
                            SuccessScreen(navController)
                        }
                        composable("search") {
                            SearchScreen(navController, { isDarkMode = !isDarkMode }, isDarkMode)
                        }
                    }
                }
            }
        }
    }
}
