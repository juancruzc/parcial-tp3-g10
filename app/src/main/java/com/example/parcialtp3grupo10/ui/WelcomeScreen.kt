package com.example.parcialtp3grupo10.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.parcialtp3grupo10.R

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
            // Imagen en lugar de texto
            Image(
                painter = painterResource(id = R.drawable.img), // Reemplaza "your_image" con el nombre de tu imagen en drawable
                contentDescription = "Logo de Nectar", // Descripción de la imagen
                modifier = Modifier.size(300.dp) // Ajusta el tamaño según sea necesario
            )
            Spacer(modifier = Modifier.height(4.dp)) // Reducido el espacio entre el logo y el texto
            Text(
                text = "online groceries", // Subtítulo actualizado
                fontSize = 24.sp,         // Tamaño de la fuente
                color = Color.White        // Cambiado el color del texto a blanco
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