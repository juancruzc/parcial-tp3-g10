package com.example.parcialtp3grupo10.ui.components

import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import com.example.parcialtp3grupo10.R

@Composable
fun TopImage(modifier: Modifier = Modifier) {
    Image(
        painter = painterResource(id = R.drawable.img_3),
        contentDescription = null,
        modifier = modifier,
        contentScale = ContentScale.Crop
    )
}

@Composable
fun BottomImage(modifier: Modifier = Modifier) {
    Image(
        painter = painterResource(id = R.drawable.img_2),
        contentDescription = null,
        modifier = modifier,
        contentScale = ContentScale.Crop
    )
}
