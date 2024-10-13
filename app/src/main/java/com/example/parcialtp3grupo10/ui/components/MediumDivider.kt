package com.example.parcialtp3grupo10.ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.material.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Preview
@Composable
fun MediumDivider() {
    Box(modifier = Modifier.fillMaxWidth()) {
        Divider(
            color = Color(0xFFE2E2E2),
            thickness = 1.dp,
            modifier = Modifier
                .width(350.dp)
                .align(Alignment.Center)
        )
    }
}