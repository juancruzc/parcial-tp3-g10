package com.example.parcialtp3grupo10.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.compose.foundation.Image
import androidx.compose.ui.res.painterResource
import com.example.parcialtp3grupo10.R
import androidx.compose.ui.platform.LocalContext
import com.example.parcialtp3grupo10.ui.components.ButtonBar2

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FifthScreen(navController: NavHostController) {
    var selectedLocation by remember { mutableStateOf("Buenos Aires") }
    var expandedLocation by remember { mutableStateOf(false) }

    var selectedArea by remember { mutableStateOf("Area 1") }
    var expandedArea by remember { mutableStateOf(false) }

    val locations = listOf("Buenos Aires", "Mendoza")
    val areas = listOf("Area 1", "Area 2")

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
    ) {
        // Imagen img_5 arriba a la izquierda
        Image(
            painter = painterResource(id = R.drawable.img_5),
            contentDescription = "Image in top left",
            modifier = Modifier
                .align(Alignment.TopStart) // Alinear arriba a la izquierda
                .padding(16.dp)            // Agregar un pequeño margen desde la esquina
        )

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(32.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(20.dp),
        ) {
            // Imagen img4 arriba del título
            Image(
                painter = painterResource(id = R.drawable.img_4),
                contentDescription = "Image at the top",
                modifier = Modifier.fillMaxWidth() // O puedes especificar un tamaño específico
            )

            Text(
                text = "Select Your Location",
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )

            Text(
                text = "Switch on your location to stay in tune with what’s happening in your area.",
                fontSize = 16.sp,
                color = Color.Gray,
                textAlign = androidx.compose.ui.text.style.TextAlign.Center
            )

            Spacer(modifier = Modifier.height(30.dp))

            // Desplegable para seleccionar zona
            ExposedDropdownMenuBox(
                expanded = expandedLocation,
                onExpandedChange = { expandedLocation = !expandedLocation }
            ) {
                TextField(
                    readOnly = true,
                    value = selectedLocation,
                    onValueChange = { },
                    label = { Text("Your Zone") },
                    trailingIcon = {
                        ExposedDropdownMenuDefaults.TrailingIcon(expanded = expandedLocation)
                    },
                    colors = TextFieldDefaults.colors(
                        focusedContainerColor = Color.Transparent,
                        unfocusedContainerColor = Color.Transparent,
                        focusedTextColor = Color.Black,
                        unfocusedTextColor = Color.Black
                    ),
                    modifier = Modifier.fillMaxWidth()
                )

                ExposedDropdownMenu(
                    expanded = expandedLocation,
                    onDismissRequest = { expandedLocation = false }
                ) {
                    locations.forEach { location ->
                        DropdownMenuItem(
                            text = { Text(location) },
                            onClick = {
                                selectedLocation = location
                                expandedLocation = false
                            }
                        )
                    }
                }
            }

            // Desplegable para seleccionar área
            ExposedDropdownMenuBox(
                expanded = expandedArea,
                onExpandedChange = { expandedArea = !expandedArea }
            ) {
                TextField(
                    readOnly = true,
                    value = selectedArea,
                    onValueChange = { },
                    label = { Text("Your Area") },
                    trailingIcon = {
                        ExposedDropdownMenuDefaults.TrailingIcon(expanded = expandedArea)
                    },
                    colors = TextFieldDefaults.colors(
                        focusedContainerColor = Color.Transparent,
                        unfocusedContainerColor = Color.Transparent,
                        focusedTextColor = Color.Black,
                        unfocusedTextColor = Color.Black
                    ),
                    modifier = Modifier.fillMaxWidth()
                )

                ExposedDropdownMenu(
                    expanded = expandedArea,
                    onDismissRequest = { expandedArea = false }
                ) {
                    areas.forEach { area ->
                        DropdownMenuItem(
                            text = { Text(area) },
                            onClick = {
                                selectedArea = area
                                expandedArea = false
                            }
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            ButtonBar2(title = "Submit", onClick = { navController?.navigate("home") })

        }

        // Imagen img_2 abajo de todo, centrada horizontalmente
        Image(
            painter = painterResource(id = R.drawable.img_2),
            contentDescription = "Image at the bottom",
            modifier = Modifier
                .align(Alignment.BottomCenter)  // Alinear al fondo, en el centro
                .fillMaxWidth()
        )
    }
}

@Preview(showBackground = true)
@Composable
fun FifthScreenPreview() {
    FifthScreen(navController = NavHostController(context = LocalContext.current))
}