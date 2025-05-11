package com.example.recipes.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class IngredientsModel(
    @StringRes val name: Int,
    @StringRes val qty: Int,
    @DrawableRes val img: Int
)
