package com.example.parcialtp3grupo10

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
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
import com.example.parcialtp3grupo10.ui.components.Header
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

@Preview
@Composable
fun AccountScreen() {
    Scaffold(
        topBar = {
            Header("Account ")
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
                items(items.size) { index ->
                    val item = items[index]
                    Column {
                        ListAccountItem(item)
                        if (index < items.size - 1) {
                            MediumDivider()
                        }
                    }
                }
            }
            Divider(
                color = Color(0xFFE2E2E2),
                thickness = 1.dp,
            )
            Spacer(modifier = Modifier.size(30.dp))
            LogoutButton("Log Out")
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
fun LogoutButton(title: String) {
    Button(
        onClick = { },
        modifier = Modifier
            .fillMaxWidth()
            .height(95.dp)
            .padding(16.dp),
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