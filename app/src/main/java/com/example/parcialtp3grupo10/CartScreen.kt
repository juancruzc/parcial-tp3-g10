package com.example.parcialtp3grupo10

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Brightness2
import androidx.compose.material.icons.filled.Brightness7
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
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
import com.example.parcialtp3grupo10.ui.components.CheckoutCard
import com.example.parcialtp3grupo10.viewmodel.CartViewModel
import com.example.parcialtp3grupo10.viewmodel.OrderState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CartScreen(
    navController: NavController?,
    toggleDarkMode: () -> Unit,
    isDarkMode: Boolean,
    modifier: Modifier = Modifier
) {
    val viewModel: CartViewModel = viewModel()
    val cartState by viewModel.cartState.collectAsState()
    val orderState by viewModel.orderState.collectAsState()
    var showOverlay by remember { mutableStateOf(false) }
    val context = LocalContext.current

    val currentRoute = navController?.currentBackStackEntry?.destination?.route ?: "cart"

    LaunchedEffect(Unit) {
        viewModel.loadSampleProducts(context)
    }

    LaunchedEffect(orderState) {
        when (orderState) {
            is OrderState.Success -> {
                Toast.makeText(context, "Orden completada con éxito", Toast.LENGTH_SHORT).show()
                navController?.navigate("success")
            }
            is OrderState.Error -> {
                Toast.makeText(context, (orderState as OrderState.Error).message, Toast.LENGTH_LONG).show()
            }
            else -> {}
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Box(
                        modifier = Modifier.fillMaxWidth(),
                        contentAlignment = Alignment.Center
                    ) {
                        Text("Mi Carrito", color = MaterialTheme.colorScheme.onSurface)
                    }
                },
                navigationIcon = {
                    IconButton(onClick = { /* Accion aquí si es necesario */ }) {
                        Icon(
                            imageVector = Icons.Default.Menu,
                            contentDescription = "Menu",
                            tint = MaterialTheme.colorScheme.onSurface
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.surface
                ),
                actions = {
                    IconButton(
                        onClick = toggleDarkMode,
                        modifier = Modifier
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
                }
            )
        },
        bottomBar = {
            if (navController != null) {
                BottNavigationBar(navController, isDarkMode, currentRoute)
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
                    .background(MaterialTheme.colorScheme.background),
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
                            onRemove = { viewModel.removeFromCart(product) },
                            onIncrease = { viewModel.addItemToCart(product) },
                            onDecrease = { viewModel.removeItemQuantity(product) }
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
