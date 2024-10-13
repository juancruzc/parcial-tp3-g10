package com.example.parcialtp3grupo10.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.tooling.preview.Preview

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FifthScreen() {
    // Variables para manejar los estados de los desplegables
    var selectedLocation by remember { mutableStateOf("Buenos Aires") }
    var expandedLocation by remember { mutableStateOf(false) } // Estado del menú desplegable de zonas

    var selectedArea by remember { mutableStateOf("Area 1") }
    var expandedArea by remember { mutableStateOf(false) } // Estado del menú desplegable de áreas

    // Opciones para el menú desplegable
    val locations = listOf("Buenos Aires", "Mendoza")
    val areas = listOf("Area 1", "Area 2")

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White), // Fondo blanco
        contentAlignment = Alignment.Center // Centramos el contenido
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(32.dp), // Espacio alrededor del contenido
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(20.dp) // Espaciado entre elementos
        ) {
            // Título principal
            Text(
                text = "Select Your Location",
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )

            // Mensaje descriptivo
            Text(
                text = "Switch on your location to stay in tune with what’s happening in your area.",
                fontSize = 16.sp,
                color = Color.Gray,
                textAlign = androidx.compose.ui.text.style.TextAlign.Center // Centrar texto
            )

            Spacer(modifier = Modifier.height(30.dp))

            // Desplegable para seleccionar zona
            ExposedDropdownMenuBox(
                expanded = expandedLocation,
                onExpandedChange = { expandedLocation = !expandedLocation } // Manejar la expansión del menú
            ) {
                TextField(
                    readOnly = true,
                    value = selectedLocation,
                    onValueChange = { },
                    label = { Text("Your Zone") },
                    trailingIcon = {
                        ExposedDropdownMenuDefaults.TrailingIcon(expanded = expandedLocation)
                    },
                    colors = TextFieldDefaults.textFieldColors(
                        containerColor = Color.White // Color de fondo del TextField
                    ),
                    modifier = Modifier.fillMaxWidth()
                )

                // Opciones del menú desplegable
                ExposedDropdownMenu(
                    expanded = expandedLocation,
                    onDismissRequest = { expandedLocation = false } // Cerrar el menú al hacer clic fuera
                ) {
                    locations.forEach { location ->
                        DropdownMenuItem(
                            text = { Text(location) },
                            onClick = {
                                selectedLocation = location
                                expandedLocation = false // Cerrar el menú al seleccionar una opción
                            }
                        )
                    }
                }
            }

            // Desplegable para seleccionar área
            ExposedDropdownMenuBox(
                expanded = expandedArea,
                onExpandedChange = { expandedArea = !expandedArea } // Manejar la expansión del menú
            ) {
                TextField(
                    readOnly = true,
                    value = selectedArea,
                    onValueChange = { },
                    label = { Text("Your Area") },
                    trailingIcon = {
                        ExposedDropdownMenuDefaults.TrailingIcon(expanded = expandedArea)
                    },
                    colors = TextFieldDefaults.textFieldColors(
                        containerColor = Color.White // Color de fondo del TextField
                    ),
                    modifier = Modifier.fillMaxWidth()
                )

                // Opciones del menú desplegable
                ExposedDropdownMenu(
                    expanded = expandedArea,
                    onDismissRequest = { expandedArea = false } // Cerrar el menú al hacer clic fuera
                ) {
                    areas.forEach { area ->
                        DropdownMenuItem(
                            text = { Text(area) },
                            onClick = {
                                selectedArea = area
                                expandedArea = false // Cerrar el menú al seleccionar una opción
                            }
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Botón de confirmación
            Button(
                onClick = { /* Acción del botón */ },
                modifier = Modifier
                    .width(364.dp) // Ancho en dp
                    .height(67.dp), // Altura en dp
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF53B175) // Color de fondo (verde)
                )
            ) {
                Text(
                    "Submit",
                    color = Color.White // Texto en blanco para el botón
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun FifthScreenPreview() {
    FifthScreen()
}