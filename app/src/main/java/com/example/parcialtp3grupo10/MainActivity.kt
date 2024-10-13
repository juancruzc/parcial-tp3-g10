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
import com.example.parcialtp3grupo10.ui.WelcomeScreen
import com.example.parcialtp3grupo10.ui.SecondScreen
import com.example.parcialtp3grupo10.ui.LoginScreen
import com.example.parcialtp3grupo10.ui.RegisterScreen
import com.example.parcialtp3grupo10.ui.FifthScreen
import kotlinx.coroutines.delay

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController: NavHostController = rememberNavController()

            Surface(
                modifier = Modifier.fillMaxSize(),
                color = MaterialTheme.colorScheme.background
            ) {
                NavHost(navController = navController, startDestination = "welcome") {
                    composable("welcome") {
                        WelcomeScreen() // Elimina el viewModel
                        LaunchedEffect(Unit) {
                            delay(3000L)  // 3 segundos
                            navController.navigate("second")
                        }
                    }
                    composable("second") {
                        SecondScreen(navController)
                    }
                    composable("login") {
                        LoginScreen(navController)
                    }

                    composable("register") {  // Nueva ruta para la pantalla de registro
                        RegisterScreen(navController)
                    }

                    composable("lastScreen") {
                        FifthScreen()
                    }
                }
            }
        }
    }
}