package com.example.parcialtp3grupo10.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModel
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Remove
import androidx.compose.material.icons.filled.Share
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.ui.draw.clip
import com.example.parcialtp3grupo10.R


// ViewModel to manage the product details
class ProductViewModel : ViewModel() {
    var quantity by mutableIntStateOf(1)
        private set

    fun increaseQuantity() {
        quantity++
    }

    fun decreaseQuantity() {
        if (quantity > 1) quantity--
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductDetailScreen(viewModel: ProductViewModel = ProductViewModel()) {
    val productTitle = "Naturel Red Apple"
    val price = "$4.99"
    val productDescription = "Apples are nutritious. Apples may be good for weight loss. Apples may be good for your heart. As part of a healthful and varied diet."

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Product Detail") },
                navigationIcon = {
                    IconButton(onClick = { /* Handle back navigation */ }) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                    }
                },
                actions = {
                    IconButton(onClick = { /* Share action */ }) {
                        Icon(Icons.Default.Share, contentDescription = "Share")
                    }
                }
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(16.dp)
                .padding(bottom = 16.dp) // Añadir padding inferior para el home indicator
        ) {
            // Imagen del producto
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(250.dp)
                    .clip(RoundedCornerShape(16.dp))
                    .background(Color.LightGray)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.apple), // Reemplaza con tu recurso de imagen
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize()
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Título del producto
            Text(
                text = productTitle,
                fontSize = 26.sp,
                fontWeight = FontWeight.SemiBold, // Negrita para el título
                color = Color.Black,
                modifier = Modifier.padding(bottom = 8.dp)
            )

            // Peso y precio
            Text(
                text = "1kg, Price",
                fontSize = 16.sp,
                color = Color.Gray,
                modifier = Modifier.padding(bottom = 4.dp)
            )

            Spacer(modifier = Modifier.height(8.dp))

            // Selector de cantidad
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                IconButton(
                    onClick = { viewModel.decreaseQuantity() },
                    modifier = Modifier
                        .border(1.dp, Color.Gray, RoundedCornerShape(4.dp))
                        .size(40.dp)
                ) {
                    Icon(Icons.Default.Remove, contentDescription = "Decrease quantity")
                }

                Text(
                    text = viewModel.quantity.toString(),
                    fontSize = 18.sp,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.width(40.dp)
                )

                IconButton(
                    onClick = { viewModel.increaseQuantity() },
                    modifier = Modifier
                        .border(1.dp, Color.Gray, RoundedCornerShape(4.dp))
                        .size(40.dp)
                ) {
                    Icon(Icons.Default.Add, contentDescription = "Increase quantity")
                }

                Spacer(modifier = Modifier.weight(1f))

                Text(
                    text = price,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.primary
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Sección de detalles del producto
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = "Product Detail",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.weight(1f)
                )
                Icon(
                    painter = painterResource(id = R.drawable.vector), // Flecha
                    contentDescription = "Arrow",
                    tint = Color.Gray,
                    modifier = Modifier.size(20.dp)
                )
            }

            Text(
                text = productDescription,
                fontSize = 14.sp,
                color = Color.Gray,
                modifier = Modifier.padding(vertical = 8.dp)
            )

            HorizontalDivider(
                modifier = Modifier.padding(vertical = 8.dp),
                thickness = 1.dp,
                color = Color.LightGray
            )

            // Sección de nutrición
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = "Nutritions",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.weight(1f)
                )
                Box(
                    modifier = Modifier
                        .background(Color.LightGray, shape = RoundedCornerShape(4.dp))
                        .padding(horizontal = 8.dp, vertical = 4.dp)
                ) {
                    Text(
                        text = "100gr",
                        fontSize = 14.sp,
                        color = Color.Gray
                    )
                }
                Spacer(modifier = Modifier.width(8.dp)) // Añade espacio entre "100gr" y la flecha
                Icon(
                    painter = painterResource(id = R.drawable.vector), // Flecha
                    contentDescription = "Arrow",
                    tint = Color.Gray,
                    modifier = Modifier.size(20.dp)
                )
            }

            // Divider inferior
            HorizontalDivider(
                modifier = Modifier.padding(vertical = 8.dp),
                thickness = 1.dp,
                color = Color.LightGray
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Sección de reviews
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Review",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.weight(1f)
                )

                Row {
                    repeat(5) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_start), // Estrella
                            contentDescription = "Star",
                            tint = Color(0xFFF2994A),
                            modifier = Modifier.size(20.dp)
                        )
                    }
                }
                Spacer(modifier = Modifier.width(8.dp))
                Icon(
                    painter = painterResource(id = R.drawable.vector), // Flecha
                    contentDescription = "Arrow",
                    tint = Color.Gray,
                    modifier = Modifier.size(20.dp)
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Botón "Add To Basket"
            Button(
                onClick = { /* Add to basket */ },
                shape = RoundedCornerShape(20.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF6FCF97)),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(55.dp)  // Ajustar la altura del botón
            ) {
                Text(
                    text = "Add To Basket",
                    color = Color.White,
                    fontSize = 16.sp
                )
            }

            // Home Indicator
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(6.dp)
                    .padding(top = 8.dp) // Espaciado entre el botón y el indicador
                    .background(Color.Gray, shape = RoundedCornerShape(3.dp))
                    .align(Alignment.CenterHorizontally)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewProductDetailScreen() {
    ProductDetailScreen()
}
