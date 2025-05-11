package com.example.recipes.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class UserModel(
    @StringRes val name: Int,
    @DrawableRes val img: Int,
    val favourites: List<MealModel>
)
