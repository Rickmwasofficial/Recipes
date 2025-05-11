package com.example.recipes.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class ArticlesModel(
    @StringRes val name: Int,
    @DrawableRes val img1: Int,
    @StringRes val desc: Int,
    @StringRes val content: Int
)
