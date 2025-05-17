package com.example.recipes.model

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.ui.graphics.vector.ImageVector

data class ProfileCardsModel(
    val icon: ImageVector,
    val text: String,
    val icon2: ImageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight
)
