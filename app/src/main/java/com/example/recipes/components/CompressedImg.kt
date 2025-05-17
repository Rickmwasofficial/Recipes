package com.example.recipes.components

import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import coil.compose.AsyncImage
import coil.request.ImageRequest
import coil.size.Scale
import androidx.compose.ui.platform.LocalContext

@Composable
fun CompressedImage(
    drawableRes: Int,
    modifier: Modifier = Modifier
) {
    AsyncImage(
        model = ImageRequest.Builder(LocalContext.current)
            .data(drawableRes)
            .scale(Scale.FILL) // This ensures the image scales to fit its container
            .build(),
        contentDescription = null,
        modifier = modifier
    )
}
