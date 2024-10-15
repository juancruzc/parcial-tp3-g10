package com.example.parcialtp3grupo10

import android.annotation.SuppressLint
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
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
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import androidx.navigation.NavController
import com.example.parcialtp3grupo10.model.Product
import com.example.parcialtp3grupo10.ui.components.BottNavigationBar
import com.example.parcialtp3grupo10.ui.components.CartCard
import com.example.parcialtp3grupo10.ui.components.CheckoutCard
import com.example.parcialtp3grupo10.ui.components.Header

@Composable
fun CartScreen(modifier: Modifier, navController: NavController? = null) {

    var showOverlay by remember { mutableStateOf(false) }
    var total by remember { mutableDoubleStateOf(0.0) }

    Scaffold(
        topBar = {
            Header("My cart")
            HorizontalDivider(color = Color(0xFFB3B3B3), thickness = 1.dp)
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
                .padding(innerPadding),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
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

            total = productsOne.sumOf { it.price }
            CheckoutButton(total.toString(), onClick = { showOverlay = true })
        }

        if (showOverlay) {
            Box(
                modifier = Modifier
                    .background(Color(0x80000000))
                    .zIndex(1f)
            ) {
                if (navController != null) {
                    SlideUpPopup(
                        isVisible = showOverlay,
                        onClose = { showOverlay = false },
                        total = total,
                        modifier = modifier
                            .align(Alignment.BottomCenter)
                            .fillMaxWidth()
                            .wrapContentHeight()
                            .padding(bottom = 16.dp),
                        navController = navController
                    )
                }
            }
        }
    }
}

@SuppressLint("UseOfNonLambdaOffsetOverload")
@Composable
fun SlideUpPopup(
    isVisible: Boolean,
    total: Double,
    onClose: () -> Unit,
    modifier: Modifier = Modifier,
    navController: NavController
) {
    AnimatedVisibility(
        visible = isVisible,
        enter = slideInVertically(
            initialOffsetY = { it },
            animationSpec = tween(durationMillis = 300)
        ),
        exit = slideOutVertically(
            targetOffsetY = { it },
            animationSpec = tween(durationMillis = 300)
        )
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color(0x80000000))
            )
            Box(
                modifier = modifier
                    .align(Alignment.BottomCenter)
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .background(Color.White, RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp))
                    .zIndex(2f)
            ) {
                CheckoutCard(total, onClose, navController)
            }
        }
    }
}

@Composable
fun CheckoutButton(totalPrice: String, onClick: () -> Unit) {
    Button(
        onClick = onClick ,
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
        CartScreen(modifier = Modifier)
    }
}