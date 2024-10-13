package com.example.parcialtp3grupo10

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.parcialtp3grupo10.ui.components.BottNavigationBar
import com.example.parcialtp3grupo10.ui.components.ButtonBar
import com.example.parcialtp3grupo10.ui.components.Header

data class DrinkItem(
    val name: String,
    val volume: String,
    val price: String,
    val imageRes: Int
)

@Preview
@Composable
fun FavoritesScreen() {
    val drinks = listOf(
        DrinkItem("Sprite Can", "325ml", "$1.50", R.drawable.sprite),
        DrinkItem("Diet Coke", "355ml", "$1.99", R.drawable.diet_coke),
        DrinkItem("Apple & Grape Juice", "2L", "$15.50", R.drawable.juice),
        DrinkItem("Coca Cola Can", "325ml", "$4.99", R.drawable.coke),
        DrinkItem("Pepsi Can", "330ml", "$4.99", R.drawable.pepsi)
    )

    Scaffold(
        topBar = {
            Header("Favorites")
        },
        bottomBar = {
            BottNavigationBar()
        }
    ) { innerPadding ->
        Column(modifier = Modifier.padding(innerPadding)) {
            Spacer(modifier = Modifier.size(24.dp))
            Divider(
                color = Color(0xFFE2E2E2),
                thickness = 1.dp,
            )
            LazyColumn(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxHeight()
            ) {
                items(drinks.size) { index ->
                    val drink = drinks[index]
                    Column {
                        DrinkItem(drink)
                        if (index < drinks.size - 1) {
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
                    }
                }
            }
            Divider(
                color = Color(0xFFE2E2E2),
                thickness = 1.dp,
            )
            Spacer(modifier = Modifier.size(30.dp))
            ButtonBar("Add All To Cart")
        }
    }
}

@Composable
fun DrinkItem(drink: DrinkItem) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(110.dp)
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(id = drink.imageRes),
            contentDescription = drink.name,
            modifier = Modifier.size(48.dp)
        )
        Column(
            modifier = Modifier
                .weight(1f)
                .padding(start = 16.dp)
        ) {
            Text(drink.name, fontWeight = FontWeight.Bold)
            Text("${drink.volume}, Price", color = Color.Gray, fontSize = 14.sp)
        }
        Text(
            drink.price,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(end = 8.dp)
        )
        Icon(
            painter = painterResource(id = R.drawable.next_arrow),
            contentDescription = "More",
            modifier = Modifier.size(15.dp)
        )
    }
}