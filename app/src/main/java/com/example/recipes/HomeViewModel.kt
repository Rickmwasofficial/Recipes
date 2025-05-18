package com.example.recipes

import android.content.Context
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.recipes.data.ArticleData.getArticles
import com.example.recipes.data.MealRepository
import com.example.recipes.data.UserData.getUsers
import com.example.recipes.model.MealModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlin.collections.filter

const val TAG2 = "Search"

class HomeViewModel(private val mealRepository: MealRepository): ViewModel() {
    var cats = mealRepository.meals.value.map { it.category }.toSet()
    val mealsCat = mutableSetOf(R.string.all) + cats
    private val _uiState = MutableStateFlow(
        HomeUiState(
            meals = mealRepository.meals.value as MutableList<MealModel>,
            articles = getArticles(),
            user = getUsers()[0],
            categories = mealsCat
        )
    )
    init {
        // Listen to updates from the repository
        viewModelScope.launch {
            mealRepository.meals.collect { updatedMeals ->
                _uiState.value = _uiState.value.copy(meals = updatedMeals as MutableList<MealModel>)
            }
        }
    }

    // make the state a read only state flow
    val uiState: StateFlow<HomeUiState> = _uiState.asStateFlow()

    fun filterRecipes(category: Int) {
        if (category == mealsCat.toList()[0]) {
            _uiState.value = _uiState.value.copy(meals = mealRepository.meals.value as MutableList<MealModel>, category = category)
        } else {
            var filteredMeals = mealRepository.meals.value.filter { it.category == category }
            _uiState.value = _uiState.value.copy(meals = filteredMeals as MutableList<MealModel>, category = category)
        }
    }

    fun searchItems(searchTerm: String, context: Context) {
        try {
            val trimmedSearchTerm = searchTerm.trim().lowercase()

            val foundMeals = mealRepository.meals.value.filter {
                context.getString(it.name).lowercase().contains(trimmedSearchTerm)
            }

            _uiState.value = _uiState.value.copy(
                meals = foundMeals as MutableList<MealModel>,
                searchString = searchTerm,
                category = mealRepository.meals.value.map { it.category }.toList()[0]
            )

            Log.d(TAG2, "Found ${foundMeals.size} meals matching '$searchTerm'")
        } catch (err: Exception) {
            Log.e(TAG2, "Error in searchItems: ${err.message}")
        }
    }

    fun likeItem(id: Int) {
        mealRepository.toggleLike(id)
    }
}