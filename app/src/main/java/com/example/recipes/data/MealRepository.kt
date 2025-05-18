package com.example.recipes.data

import com.example.recipes.model.MealModel
import kotlinx.coroutines.flow.StateFlow

class MealRepository {
    val meals: StateFlow<List<MealModel>> = MealData.meals

    fun toggleLike(id: Int) {
        MealData.toggleLike(id)  // Delegate to MealData
    }
}