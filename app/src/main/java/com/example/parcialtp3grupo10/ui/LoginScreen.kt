package com.example.parcialtp3grupo10.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import androidx.compose.ui.res.painterResource
import com.example.parcialtp3grupo10.R
import androidx.compose.foundation.Image

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(navController: NavHostController) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier
                .padding(32.dp)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.Start
        ) {
            // Imagen centrada horizontalmente
            Image(
                painter = painterResource(id = R.drawable.img_3), // Reemplaza con el nombre de tu imagen
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp) // Ajusta la altura según sea necesario
                    .align(Alignment.CenterHorizontally) // Centra la imagen horizontalmente
            )

            Spacer(modifier = Modifier.height(116.dp)) // Espaciado entre la imagen y el título

            Text(
                text = "Sign In",
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(bottom = 8.dp)
            )

            Text(
                text = "Enter your email and password",
                fontSize = 16.sp,
                color = Color.Gray,
                modifier = Modifier.padding(bottom = 24.dp)
            )

            // Campo de texto para el correo electrónico
            Text(
                text = "Email",
                color = Color.Gray,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(bottom = 4.dp)
            )
            TextField(
                value = email,
                onValueChange = { email = it },
                modifier = Modifier.fillMaxWidth(),
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = Color.Transparent,
                    unfocusedContainerColor = Color.Transparent,
                    focusedTextColor = Color.Black,
                    unfocusedTextColor = Color.Black
                )
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Campo de texto para la contraseña
            Text(
                text = "Password",
                color = Color.Gray,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(bottom = 4.dp)
            )
            TextField(
                value = password,
                onValueChange = { password = it },
                visualTransformation = PasswordVisualTransformation(),
                modifier = Modifier.fillMaxWidth(),
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = Color.Transparent,
                    unfocusedContainerColor = Color.Transparent,
                    focusedTextColor = Color.Black,
                    unfocusedTextColor = Color.Black
                )
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Texto "Forgot password?" alineado a la derecha
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Spacer(modifier = Modifier.weight(1f))
                Text(
                    text = "Forgot password?",
                    color = Color.Black,
                    modifier = Modifier.padding(top = 8.dp)
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Botón "Log In"
            Button(
                onClick = {
                    navController.navigate("lastScreen") // Navegar a la última pantalla
                },
                modifier = Modifier
                    .width(364.dp)
                    .height(67.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF53B175)
                )
            ) {
                Text(
                    "Log In",
                    color = Color.White
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Texto para ir a la pantalla de registro
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Don't have an account? ",
                    color = Color.Black,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold
                )

                Text(
                    text = "Sign Up",
                    color = Color(0xFF53B175),
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.clickable {
                        navController.navigate("register") // Navegar a la pantalla de registro
                    }
                )
            }

            Spacer(modifier = Modifier.weight(1f)) // Este espaciador empuja a img_2 hacia abajo

            // Imagen en la parte inferior
            Image(
                painter = painterResource(id = R.drawable.img_2), // Reemplaza con el nombre de tu imagen
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(15.dp) // Ajusta la altura según sea necesario
                    .align(Alignment.CenterHorizontally) // Centra la imagen horizontalmente
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun LoginScreenPreview() {
    val navController = rememberNavController()
    LoginScreen(navController = navController)
}