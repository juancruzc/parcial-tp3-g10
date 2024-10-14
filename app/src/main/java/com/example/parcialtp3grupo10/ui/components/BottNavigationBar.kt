package com.example.parcialtp3grupo10.ui.components

//noinspection UsingMaterialAndMaterial3Libraries
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.parcialtp3grupo10.R

@Composable
fun BottNavigationBar(navController: NavController) {
    BottomNavigation(
        backgroundColor = Color.White,
        modifier = Modifier.height(85.dp)
    ) {
        BottomNavigationItem(
            icon = {
                Icon(
                    modifier = Modifier
                        .fillMaxWidth()
                        .size(60.dp)
                        .padding(top = 10.dp),
                    painter = painterResource(id = R.drawable.shop),
                    contentDescription = "Shop"
                )
            },
            selected = false,
            onClick = { navController.navigate("home") }
        )
        BottomNavigationItem(
            modifier = Modifier.fillMaxWidth(),
            icon = {
                Icon(
                    modifier = Modifier
                        .fillMaxWidth()
                        .size(60.dp)
                        .padding(top = 14.dp),
                    painter = painterResource(id = R.drawable.explore),
                    contentDescription = "Explore"
                )
            },
            selected = false,
            onClick = { navController.navigate("explore") }
        )
        BottomNavigationItem(

            icon = {
                Icon(
                    modifier = Modifier
                        .fillMaxWidth()
                        .size(60.dp)
                        .padding(top = 11.dp),
                    painter = painterResource(id = R.drawable.cart),
                    contentDescription = "Explore"
                )
            },
            selected = false,
            onClick = { navController.navigate("cart") }
        )
        BottomNavigationItem(
            icon = {
                Icon(
                    modifier = Modifier
                        .fillMaxWidth()
                        .size(59.dp)
                        .padding(top = 11.dp),
                    painter = painterResource(id = R.drawable.favourite),
                    contentDescription = "Favorites"
                )
            },
            modifier = Modifier.padding(),
            selected = false,
            onClick = { navController.navigate("favourites") }
        )
        BottomNavigationItem(
            icon = {
                Icon(
                    modifier = Modifier
                        .fillMaxWidth()
                        .size(60.dp)
                        .padding(top = 10.dp),
                    painter = painterResource(id = R.drawable.account),
                    contentDescription = "Account"
                )
            },
            selected = false,
            onClick = { navController.navigate("account") }
        )
    }
}
