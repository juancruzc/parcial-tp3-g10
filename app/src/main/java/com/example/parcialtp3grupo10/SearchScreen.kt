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
import androidx.compose.material.icons.filled.Brightness2
import androidx.compose.material.icons.filled.Brightness7
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
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
fun SearchScreen(navController: NavController?, toggleDarkMode: () -> Unit, isDarkMode: Boolean) {
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

    val currentRoute = navController?.currentBackStackEntry?.destination?.route ?: "search"

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
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary
                ),
                actions = {
                    IconButton(onClick = toggleDarkMode) {
                        Icon(
                            imageVector = if (isDarkMode) Icons.Default.Brightness7 else Icons.Default.Brightness2,
                            contentDescription = "Toggle Dark Mode",
                            tint = MaterialTheme.colorScheme.onPrimary
                        )
                    }
                }
            )
        },
        bottomBar = {
            if (navController != null) {
                BottNavigationBar(navController, isDarkMode, currentRoute)
            }
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
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
    Product("Egg Chicken Red", "6pcs, Price", 1.99, R.drawable.egg_chicken_red,1),
    Product("Egg Chicken White", "180g, Price", 1.50, R.drawable.egg_chicken_white,1),
    Product("Egg Pasta", "30gm, Price", 15.99, R.drawable.egg_pasta,1),
    Product("Egg Noodles", "2x, Price", 15.99, R.drawable.egg_noodles,1),
    Product("Egg Noodles", "2x, Price", 15.99, R.drawable.egg_noodles_2,1),
    Product("Egg Mayonnaise", "2x, Price", 15.99, R.drawable.mayonaisse,1),
    Product("Diet Coke", "355ml", 1.99, R.drawable.diet_coke,1),
    Product("Sprite Can", "325ml", 1.50, R.drawable.sprite_can,1),
    Product("Apple & Grape Juice", "2L", 15.99, R.drawable.apple_grape_juice,1),
    Product("Orange Juice", "2L", 15.99, R.drawable.orange_juice,1),
    Product("Coca Cola Can", "325ml", 4.99, R.drawable.coca_cola_can,1),
    Product("Pepsi Can", "330ml", 4.99, R.drawable.pepsi_can,1),
    Product("Organic Bananas", "7pcs, Price", 4.99, R.drawable.banana,1),
    Product("Red Apple", "1kg, Price", 4.99, R.drawable.apple,1),
    Product("Ginger", "250g, Price", 4.99, R.drawable.ginger,1),
    Product("Bell Pepper Red", "1kg, Price", 4.99, R.drawable.pepper,1)
)
