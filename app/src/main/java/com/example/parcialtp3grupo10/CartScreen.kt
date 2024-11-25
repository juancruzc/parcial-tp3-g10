package com.example.parcialtp3grupo10

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.parcialtp3grupo10.ui.components.BottNavigationBar
import com.example.parcialtp3grupo10.ui.components.CartCard
import com.example.parcialtp3grupo10.ui.components.Header
import com.example.parcialtp3grupo10.ui.components.SlideUpPopup
import com.example.parcialtp3grupo10.viewmodel.CartViewModel
import com.example.parcialtp3grupo10.viewmodel.OrderState

@Composable
fun CartScreen(modifier: Modifier = Modifier, navController: NavController? = null) {
    val viewModel: CartViewModel = viewModel()
    val cartState by viewModel.cartState.collectAsState()
    val orderState by viewModel.orderState.collectAsState()
    var showOverlay by remember { mutableStateOf(false) }
    val context = LocalContext.current

    // Efecto para cargar productos de ejemplo
    LaunchedEffect(Unit) {
        viewModel.loadSampleProducts()
    }

    // Observar el estado de la orden
    LaunchedEffect(orderState) {
        when (orderState) {
            is OrderState.Success -> {
                Toast.makeText(context, "Orden completada con éxito", Toast.LENGTH_SHORT).show()
                navController?.navigate("success")
            }
            is OrderState.Error -> {
                Toast.makeText(context, (orderState as OrderState.Error).message, Toast.LENGTH_LONG).show()
            }
            else -> {} // No hacer nada para otros estados
        }
    }

    Scaffold(
        topBar = {
            Header("Mi Carrito")
            HorizontalDivider(color = Color(0xFFB3B3B3), thickness = 1.dp)
        },
        bottomBar = {
            if (navController != null) {
                BottNavigationBar(navController)
            }
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.White),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                LazyColumn(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxWidth(),
                    contentPadding = PaddingValues(16.dp)
                ) {
                    items(cartState.items) { product ->
                        val imagePainter = painterResource(id = product.imageRes)
                        CartCard(
                            productName = product.name,
                            description = product.description,
                            price = product.price,
                            imagePainter = imagePainter,
                            onRemove = { viewModel.removeFromCart(product) }
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                    }
                }

                CheckoutButton(
                    totalPrice = cartState.total.toString(),
                    onClick = {
                        if (cartState.items.isNotEmpty()) {
                            showOverlay = true
                        } else {
                            Toast.makeText(context, "El carrito está vacío", Toast.LENGTH_SHORT).show()
                        }
                    }
                )
            }

            // Overlay para el checkout
            if (showOverlay) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color(0x80000000))
                        .zIndex(1f)
                ) {
                    if (navController != null) {
                        SlideUpPopup(
                            isVisible = showOverlay,
                            onClose = { showOverlay = false },
                            total = cartState.total,
                            modifier = Modifier
                                .align(Alignment.BottomCenter)
                                .fillMaxWidth()
                                .wrapContentHeight()
                                .padding(bottom = 16.dp),
                            navController = navController,
                            onCheckout = {
                                viewModel.checkout()
                                showOverlay = false
                            }
                        )
                    }
                }
            }

            // Loading indicator
            if (orderState is OrderState.Loading) {
                CircularProgressIndicator(
                    modifier = Modifier
                        .align(Alignment.Center)
                        .zIndex(2f)
                )
            }
        }
    }
}

@Composable
fun CheckoutButton(totalPrice: String, onClick: () -> Unit) {
    Button(
        onClick = onClick,
        modifier = Modifier
            .fillMaxWidth()
            .height(95.dp)
            .padding(16.dp),
        shape = MaterialTheme.shapes.medium,
        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF53B175))
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
                    text = "Ir al Checkout",
                    color = Color.White,
                    style = MaterialTheme.typography.bodyLarge
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = "$$totalPrice",
                    color = Color.White,
                    style = MaterialTheme.typography.bodyLarge
                )
            }
        }
    }
}