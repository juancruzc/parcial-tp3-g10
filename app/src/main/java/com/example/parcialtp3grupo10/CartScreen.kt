package com.example.parcialtp3grupo10

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import com.example.parcialtp3grupo10.model.Product
import com.example.parcialtp3grupo10.ui.components.CartCard
import com.example.parcialtp3grupo10.ui.components.Header

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CartScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        TopAppBar(
            modifier = Modifier.zIndex(1f),
            title = {
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    Header("My Cart")
                }
            },
        )
        Spacer(modifier = Modifier.size(5.dp))

        HorizontalDivider(color = Color(0xFFB3B3B3), thickness = 1.dp)

        val productsOne = listOf(
            Product("Organic Bananas", "7pcs, Price", 4.99, R.drawable.banana),
            Product("Red Apple", "1kg, Price", 4.99, R.drawable.apple),
        )

        LazyColumn(
            modifier = Modifier.fillMaxWidth(),
            contentPadding = PaddingValues(16.dp)
        ) {
            items(productsOne) { product ->
                val imagePainter = painterResource(id = product.imageRes)
                CartCard(
                    productName = product.name,
                    description = product.description,
                    price = product.price,
                    imagePainter
                )
            }
        }

        val total = productsOne.sumOf { it.price }
        CheckoutButton(total.toString(), {})

    }
}

@Composable
fun CheckoutButton(totalPrice: String, onClick: () -> Unit) {
    Button(
        onClick = {},
        modifier = Modifier
            .fillMaxWidth()
            .height(95.dp)
            .padding(16.dp),
        shape = RoundedCornerShape(12.dp),
        colors = ButtonDefaults.buttonColors(Color(0xFF53B175)),
    ) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Go to Checkout",
                    color = Color.White,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(end = 24.dp)
                )
            }

            Box(
                modifier = Modifier
                    .align(Alignment.CenterEnd)
                    .background(Color(0xFF37915F), RoundedCornerShape(12.dp))
                    .padding(horizontal = 8.dp, vertical = 4.dp)
            ) {
                Text(
                    text = "$$totalPrice",
                    color = Color.White,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CartScreenPreview() {
    MaterialTheme {
        CartScreen()
    }
}