package com.example.parcialtp3grupo10

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.parcialtp3grupo10.model.Product
import com.example.parcialtp3grupo10.ui.components.BottNavigationBar
import com.example.parcialtp3grupo10.ui.components.Header
import com.example.parcialtp3grupo10.ui.components.ProductCard

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun HomeScreen(navController: NavController? = null) {
    Scaffold (
        bottomBar = {
            if (navController != null) {
                BottNavigationBar(navController)
            }
        }
    ) { innerPadding ->
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(innerPadding),
    ) {
        TopAppBar(
            title = {
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    Header("Shop")

                }
            },
        )
        Text(
            text = "Buenos Aires",
            style = MaterialTheme.typography.bodyMedium,
            color = Color(0xFF4C4F4D),
            fontSize = 18.sp,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth(),
            contentPadding = PaddingValues(16.dp)
        ) {
            item {
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(120.dp)
                        .padding(bottom = 16.dp),
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(Color(0xFFF5F5F5))
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.banner),
                            contentDescription = "Product Image",
                            modifier = Modifier
                                .fillMaxWidth()
                                .fillMaxSize(),
                            contentScale = ContentScale.Crop,
                            alpha = 1f
                        )
                    }
                }
            }

            item {
                SectionHeader(
                    title = "Exclusive Offer",
                    onSeeAllClick = { /* Handle see all */ }
                )
            }
            val productsOne = listOf(
                Product("Organic Bananas", "7pcs, Priceg", 4.99, R.drawable.banana),
                Product("Red Apple", "1kg, Priceg", 4.99, R.drawable.apple),
                Product(
                    "Ginger",
                    "250g, Priceg",
                    4.99,
                    R.drawable.ginger
                )
            )
            item {
                LazyRow(
                    horizontalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    items(productsOne) { product ->
                        val imagePainter = painterResource(id = product.imageRes)
                        ProductCard(
                            productName = product.name,
                            description = product.description,
                            price = product.price,
                            imagePainter,
                            onAddClick = { }
                        )
                    }
                }
            }
            item {
                SectionHeader(
                    title = "Best Selling",
                    onSeeAllClick = {  }
                )
            }

            val productsTwo = listOf(
                Product("Bell Pepper Red", "1kg, Priceg", 4.99, R.drawable.pepper),
                Product("Ginger", "250g, Priceg", 4.99, R.drawable.ginger),
                Product(
                    "Ginger",
                    "250g, Priceg",
                    4.99,
                    R.drawable.ginger
                )
            )
            item {
                LazyRow(
                    horizontalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    items(productsTwo) { product ->
                        val imagePainter = painterResource(id = product.imageRes)
                        ProductCard(
                            productName = product.name,
                            description = product.description,
                            price = product.price,
                            imagePainter,
                            onAddClick = { /* Handle add */ }
                        )
                    }
                }
            }
        }
    }
    }
}

@Composable
private fun SectionHeader(
    title: String,
    onSeeAllClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 16.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold
        )
        TextButton(onClick = onSeeAllClick) {
            Text(
                text = "See all",
                color = Color(0xFF4CAF50)
            )
        }
    }
}

@Preview
@Composable
fun PreviewHomeScreen() {
    MaterialTheme {
        HomeScreen()
    }
}
