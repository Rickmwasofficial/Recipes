package com.example.recipes

import androidx.annotation.StringRes
import com.example.recipes.model.ArticlesModel
import com.example.recipes.model.MealModel
import com.example.recipes.model.UserModel

data class HomeUiState(
    val user: UserModel,
    val meals: MutableList<MealModel> = mutableListOf(),
    val articles: List <ArticlesModel> = listOf(),
    @StringRes val category: Int = R.string.all,
    val categories: Set<Int>,
    val searchString: String = ""
)