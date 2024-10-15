package com.example.parcialtp3grupo10

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.foundation.layout.padding
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.parcialtp3grupo10.ui.WelcomeScreen
import com.example.parcialtp3grupo10.ui.SecondScreen
import com.example.parcialtp3grupo10.ui.LoginScreen
import com.example.parcialtp3grupo10.ui.RegisterScreen
import com.example.parcialtp3grupo10.ui.FifthScreen
import com.example.parcialtp3grupo10.ui.FindProductsScreen
import com.example.parcialtp3grupo10.ui.theme.ParcialTP3Grupo10Theme
import kotlinx.coroutines.delay

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ParcialTP3Grupo10Theme {
                val navController: NavHostController = rememberNavController()

                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    NavHost(navController = navController, startDestination = "welcome") {
                        composable("welcome") {
                            WelcomeScreen()
                            LaunchedEffect(Unit) {
                                delay(3000L)
                                navController.navigate("second")
                            }
                        }
                        composable("second") {
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
                            HomeScreen(navController)
                        }
                        composable("explore") {
                            FindProductsScreen(navController)
                        }
                        composable("cart") {
                            CartScreen(Modifier, navController)
                        }
                        composable("favourites") {
                            FavoritesScreen(navController)
                        }
                        composable("account") {
                            AccountScreen(navController)
                        }
                        composable("search") {
                            SearchScreen(navController)
                        }
                    }
                }
            }
        }
    }
}