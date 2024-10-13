package com.example.parcialtp3grupo10.ui

import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

// Composición de la pantalla de bienvenida
@Composable
fun WelcomeScreen() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF53B175)), // Fondo verde claro
        contentAlignment = Alignment.Center // Centrar el contenido
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally // Centramos los textos horizontalmente
        ) {
            Text(
                text = "nectar",              // Título actualizado
                fontSize = 40.sp,              // Tamaño de la fuente
                fontWeight = FontWeight.Bold,  // Negrita
                color = Color.White            // Color del texto
            )
            Spacer(modifier = Modifier.height(8.dp)) // Espaciado entre los textos
            Text(
                text = "online groceriet",      // Subtítulo actualizado
                fontSize = 24.sp,               // Tamaño de la fuente
                color = Color.White             // Color del texto
            )
        }
    }
}

// Previsualización para ver la pantalla en el editor
@Preview(showBackground = true)
@Composable
fun WelcomeScreenPreview() {
    WelcomeScreen()
}