package com.example.parcialtp3grupo10.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.text.font.FontWeight
import androidx.navigation.NavHostController
import androidx.compose.runtime.remember

@Composable
fun SecondScreen(navController: NavHostController?) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        contentAlignment = Alignment.BottomCenter
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 32.dp, vertical = 48.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            // Contenido de SecondScreen
            Text(
                text = "WELCOME TO OUR STORE",
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )

            Button(
                onClick = { navController?.navigate("login") }, // Navega a LoginScreen
                modifier = Modifier
                    .width(364.dp)
                    .height(67.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF53B175)
                )
            ) {
                Text(
                    text = "Get Started",
                    color = Color.White,
                    fontSize = 16.sp
                )
            }
        }
    }
}

// Previsualización para ver la pantalla en el editor
@Preview(showBackground = true)
@Composable
fun SecondScreenPreview() {
    SecondScreen(navController = null) // navController es null para la preview
}