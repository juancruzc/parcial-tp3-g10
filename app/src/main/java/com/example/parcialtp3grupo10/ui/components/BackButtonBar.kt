package com.example.parcialtp3grupo10.ui.components
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController


@Composable
fun BackButtonBar(
    title: String,
    navController: NavController? = null,
    destination: String // Asegúrate de tener este parámetro
) {
    Button(
        onClick = {
            navController?.navigate(destination)
        },
        modifier = Modifier
            .fillMaxWidth()
            .height(95.dp)
            .padding(16.dp),
        shape = RoundedCornerShape(12.dp),
        colors = ButtonDefaults.buttonColors(Color.LightGray)
    ) {
        Text(title, color = Color.White, fontSize = 20.sp)
    }
}

@Preview(showBackground = true)
@Composable
fun BackButtonBarPreview() {
    MaterialTheme {
        BackButtonBar("Back to Login", destination = "login")
    }
}
