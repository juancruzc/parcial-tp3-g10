package com.example.parcialtp3grupo10.ui
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.parcialtp3grupo10.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OrderFailedDialog(
    onTryAgain: () -> Unit = {},
    onBackToHome: () -> Unit = {}
) {
    Surface(
        modifier = Modifier
            .fillMaxSize()
            .padding(32.dp),
        shape = RoundedCornerShape(16.dp),
        color = Color.White
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.padding(16.dp)
        ) {
            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.TopStart
            ) {
                IconButton(onClick = { /* Close dialog */ }) {
                    Icon(
                        imageVector = Icons.Default.Close,
                        contentDescription = "Close"
                    )
                }
            }
            Spacer(modifier = Modifier.height(16.dp))
            Box(
                modifier = Modifier
                    .size(100.dp)
                    .background(color = Color(0xFFE0F7FA), shape = CircleShape)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_grocery_bag),
                    contentDescription = null,
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Crop
                )
            }
            Spacer(modifier = Modifier.height(24.dp))
            Text(
                "Oops! Order Failed",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                "Something went terribly wrong.",
                fontSize = 16.sp,
                color = Color.Gray
            )
            Spacer(modifier = Modifier.height(24.dp))
            Button(
                onClick = onTryAgain,
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF81C784))
            ) {
                Text("Please Try Again")
            }
            Spacer(modifier = Modifier.height(16.dp))
            TextButton(onClick = onBackToHome) {
                Text("Back to home", color = Color.Black)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun OrderFailedDialogPreview() {
    OrderFailedDialog()
}
