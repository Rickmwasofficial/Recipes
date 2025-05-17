package com.example.recipes.components

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource

@Composable
fun TransitionScopeImg(@DrawableRes img: Int, modifier: Modifier = Modifier, alpha: Float = 1f) {
    Image(
        painter = painterResource(img),
        contentDescription = null,
        contentScale = ContentScale.Crop,
        alpha = alpha,
        modifier = modifier
    )
}