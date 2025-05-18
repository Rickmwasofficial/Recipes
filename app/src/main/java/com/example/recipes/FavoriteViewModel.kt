package com.example.recipes

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.recipes.data.MealRepository
import com.example.recipes.model.MealModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class FavoriteViewModel(private val mealRepository: MealRepository): ViewModel() {
    private val _uiState = MutableStateFlow(
        FavoriteUiState(
            favMeals = mealRepository.meals.value.filter { it.liked } as MutableList<MealModel>
        )
    )
    val uiState: StateFlow<FavoriteUiState> = _uiState.asStateFlow()
    init {
        // Listen to updates from the repository
        viewModelScope.launch {
            mealRepository.meals.collect { updatedMeals ->
                _uiState.value = _uiState.value.copy(
                    favMeals = updatedMeals.filter { it.liked } as MutableList<MealModel>
                )
            }
        }
    }
    fun likeItem(id: Int) {
        mealRepository.toggleLike(id)
    }
}