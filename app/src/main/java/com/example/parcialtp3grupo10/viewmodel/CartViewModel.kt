package com.example.parcialtp3grupo10.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.parcialtp3grupo10.R
import com.example.parcialtp3grupo10.model.Order
import com.example.parcialtp3grupo10.model.OrderItem
import com.example.parcialtp3grupo10.model.Product
import com.google.firebase.FirebaseApp
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

data class CartState(
    val items: List<Product> = emptyList(),
    val total: Double = 0.0,
    val isLoading: Boolean = false
)

sealed class OrderState {
    object Initial : OrderState()
    object Loading : OrderState()
    data class Success(val orderId: String) : OrderState()
    data class Error(val message: String): OrderState()
}

class CartViewModel : ViewModel() {
    private val db by lazy {
        try {
            FirebaseFirestore.getInstance().also {
                Log.d("Firebase", "Firestore initialized successfully")
            }
        } catch (e: Exception) {
            Log.e("Firebase", "Error initializing Firestore: ${e.message}")
            null
        }
    }

    private val ordersCollection by lazy { db?.collection("orders") }

    private val _orderState = MutableStateFlow<OrderState>(OrderState.Initial)
    val orderState: StateFlow<OrderState> = _orderState.asStateFlow()

    private val _cartState = MutableStateFlow(CartState())
    val cartState: StateFlow<CartState> = _cartState.asStateFlow()

    fun addToCart(product: Product) {
        viewModelScope.launch {
            val currentItems = _cartState.value.items.toMutableList()
            currentItems.add(product)
            updateCartState(currentItems)
        }
    }

    fun removeFromCart(product: Product) {
        viewModelScope.launch {
            val currentItems = _cartState.value.items.toMutableList()
            currentItems.remove(product)
            updateCartState(currentItems)
        }
    }

    private fun updateCartState(items: List<Product>) {
        val total = calculateTotal(items)
        _cartState.value = CartState(
            items = items,
            total = total
        )
    }

    private fun calculateTotal(items: List<Product>): Double {
        return items.sumOf { it.price }
    }

    fun checkout() {
        val items = _cartState.value.items.map { product ->
            OrderItem(
                productName = product.name,
                quantity = 1,
                price = product.price
            )
        }

        saveOrder(items, _cartState.value.total)
    }

    fun saveOrder(items: List<OrderItem>, totalAmount: Double) {
        viewModelScope.launch {
            try {
                _orderState.value = OrderState.Loading

                if (db == null || ordersCollection == null) {
                    _orderState.value = OrderState.Error("Firebase no está inicializado")
                    return@launch
                }

                val order = Order(
                    items = items,
                    totalAmount = totalAmount
                )

                ordersCollection?.add(order)
                    ?.addOnSuccessListener { documentReference ->
                        val orderId = documentReference.id
                        _orderState.value = OrderState.Success(orderId)
                        _cartState.value = CartState()
                    }
                    ?.addOnFailureListener { e ->
                        _orderState.value = OrderState.Error(
                            e.message ?: "Error al guardar la orden"
                        )
                    }
            } catch (exception: Exception) {
                _orderState.value = OrderState.Error(
                    exception.message ?: "Error inesperado"
                )
                Log.e("Firebase", "Error en saveOrder: ${exception.message}")
            }
        }
    }

    fun loadSampleProducts() {
        val sampleProducts = listOf(
            Product(
                name = "Organic Bananas",
                description = "7pcs, Price",
                price = 4.99,
                imageRes = R.drawable.banana
            ),
            Product(
                name = "Red Apple",
                description = "1kg, Price",
                price = 4.99,
                imageRes = R.drawable.apple
            )
        )

        viewModelScope.launch {
            sampleProducts.forEach { addToCart(it) }
        }
    }
}





