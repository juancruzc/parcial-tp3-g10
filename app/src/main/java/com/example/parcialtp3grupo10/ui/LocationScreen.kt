package com.example.parcialtp3grupo10.ui

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.parcialtp3grupo10.ui.components.ButtonBar2
import com.example.parcialtp3grupo10.ui.components.TopLeftImage
import com.example.parcialtp3grupo10.ui.components.TopFullWidthImage
import com.example.parcialtp3grupo10.ui.components.BottomCenteredImage
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import androidx.compose.material3.TextFieldDefaults


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FifthScreen(navController: NavHostController) {
    var expandedPaises by remember { mutableStateOf(false) }
    var expandedProvincias by remember { mutableStateOf(false) }
    var nombresPaises by remember { mutableStateOf(listOf<String>()) }
    var provincias by remember { mutableStateOf(listOf<String>()) }
    var paisSeleccionado by remember { mutableStateOf("") }
    var provinciaSeleccionada by remember { mutableStateOf("") }
    var isLoading by remember { mutableStateOf(true) }

    // Cargar países desde Firebase
    LaunchedEffect(Unit) {
        try {
            val db = Firebase.firestore
            db.collection("Paises")
                .get()
                .addOnSuccessListener { documentos ->
                    nombresPaises = documentos.documents.map { it.id }
                    if (nombresPaises.isNotEmpty()) {
                        paisSeleccionado = nombresPaises[0]
                    }
                    isLoading = false
                    Log.d("Firebase", "Países cargados: $nombresPaises")
                }
                .addOnFailureListener { e ->
                    Log.e("Firebase", "Error al cargar países", e)
                    isLoading = false
                }
        } catch (e: Exception) {
            Log.e("Firebase", "Error", e)
            isLoading = false
        }
    }

    // Cargar provincias cuando se selecciona un país
    LaunchedEffect(paisSeleccionado) {
        if (paisSeleccionado.isNotEmpty()) {
            try {
                val db = Firebase.firestore
                db.collection("Paises")
                    .document(paisSeleccionado)
                    .collection("provincias")
                    .get()
                    .addOnSuccessListener { documentos ->
                        provincias = documentos.documents.map { it.id }
                        if (provincias.isNotEmpty()) {
                            provinciaSeleccionada = provincias[0]
                        }
                        Log.d("Firebase", "Provincias cargadas para $paisSeleccionado: $provincias")
                    }
                    .addOnFailureListener { e ->
                        Log.e("Firebase", "Error al cargar provincias", e)
                    }
            } catch (e: Exception) {
                Log.e("Firebase", "Error", e)
            }
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
    ) {
        TopLeftImage()

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(32.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(20.dp),
        ) {
            TopFullWidthImage()

            Text(
                text = "Select Your Location",
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )

            Text(
                text = "Switch on your location to stay in tune with what's happening in your area.",
                fontSize = 16.sp,
                color = Color.Gray,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(30.dp))

            if (isLoading) {
                CircularProgressIndicator()
            } else {
                // Dropdown de Países
                ExposedDropdownMenuBox(
                    expanded = expandedPaises,
                    onExpandedChange = { expandedPaises = it }
                ) {
                    TextField(
                        value = paisSeleccionado,
                        onValueChange = {},
                        readOnly = true,
                        trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expandedPaises) },
                        modifier = Modifier
                            .menuAnchor()
                            .fillMaxWidth(),
                        colors = TextFieldDefaults.colors(
                            focusedContainerColor = Color.White,
                            unfocusedContainerColor = Color.White,
                            disabledContainerColor = Color.Transparent,
                            focusedIndicatorColor = Color.Gray,
                            unfocusedIndicatorColor = Color.LightGray
                        ),
                        label = { Text("Select your country") }
                    )

                    ExposedDropdownMenu(
                        expanded = expandedPaises,
                        onDismissRequest = { expandedPaises = false }
                    ) {
                        nombresPaises.forEach { pais ->
                            DropdownMenuItem(
                                text = { Text(text = pais) },
                                onClick = {
                                    paisSeleccionado = pais
                                    expandedPaises = false
                                    provinciaSeleccionada = ""
                                    Log.d("Firebase", "País seleccionado: $pais")
                                }
                            )
                        }
                    }
                }

                // Dropdown de Provincias
                if (paisSeleccionado.isNotEmpty()) {
                    ExposedDropdownMenuBox(
                        expanded = expandedProvincias,
                        onExpandedChange = { expandedProvincias = it }
                    ) {
                        TextField(
                            value = provinciaSeleccionada,
                            onValueChange = {},
                            readOnly = true,
                            trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expandedProvincias) },
                            modifier = Modifier
                                .menuAnchor()
                                .fillMaxWidth(),
                            colors = TextFieldDefaults.colors(
                                focusedContainerColor = Color.White,
                                unfocusedContainerColor = Color.White,
                                disabledContainerColor = Color.Transparent,
                                focusedIndicatorColor = Color.Gray,
                                unfocusedIndicatorColor = Color.LightGray
                            ),
                            label = { Text("Select your province") }
                        )

                        ExposedDropdownMenu(
                            expanded = expandedProvincias,
                            onDismissRequest = { expandedProvincias = false }
                        ) {
                            provincias.forEach { provincia ->
                                DropdownMenuItem(
                                    text = { Text(text = provincia) },
                                    onClick = {
                                        provinciaSeleccionada = provincia
                                        expandedProvincias = false
                                        Log.d("Firebase", "Provincia seleccionada: $provincia")
                                    }
                                )
                            }
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            ButtonBar2(
                title = "Submit",
                onClick = {
                    if (paisSeleccionado.isNotEmpty() && provinciaSeleccionada.isNotEmpty()) {
                        navController.navigate("home")
                    }
                }
            )
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