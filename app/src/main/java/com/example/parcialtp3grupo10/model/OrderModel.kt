package com.example.parcialtp3grupo10.model

data class OrderItem(
    val productName: String,
    val quantity: Int,
    val price: Double,
    val subtotal: Double = price * quantity
)

data class Order(
    val items: List<OrderItem>,
    val totalAmount: Double,
    val timestamp: Long = System.currentTimeMillis()
)

/*data class Product(
    val name: String,
    val description: String,
    val price: Double,
    val imageRes: Int
)*/

