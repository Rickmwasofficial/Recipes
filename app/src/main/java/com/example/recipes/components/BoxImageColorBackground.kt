package com.example.recipes.components

import androidx.annotation.DrawableRes
import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import com.example.recipes.boundsTransform

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun BoxImageColorBackground(@DrawableRes img: Int, shape: RoundedCornerShape, index: Int, sharedTransitionScope: SharedTransitionScope, animatedContentScope: AnimatedContentScope, modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
    )
    with(sharedTransitionScope) {
        Image(
            painter = painterResource(img),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            alignment = Alignment.Center,
            alpha = 0.6f,
            modifier = Modifier
                .fillMaxSize()
                .sharedElement(
                    sharedContentState = rememberSharedContentState(key = "header-${index}"),
                    animatedVisibilityScope = animatedContentScope,
                    boundsTransform = boundsTransform
                )
                .clip(shape)
        )
    }
}