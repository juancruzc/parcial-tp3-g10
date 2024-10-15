package com.example.parcialtp3grupo10.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.parcialtp3grupo10.R
import com.example.parcialtp3grupo10.ui.components.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegisterScreen(navController: NavHostController?) {
    // Variables locales para los campos de entrada
    var username by remember { mutableStateOf("") }
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
                .fillMaxSize()
                .background(Color.White)
                .padding(32.dp)
                .verticalScroll(rememberScrollState()), // Asegúrate de que este modificador esté aquí
            horizontalAlignment = Alignment.Start
        ) {
            // Imagen superior
            Image(
                painter = painterResource(id = R.drawable.img_3),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)
                    .align(Alignment.CenterHorizontally)
            )

            Spacer(modifier = Modifier.height(116.dp))

            // Componente de título y subtítulo
            TitleAndSubtitle()

            Spacer(modifier = Modifier.height(16.dp))

            // Campos de texto
            InputField(
                label = "Username",
                value = username,
                onValueChange = { username = it }  // Actualización de estado
            )
            Spacer(modifier = Modifier.height(16.dp))

            InputField(
                label = "Email",
                value = email,
                onValueChange = { email = it }  // Actualización de estado
            )
            Spacer(modifier = Modifier.height(16.dp))

            InputField(
                label = "Password",
                value = password,
                onValueChange = { password = it },  // Actualización de estado
                isPassword = true  // Para ocultar el texto de la contraseña
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Texto de términos y condiciones
            TermsAndConditionsText()

            Spacer(modifier = Modifier.height(16.dp))

            // Botón de "Sign Up"
            ButtonBar2(title = "Sign Up", onClick = { navController?.navigate("lastScreen") })

            Spacer(modifier = Modifier.height(16.dp))

            // Botón de retroceso para volver a la pantalla de inicio de sesión
            BackButtonBar(title = "Back to Login", navController = navController, destination = "login")

            Spacer(modifier = Modifier.weight(1f))

            // Imagen inferior
            Image(
                painter = painterResource(id = R.drawable.img_2),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(15.dp)
                    .align(Alignment.CenterHorizontally)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun RegisterScreenPreview() {
    RegisterScreen(navController = rememberNavController())
}
