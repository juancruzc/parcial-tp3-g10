package com.example.parcialtp3grupo10.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.material3.Text

@Composable
fun TitleAndSubtitle(modifier: Modifier = Modifier) {
    Column(modifier = modifier) {
        Text(
            text = "Sign Up",
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 8.dp)
        )

        Text(
            text = "Enter your credentials to continue",
            fontSize = 16.sp,
            color = androidx.compose.ui.graphics.Color.Gray,
            modifier = Modifier.padding(bottom = 24.dp)
        )
    }
}
