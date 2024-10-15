package com.example.parcialtp3grupo10.model

import androidx.compose.ui.graphics.painter.Painter

data class Beverage(
    val name: String,
    val description: String,
    val volume: String,
    val price: Double,
    val imagePainter: Painter
)
