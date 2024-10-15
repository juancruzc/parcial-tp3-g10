package com.example.parcialtp3grupo10

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.runtime.setValue
import androidx.navigation.NavController
import com.example.parcialtp3grupo10.model.Product
import com.example.parcialtp3grupo10.ui.components.BottNavigationBar
import com.example.parcialtp3grupo10.ui.components.Header
import com.example.parcialtp3grupo10.ui.components.ProductCard

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchScreen(navController: NavController? = null) {
    var searchQuery by remember { mutableStateOf("") }
    val filteredProducts = remember(searchQuery) {
        if (searchQuery.isEmpty()) {
            sampleProducts
        } else {
            sampleProducts.filter { product ->
                product.name.contains(searchQuery, ignoreCase = true) ||
                        product.description.contains(searchQuery, ignoreCase = true)
            }
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Column(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                    ) {
                        Header("Search")
                    }
                },
            )
        },
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
                .padding(innerPadding)
        ) {
            SearchBar(
                value = searchQuery,
                onValueChange = { searchQuery = it }
            )
            ProductGrid(
                products = filteredProducts,
                modifier = Modifier.weight(1f)
            )
        }
    }
}

@Composable
fun ProductGrid(products: List<Product>, modifier: Modifier = Modifier) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        contentPadding = PaddingValues(16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        modifier = modifier
    ) {
        items(products) { product ->
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

@Composable
fun SearchBar(value: String, onValueChange: (String) -> Unit) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        placeholder = { Text("Search") },
        leadingIcon = { Icon(Icons.Default.Search, contentDescription = null) },
        shape = RoundedCornerShape(8.dp)
    )
}

val sampleProducts = listOf(
    Product("Egg Chicken Red", "6pcs, Price", 1.99, R.drawable.egg_chicken_red),
    Product("Egg Chicken White", "180g, Price", 1.50, R.drawable.egg_chicken_white),
    Product("Egg Pasta", "30gm, Price", 15.99, R.drawable.egg_pasta),
    Product("Egg Noodles", "2x, Price", 15.99, R.drawable.egg_noodles),
    Product("Egg Noodles", "2x, Price", 15.99, R.drawable.egg_noodles_2),
    Product("Egg Mayonnaise", "2x, Price", 15.99, R.drawable.mayonaisse),
    Product("Diet Coke", "355ml", 1.99, R.drawable.diet_coke),
    Product("Sprite Can", "325ml", 1.50, R.drawable.sprite_can),
    Product("Apple & Grape Juice", "2L", 15.99, R.drawable.apple_grape_juice),
    Product("Orange Juice", "2L", 15.99, R.drawable.orange_juice),
    Product("Coca Cola Can", "325ml", 4.99, R.drawable.coca_cola_can),
    Product("Pepsi Can", "330ml", 4.99, R.drawable.pepsi_can),
    Product("Organic Bananas", "7pcs, Price", 4.99, R.drawable.banana),
    Product("Red Apple", "1kg, Price", 4.99, R.drawable.apple),
    Product("Ginger", "250g, Price", 4.99, R.drawable.ginger),
    Product("Bell Pepper Red", "1kg, Price", 4.99, R.drawable.pepper)
)
