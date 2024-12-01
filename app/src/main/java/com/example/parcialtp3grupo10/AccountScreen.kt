package com.example.parcialtp3grupo10

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Brightness2
import androidx.compose.material.icons.filled.Brightness7
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.parcialtp3grupo10.ui.components.BottNavigationBar
import com.example.parcialtp3grupo10.ui.components.MediumDivider

data class ListableItem(
    val name: String,
    val imageRes: Int
)

val items = listOf(
    ListableItem("Orders", R.drawable.orders),
    ListableItem("My Details", R.drawable.my_details),
    ListableItem("Delivery Address", R.drawable.address),
    ListableItem("Payment Methods", R.drawable.payment),
    ListableItem("Promo Card", R.drawable.promo),
    ListableItem("Notifications", R.drawable.bell),
    ListableItem("Help", R.drawable.help),
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AccountScreen(navController: NavController?, toggleDarkMode: () -> Unit, isDarkMode: Boolean) {
    val currentRoute = navController?.currentBackStackEntry?.destination?.route ?: "account"

    fun logout() {
        navController?.navigate("login") {
            popUpTo("login") { inclusive = true }
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Box(
                        modifier = Modifier.fillMaxWidth(),
                        contentAlignment = Alignment.Center
                    ) {
                        Text("Account", color = MaterialTheme.colorScheme.onSurface)
                    }
                },
                navigationIcon = {
                    IconButton(onClick = {}) {
                        Icon(
                            imageVector = Icons.Default.Menu,
                            contentDescription = "Menu",
                            tint = MaterialTheme.colorScheme.onSurface
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.surface
                )
            )
        },
        bottomBar = {
            if (navController != null) {
                BottNavigationBar(navController, isDarkMode, currentRoute)
            }
        }
    ) { innerPadding ->
        Column(modifier = Modifier.padding(innerPadding)) {
            // Perfil de usuario
            UserProfile()

            Spacer(modifier = Modifier.size(24.dp))
            Divider(
                color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.2f),
                thickness = 1.dp,
            )
            LazyColumn(
                modifier = Modifier.weight(1f).fillMaxHeight()
            ) {
                items(items.size) { index ->
                    val item = items[index]
                    Column {
                        ListAccountItem(item)
                        if (index < items.size - 1) {
                            MediumDivider()
                        }
                    }
                }
                item {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text("Dark mode", fontWeight = FontWeight.Bold, fontSize = 18.sp)
                        Switch(
                            checked = isDarkMode,
                            onCheckedChange = { toggleDarkMode() }
                        )
                    }
                }
            }
            Divider(
                color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.2f),
                thickness = 1.dp,
            )
            Spacer(modifier = Modifier.size(30.dp))
            LogoutButton(title = "Log Out", onLogout = { logout() })
        }
    }
}

@Composable
fun UserProfile() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(id = R.drawable.img_7), // Reemplaza con la imagen adecuada
            contentDescription = "Profile Picture",
            modifier = Modifier.size(50.dp).padding(end = 8.dp)
        )
        Column {
            Text("Afsar Hossen", fontWeight = FontWeight.Bold, fontSize = 20.sp)
            Text("lmshuvo97@gmail.com", color = Color.Gray, fontSize = 16.sp)
        }
        Spacer(modifier = Modifier.weight(1f))
        IconButton(onClick = { /* Acción de edición */ }) {
            Icon(
                painter = painterResource(id = R.drawable.img_8), // Reemplaza con el ícono adecuado
                contentDescription = "Edit",
                tint = MaterialTheme.colorScheme.primary,
                modifier = Modifier.size(20.dp)
            )
        }
    }
}

@Composable
fun ListAccountItem(item: ListableItem) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(70.dp)
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(id = item.imageRes),
            contentDescription = item.name,
            modifier = Modifier.size(25.dp)
        )
        Column(
            modifier = Modifier
                .weight(1f)
                .padding(start = 16.dp)
        ) {
            Text(item.name, fontWeight = FontWeight.Bold, fontSize = 18.sp)
        }
        Icon(
            painter = painterResource(id = R.drawable.next_arrow),
            contentDescription = "More",
            modifier = Modifier.size(15.dp)
        )
    }
}

@Composable
fun LogoutButton(title: String, onLogout: () -> Unit) {
    Button(
        onClick = onLogout,
        modifier = Modifier.fillMaxWidth().height(95.dp).padding(16.dp),
        shape = RoundedCornerShape(12.dp),
        colors = ButtonDefaults.buttonColors(Color(color = 0xFFF2F3F2))
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                painter = painterResource(id = R.drawable.logout),
                contentDescription = "Logout",
                modifier = Modifier.size(25.dp),
                tint = Color(color = 0xFF53B175)
            )
            Spacer(modifier = Modifier.width(12.dp))
            Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
                Text(
                    text = title,
                    color = Color(color = 0xFF53B175),
                    fontSize = 20.sp
                )
            }
        }
    }
}
