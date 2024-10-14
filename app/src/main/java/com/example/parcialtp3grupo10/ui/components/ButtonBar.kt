package com.example.parcialtp3grupo10.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController

@Preview(showBackground = true)
@Composable
fun ButtonBarPreview() {
    MaterialTheme {
        ButtonBar("Add all to cart")
    }
}

@Composable
fun ButtonBar(title: String) {
    Button(
        onClick = {  },
        modifier = Modifier
            .fillMaxWidth()
            .height(95.dp)
            .padding(16.dp),
        shape = RoundedCornerShape(12.dp),
        colors = ButtonDefaults.buttonColors(Color(0xFF53B175))
    ) {
        Text(title, color = Color.White, fontSize = 20.sp)
    }
}

@Composable
fun ButtonBar2(title: String, onClick: () -> Unit) {
    Button(
        onClick = onClick,  // Aquí aceptamos la función que se ejecutará al hacer clic
        modifier = Modifier
            .fillMaxWidth()
            .height(95.dp)
            .padding(16.dp),
        shape = RoundedCornerShape(12.dp),
        colors = ButtonDefaults.buttonColors(Color(0xFF53B175))
    ) {
        Text(title, color = Color.White, fontSize = 20.sp)
    }
}