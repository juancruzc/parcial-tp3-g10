package com.example.parcialtp3grupo10.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.parcialtp3grupo10.ui.components.ButtonBar2
import com.example.parcialtp3grupo10.ui.components.DropdownMenuComponent
import com.example.parcialtp3grupo10.ui.components.TopLeftImage
import com.example.parcialtp3grupo10.ui.components.TopFullWidthImage
import com.example.parcialtp3grupo10.ui.components.BottomCenteredImage


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FifthScreen(navController: NavHostController) {
    var selectedLocation by remember { mutableStateOf("Buenos Aires") }
    var selectedArea by remember { mutableStateOf("Area 1") }

    val locations = listOf("Buenos Aires", "Mendoza")
    val areas = listOf("Area 1", "Area 2")

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
    ) {
        TopLeftImage() // Imagen modular arriba a la izquierda

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(32.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(20.dp),
        ) {
            TopFullWidthImage() // Imagen modular arriba del título

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

            // Componente desplegable para seleccionar la ubicación
            DropdownMenuComponent(
                label = "Your Zone",
                selectedOption = selectedLocation,
                options = locations
            ) { location ->
                selectedLocation = location
            }

            // Componente desplegable para seleccionar el área
            DropdownMenuComponent(
                label = "Your Area",
                selectedOption = selectedArea,
                options = areas
            ) { area ->
                selectedArea = area
            }

            Spacer(modifier = Modifier.height(16.dp))

            ButtonBar2(title = "Submit", onClick = { navController.navigate("home") })
        }


        BottomCenteredImage(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .height(50.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun FifthScreenPreview() {
    FifthScreen(navController = NavHostController(context = LocalContext.current))
}
