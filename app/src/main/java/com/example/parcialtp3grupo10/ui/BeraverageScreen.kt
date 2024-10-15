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
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.parcialtp3grupo10.R
import com.example.parcialtp3grupo10.model.Beverage
import com.example.parcialtp3grupo10.model.Product
import com.example.parcialtp3grupo10.ui.components.ProductCard
import com.example.parcialtp3grupo10.ui.theme.ParcialTP3Grupo10Theme

@Composable
fun BeverageScreen(
    navController: NavController,  // Agregar el NavController
    modifier: Modifier = Modifier,
    viewModel: BeverageViewModel = viewModel()
) {
    val beverages by viewModel.beverages.observeAsState(emptyList())

    val productsTwo = listOf(
        Product("Coca Cola", "325ml", 4.99, R.drawable.coca_cola_can),
        Product("Diet Coke", "325ml", 1.50, R.drawable.diet_coke),
        Product("Pepsi", "330ml", 4.99, R.drawable.pepsi_can),
        Product("Sprite", "325ml", 1.50, R.drawable.sprite_can),
        Product("Apple Grape Juice", "2L", 15.99, R.drawable.apple_grape_juice),
        Product("Orange Juice", "250g", 4.99, R.drawable.orange_juice),
        )
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(bottom = 16.dp)
        ) {
            IconButton(onClick = { navController.navigateUp() }) { // Usar navigateUp para volver
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
            columns = GridCells.Fixed(2),
            contentPadding = PaddingValues(8.dp),
            horizontalArrangement = Arrangement.spacedBy(4.dp),
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            items(productsTwo) { beverage ->
                BeverageItem(beverage)
            }
        }
    }
}

@Composable
fun BeverageItem(product: Product) {
    val imagePainter = painterResource(id = product.imageRes)
    ProductCard(
        productName = product.name,
        description = product.description,
        price = product.price,
        imagePainter,
        onAddClick = { /* Handle add */ }
    )
}


@Preview(showBackground = true)
@Composable
fun BeverageScreenPreview() {
    val navController = rememberNavController()  // Simulamos el NavController en el Preview
    ParcialTP3Grupo10Theme {
        BeverageScreen(navController = navController)  // Proporcionamos el NavController
    }
}
