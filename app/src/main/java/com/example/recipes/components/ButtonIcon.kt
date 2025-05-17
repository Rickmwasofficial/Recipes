package com.example.recipes.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController

@Composable
fun ButtonWithIcon(icon: ImageVector, onClick: () -> Unit, modifier: Modifier = Modifier, tint: Color = Color.White, bgColor: Color = MaterialTheme.colorScheme.surfaceContainer) {
    IconButton(
        onClick = { onClick() },
        modifier = modifier
            .background(
                bgColor,
                shape = RoundedCornerShape(10.dp)
            )
            .height(40.dp)
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = tint
        )
    }
}