package com.example.parcialtp3grupo10.ui.components

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.parcialtp3grupo10.R
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue

@SuppressLint("DefaultLocale")
@Composable
fun CartCard(
    productName: String,
    description: String,
    price: Double,
    imagePainter: Painter,
    onRemove:()-> Unit,
    modifier: Modifier = Modifier
) {
    var quantity by remember { mutableStateOf(1) }

    Card(
        modifier = modifier
            .width(460.dp)
            .height(100.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(12.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = imagePainter,
                contentDescription = "Product Image",
                modifier = Modifier
                    .size(100.dp)
                    .padding(4.dp),
                contentScale = ContentScale.Fit,
            )

            Column(
                horizontalAlignment = Alignment.Start,
                modifier = Modifier
                    .weight(1f)
                    .padding(start = 12.dp),
            ) {
                Text(
                    text = productName,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold
                )

                Text(
                    text = description,
                    style = MaterialTheme.typography.bodySmall,
                    color = Color.Gray,
                    modifier = Modifier.padding(top = 4.dp, bottom = 4.dp)
                )

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .weight(1f)
                        .padding(start = 12.dp),
                ) {
                    IconButton(
                        onClick = { if (quantity > 1) quantity-- },
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.minus),
                            contentDescription = "Add to cart",
                            tint = Color(0xFFB3B3B3),
                            modifier = Modifier
                                .size(40.dp)
                        )
                    }

                    Text(
                        text = quantity.toString(),
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold
                    )

                    IconButton(
                        onClick = { quantity++ },
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.plus),
                            contentDescription = "Add to cart",
                            tint = Color(0xFF53B175),
                            modifier = Modifier
                                .size(40.dp)
                        )
                    }
                }
            }

            Column(
                horizontalAlignment = Alignment.End,
                verticalArrangement = Arrangement.spacedBy(15.dp)
            ){
                Icon(
                    imageVector = Icons.Default.Close,
                    contentDescription = "Remove form cart",
                    tint = Color(0xFFB3B3B3),
                    modifier = Modifier
                        .size(18.dp)
                )

                Text(
                    text = "$${String.format("%.2f", price)}",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold
                )
            }
        }
        HorizontalDivider(color = Color(0xFFB3B3B3), thickness = 2.dp)
    }
}

@Preview(showBackground = true)
@Composable
fun CartCardPreview() {
    val imagePainter = painterResource(id = R.drawable.apple)
    MaterialTheme {
        CartCard(
            productName = "Apple",
            description = "Fresh Red Apple",
            price = 1.99,
            imagePainter = imagePainter,
            onRemove = {}
        )
    }
}