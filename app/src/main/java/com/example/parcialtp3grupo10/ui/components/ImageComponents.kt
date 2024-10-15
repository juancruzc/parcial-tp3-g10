package com.example.parcialtp3grupo10.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.parcialtp3grupo10.R

@Composable
fun TopLeftImage(modifier: Modifier = Modifier) {
    Image(
        painter = painterResource(id = R.drawable.img_5),
        contentDescription = "Image in top left",
        modifier = modifier, // Aquí recibes el modificador, lo cual permite que 'align' se aplique en el padre
        contentScale = ContentScale.Crop
    )
}

@Composable
fun TopFullWidthImage(modifier: Modifier = Modifier) {
    Image(
        painter = painterResource(id = R.drawable.img_4),
        contentDescription = "Image at the top",
        modifier = modifier.fillMaxWidth(),
        contentScale = ContentScale.Crop
    )
}

@Composable
fun BottomCenteredImage(modifier: Modifier = Modifier) {
    Image(
        painter = painterResource(id = R.drawable.img_2),
        contentDescription = "Image at the bottom",
        modifier = modifier
            .fillMaxWidth(0.5f) // Ajusta el ancho al 50% de la pantalla
            .height(50.dp), // Ajusta la altura según sea necesario
        contentScale = ContentScale.Crop
    )
}
