package com.example.parcialtp3grupo10.ui.components

//noinspection UsingMaterialAndMaterial3Libraries
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.parcialtp3grupo10.R

@Composable
fun BottNavigationBar(navController: NavController, isDarkMode: Boolean, currentRoute: String) {
    BottomNavigation(
        backgroundColor = if (isDarkMode) Color.Black else Color.White,
        contentColor = if (isDarkMode) Color.White else Color.Black,
        modifier = Modifier.height(56.dp)
    ) {
        val items = listOf("home", "explore", "cart", "favourites", "account")
        val icons = listOf(R.drawable.shop, R.drawable.explore, R.drawable.cart, R.drawable.img_6, R.drawable.account)

        val iconColor = if (isDarkMode) Color.White else Color.Black
        val selectedLineColor = if (isDarkMode) Color.White else Color.Black

        items.forEachIndexed { index, item ->
            val isSelected = currentRoute == item

            BottomNavigationItem(
                icon = {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Icon(
                            painter = painterResource(id = icons[index]),
                            contentDescription = null,
                            tint = iconColor,
                            modifier = Modifier.size(45.dp)
                        )
                        if (isSelected) {
                            Box(
                                modifier = Modifier
                                    .padding(top = 4.dp)
                                    .width(30.dp)
                                    .height(3.dp)
                                    .background(selectedLineColor)
                            )
                        }
                    }
                },
                selected = isSelected,
                onClick = { navController.navigate(item) }
            )
        }
    }
}
