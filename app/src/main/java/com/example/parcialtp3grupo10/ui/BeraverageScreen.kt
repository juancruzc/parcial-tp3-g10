package com.example.parcialtp3grupo10.ui

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.parcialtp3grupo10.model.Beverage
import com.example.parcialtp3grupo10.ui.theme.ParcialTP3Grupo10Theme

@Composable
fun BeverageScreen(modifier: Modifier = Modifier, viewModel: BeverageViewModel = viewModel()) {
    val beverages by viewModel.beverages.observeAsState(emptyList())

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(bottom = 16.dp)
        ) {
            IconButton(onClick = { /* Acción del botón */ }) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = "Back"
                )
            }
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = "Beverages",
                style = MaterialTheme.typography.headlineMedium
            )
        }

        LazyVerticalGrid(
            columns = GridCells.Fixed(2), // Fija el número de columnas
            contentPadding = PaddingValues(8.dp),
            horizontalArrangement = Arrangement.spacedBy(4.dp),
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            items(beverages) { beverage ->
                BeverageItem(beverage)
            }
        }
    }
}

@Composable
fun BeverageItem(beverage: Beverage) {
    Card(
        modifier = Modifier
            .fillMaxWidth(0.45f)  // Mantener el 45% del ancho
            .padding(8.dp)
            .height(220.dp),  // Fijar altura mínima para todas las tarjetas
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        shape = RoundedCornerShape(16.dp),
        border = BorderStroke(1.dp, Color(0xFFE2E2E2)),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Column(
            modifier = Modifier
                .padding(10.dp)
                .fillMaxSize(),
            verticalArrangement = Arrangement.SpaceBetween,  // Asegurar que los elementos estén bien distribuidos
            horizontalAlignment = Alignment.Start
        ) {
            // Imagen de la bebida
            Image(
                painter = painterResource(id = beverage.imageRes),
                contentDescription = beverage.name,
                contentScale = ContentScale.Fit,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(110.dp)  // Tamaño uniforme de la imagen para todas las bebidas
            )

            // Nombre de la bebida
            Text(
                text = beverage.name,
                style = MaterialTheme.typography.titleMedium.copy(
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp,
                    letterSpacing = 0.01.em,
                    color = Color(0xFF181725)
                ),
                textAlign = TextAlign.Left,
                maxLines = 1,  // Limitar a 1 línea
                overflow = TextOverflow.Ellipsis  // Asegurarse de que no haya desbordamiento
            )

            // Información del volumen
            Text(
                text = "${beverage.volume}, Price",
                style = MaterialTheme.typography.bodyMedium.copy(
                    color = Color.Gray,
                    fontSize = 14.sp
                ),
                textAlign = TextAlign.Left
            )

            // Fila para precio y botón "+"
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween // Asegurar alineación
            ) {
                // Precio
                Text(
                    text = "$${beverage.price}",
                    style = MaterialTheme.typography.titleLarge.copy(
                        fontWeight = FontWeight.Bold,
                        fontSize = 18.sp,
                        letterSpacing = 0.01.em,
                        color = Color(0xFF181725)
                    )
                )

                // Botón "+"
                IconButton(
                    onClick = { /* Acción del botón */ },
                    modifier = Modifier
                        .size(42.dp) // Tamaño uniforme del botón
                        .background(Color(0xFF53B175), shape = CircleShape)
                ) {
                    Icon(
                        imageVector = Icons.Default.Add,
                        contentDescription = "Add",
                        tint = Color.White
                    )
                }
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun BeverageScreenPreview() {
    ParcialTP3Grupo10Theme {
        BeverageScreen() // Aquí no necesitas pasar el modificador
    }
}
