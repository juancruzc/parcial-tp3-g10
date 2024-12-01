package com.example.parcialtp3grupo10.ui

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
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
import androidx.compose.foundation.verticalScroll
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Brightness2
import androidx.compose.material.icons.filled.Brightness7
import androidx.compose.ui.draw.shadow


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FifthScreen(navController: NavHostController, isDarkMode: Boolean, toggleDarkMode: () -> Unit) {
    var expandedPaises by remember { mutableStateOf(false) }
    var expandedProvincias by remember { mutableStateOf(false) }
    var nombresPaises by remember { mutableStateOf(listOf<String>()) }
    var provincias by remember { mutableStateOf(listOf<String>()) }
    var paisSeleccionado by remember { mutableStateOf("") }
    var provinciaSeleccionada by remember { mutableStateOf("") }
    var isLoading by remember { mutableStateOf(true) }

    LaunchedEffect(Unit) {
        val db = Firebase.firestore
        db.collection("Paises")
            .get()
            .addOnSuccessListener { documentos ->
                nombresPaises = documentos.documents.map { it.id }
                if (nombresPaises.isNotEmpty()) {
                    paisSeleccionado = nombresPaises[0]
                }
                isLoading = false
            }
    }

    LaunchedEffect(paisSeleccionado) {
        if (paisSeleccionado.isNotEmpty()) {
            val db = Firebase.firestore
            db.collection("Paises").document(paisSeleccionado).collection("provincias")
                .get()
                .addOnSuccessListener { documentos ->
                    provincias = documentos.documents.map { it.id }
                    if (provincias.isNotEmpty()) {
                        provinciaSeleccionada = provincias[0]
                    }
                }
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        TopLeftImage()

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 32.dp)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(90.dp))

            IconButton(
                onClick = toggleDarkMode,
                modifier = Modifier
                    .align(Alignment.End)
                    .shadow(
                        elevation = 8.dp,
                        shape = CircleShape,
                        ambientColor = if (isDarkMode) Color.White else Color.Black,
                        spotColor = if (isDarkMode) Color.White else Color.Black
                    )
                    .background(MaterialTheme.colorScheme.surface, shape = CircleShape)
            ) {
                Icon(
                    imageVector = if (isDarkMode) Icons.Default.Brightness7 else Icons.Default.Brightness2,
                    contentDescription = "Toggle Dark Mode",
                    tint = MaterialTheme.colorScheme.onSurface
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            TopFullWidthImage(
                modifier = Modifier
                    .height(250.dp)
                    .fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "Select Your Location",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onBackground
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "Switch on your location to stay in tune with what's happening in your area.",
                fontSize = 14.sp,
                color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.7f),
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(24.dp))

            if (isLoading) {
                CircularProgressIndicator(color = MaterialTheme.colorScheme.primary)
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
                            focusedContainerColor = MaterialTheme.colorScheme.surface,
                            unfocusedContainerColor = MaterialTheme.colorScheme.surface,
                            focusedIndicatorColor = MaterialTheme.colorScheme.primary,
                            unfocusedIndicatorColor = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.5f)
                        ),
                        label = { Text("Select your country", color = MaterialTheme.colorScheme.onSurface) }
                    )

                    ExposedDropdownMenu(
                        expanded = expandedPaises,
                        onDismissRequest = { expandedPaises = false }
                    ) {
                        nombresPaises.forEach { pais ->
                            DropdownMenuItem(
                                text = { Text(text = pais, color = MaterialTheme.colorScheme.onSurface) },
                                onClick = {
                                    paisSeleccionado = pais
                                    expandedPaises = false
                                    provinciaSeleccionada = ""
                                }
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

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
                                focusedContainerColor = MaterialTheme.colorScheme.surface,
                                unfocusedContainerColor = MaterialTheme.colorScheme.surface,
                                focusedIndicatorColor = MaterialTheme.colorScheme.primary,
                                unfocusedIndicatorColor = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.5f)
                            ),
                            label = { Text("Select your province", color = MaterialTheme.colorScheme.onSurface) }
                        )

                        ExposedDropdownMenu(
                            expanded = expandedProvincias,
                            onDismissRequest = { expandedProvincias = false }
                        ) {
                            provincias.forEach { provincia ->
                                DropdownMenuItem(
                                    text = { Text(text = provincia, color = MaterialTheme.colorScheme.onSurface) },
                                    onClick = {
                                        provinciaSeleccionada = provincia
                                        expandedProvincias = false
                                    }
                                )
                            }
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            Box(
                modifier = Modifier.fillMaxWidth()
            ) {
                ButtonBar2(
                    title = "Submit",
                    onClick = {
                        if (paisSeleccionado.isNotEmpty() && provinciaSeleccionada.isNotEmpty()) {
                            navController.navigate("home")
                        }
                    }
                )
            }
        }

        BottomCenteredImage(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .height(16.dp)
                .fillMaxWidth(0.2f)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun FifthScreenPreview() {
    FifthScreen(
        navController = NavHostController(context = LocalContext.current),
        isDarkMode = false,
        toggleDarkMode = {}
    )
}
