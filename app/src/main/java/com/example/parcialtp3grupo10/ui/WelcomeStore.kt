package com.example.parcialtp3grupo10.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.text.font.FontWeight
import androidx.navigation.NavHostController
import androidx.compose.ui.graphics.graphicsLayer
import com.example.parcialtp3grupo10.R

@Composable
fun SecondScreen(navController: NavHostController?) {
    // Cargar la imagen de fondo
    val backgroundImage: Painter = painterResource(id = R.drawable.man)
    // Cargar la imagen img_1
    val img1: Painter = painterResource(id = R.drawable.img_1) // Reemplaza "img_1" con el nombre de tu imagen

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Transparent), // Fondo transparente para poder ver la imagen
        contentAlignment = Alignment.BottomCenter
    ) {
        // Imagen de fondo
        Image(
            painter = backgroundImage,
            contentDescription = null,
            modifier = Modifier
                .fillMaxSize()
                .graphicsLayer(
                    scaleX = 1.1f, // Aumentar el zoom al 10%
                    scaleY = 1.1f, // Aumentar el zoom al 10%
                    translationX = -40f // Desplazar hacia la izquierda (ajusta el valor si es necesario)
                ),
            contentScale = androidx.compose.ui.layout.ContentScale.Crop // Escalar para cubrir todo
        )

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 32.dp, vertical = 48.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center // Centra el contenido verticalmente
        ) {
            // Imagen img_1 centrada
            Image(
                painter = img1,
                contentDescription = "Logo de img_1", // Descripción de la imagen
                modifier = Modifier.size(60.dp) // Ajusta el tamaño según sea necesario
            )

            Spacer(modifier = Modifier.height(16.dp)) // Espaciado entre la imagen y el título

            // Título
            Text(
                text = "Welcome",
                fontSize = 40.sp,
                color = Color.White
            )

            // Subtítulo
            Text(
                text = "to our store",
                fontSize = 40.sp,
                color = Color.White
            )

            Spacer(modifier = Modifier.height(8.dp)) // Espaciado entre el subtítulo y el siguiente texto

            // Texto en gris
            Text(
                text = "Get your groceries in as fast as one hour",
                fontSize = 16.sp,
                color = Color.Gray
            )

            Spacer(modifier = Modifier.height(20.dp)) // Espacio entre el texto y el botón

            // Botón
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