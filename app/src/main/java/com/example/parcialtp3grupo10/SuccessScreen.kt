package com.example.parcialtp3grupo10

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.parcialtp3grupo10.ui.components.BackButtonBar
import com.example.parcialtp3grupo10.ui.components.ButtonBar

@Composable
fun SuccessScreen(navController: NavController? = null) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        val checkmarkPainter: Painter = painterResource(id = R.drawable.accepted)
        Image(
            painter = checkmarkPainter,
            contentDescription = "Order Accepted Icon",
            modifier = Modifier.size(220.dp)
        )

        Spacer(modifier = Modifier.height(34.dp))

        Text(
            text = "Your Order has been accepted",
            fontWeight = FontWeight.Bold,
            fontSize = 24.sp,
            color = Color.Black
        )

        Spacer(modifier = Modifier.height(12.dp))

        Text(
            text = "Your items have been placed and is on it's way to being processed",
            fontSize = 16.sp,
            color = Color.Gray,
            modifier = Modifier.padding(horizontal = 24.dp),
            textAlign = androidx.compose.ui.text.style.TextAlign.Center
        )

    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Bottom
    ){
        ButtonBar("Track Order")
        BackButtonBar("Back to home", navController, destination="home")
    }
}

@Preview(showBackground = true)
@Composable
fun SuccessPreview() {
    MaterialTheme {
        SuccessScreen()
    }
}
