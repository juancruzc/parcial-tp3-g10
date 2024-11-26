package com.example.parcialtp3grupo10.viewmodel

import android.content.Context
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.parcialtp3grupo10.model.Order
import com.example.parcialtp3grupo10.model.OrderItem
import com.example.parcialtp3grupo10.model.Product
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
    private val itemsCollection by lazy { db?.collection("cart") }

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
    /*
    private fun updateCartState(items: List<Product>) {
        val total = calculateTotal(items)
        _cartState.value = CartState(
            items = items,
            total = total
        )
    }
    */
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

    fun loadSampleProducts(context: Context) {
        /*val sampleProducts = listOf(
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
        )*/

        itemsCollection?.get()?.addOnSuccessListener { documents ->
            val itemsList = mutableListOf<Product>()

            for (item in documents) {
                val name = item.getString("name") ?: ""
                val description = item.getString("description") ?: ""
                val price = item.getString("price")?.toDoubleOrNull() ?: 0.0

                val imageResName = item.getString("imageRes") ?: ""
                val imageRes = try {
                    context.resources.getIdentifier(imageResName, "drawable", context.packageName)
                } catch (e: Exception) {
                    Log.e("Firestore", "Error resolving image resource: ${e.message}")
                    0
                }

                val quantity = item.getString("quantity")?.toInt() ?: 1

                val product = Product(name, description, price, imageRes, quantity)
                itemsList.add(product)
            }

            viewModelScope.launch {
                updateCartState(itemsList)
            }
        }?.addOnFailureListener { exception ->
            Log.e("Firestore", "Error loading products: ${exception.message}")
        }
    }

    // Adjust addItemToCart to handle quantity
    fun addItemToCart(product: Product) {
        viewModelScope.launch {
            val currentItems = _cartState.value.items.toMutableList()
            val index = currentItems.indexOfFirst { it == product }

            if (index != -1) {
                // Increment quantity if item already exists
                val updatedItem = currentItems[index].copy(quantity = currentItems[index].quantity + 1)
                currentItems[index] = updatedItem
            } else {
                // Add new item
                currentItems.add(product)
            }

            updateCartState(currentItems)
        }
    }

    // New method to decrement quantity or remove item
    fun removeItemQuantity(product: Product) {
        viewModelScope.launch {
            val currentItems = _cartState.value.items.toMutableList()
            val index = currentItems.indexOfFirst { it == product }

            if (index != -1) {
                val currentItem = currentItems[index]
                if (currentItem.quantity > 1) {
                    // Decrease quantity
                    currentItems[index] = currentItem.copy(quantity = currentItem.quantity - 1)
                } else {
                    // Remove item if quantity becomes 0
                    currentItems.removeAt(index)
                }
            }

            updateCartState(currentItems)
        }
    }

    // Reuse this private method to update the cart state and recalculate the total
    private fun updateCartState(items: List<Product>) {
        _cartState.value = CartState(
            items = items,
            total = items.sumOf { it.price * it.quantity }
        )
    }
}






