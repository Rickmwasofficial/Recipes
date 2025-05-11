package com.example.recipes.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class MealModel(
    @DrawableRes val img1: Int,
    @DrawableRes val img2: Int,
    @StringRes val name: Int,
    @StringRes val shortDesc: Int,
    @StringRes val about: Int,
    @StringRes val time: Int,
    @StringRes val servings: Int,
    @StringRes val category: Int,
    val ingredients: List<IngredientsModel>,
)
