package com.example.parcialtp3grupo10

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.parcialtp3grupo10.ui.FindProductsScreen
import com.example.parcialtp3grupo10.ui.components.BottNavigationBar
import com.example.parcialtp3grupo10.ui.theme.ParcialTP3Grupo10Theme

@Preview(showBackground = true)
@Composable
fun MainScreen(modifier: Modifier = Modifier) {
    var selectedIndex by remember {
        mutableIntStateOf(0)
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        bottomBar = {
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
                    onClick = { selectedIndex = 0 }
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
                    onClick = { selectedIndex = 1 }
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
                    onClick = { selectedIndex = 2 }
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
                    onClick = { selectedIndex = 3 }
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
                    onClick = { selectedIndex = 4 }
                )
            }
        }
    ) { innerPadding ->
        ContentScreen(modifier = Modifier.padding(innerPadding),selectedIndex)
    }
}

@Composable
fun ContentScreen(modifier: Modifier = Modifier, selectedIndex : Int) {
    when(selectedIndex){
        0-> HomeScreen()
        1-> FindProductsScreen()
        2-> CartScreen()
        3-> FavoritesScreen()
        4-> AccountScreen()
    }
}