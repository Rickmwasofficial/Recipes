package com.example.recipes

import com.example.recipes.model.MealModel

data class FavoriteUiState(
    val favMeals: MutableList<MealModel> = mutableListOf()
)
