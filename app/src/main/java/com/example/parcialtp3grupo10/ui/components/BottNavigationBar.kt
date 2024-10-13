package com.example.parcialtp3grupo10.ui.components

//noinspection UsingMaterialAndMaterial3Libraries
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.parcialtp3grupo10.R

@Preview
@Composable
fun BottNavigationBar() {
    BottomNavigation(backgroundColor = Color.White) {
        BottomNavigationItem(
            icon = {
                Icon(
                    modifier = Modifier
                        .fillMaxWidth()
                        .size(25.dp),
                    painter = painterResource(id = R.drawable.shop),
                    contentDescription = "Shop"
                )
            },
            label = { Text("Shop") },
            selected = false,
            onClick = { }
        )
        BottomNavigationItem(
            modifier = Modifier.fillMaxWidth(),
            icon = {
                Icon(
                    modifier = Modifier
                        .fillMaxWidth()
                        .size(24.dp),
                    painter = painterResource(id = R.drawable.explore),
                    contentDescription = "Explore"
                )
            },
            label = { Text("Explore") },
            selected = false,
            onClick = { }
        )
        BottomNavigationItem(

            icon = {
                Icon(
                    modifier = Modifier
                        .fillMaxWidth()
                        .size(25.dp),
                    painter = painterResource(id = R.drawable.cart),
                    contentDescription = "Explore"
                )
            },
            label = { Text("Cart") },
            selected = false,
            onClick = { }
        )
        BottomNavigationItem(
            icon = {
                Icon(
                    modifier = Modifier
                        .fillMaxWidth()
                        .size(25.dp),
                    painter = painterResource(id = R.drawable.favourite),
                    contentDescription = "Favorites"
                )
            },
            modifier = Modifier.padding(),
            label = { Text("Favorites", maxLines = 1) },
            selected = false,
            onClick = { }
        )
        BottomNavigationItem(
            icon = {
                Icon(
                    modifier = Modifier
                        .fillMaxWidth()
                        .size(26.dp),
                    painter = painterResource(id = R.drawable.account),
                    contentDescription = "Account"
                )
            },
            label = { Text("Account") },
            selected = false,
            onClick = { }
        )
    }
}