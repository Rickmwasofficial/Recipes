package com.example.recipes

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.recipes.ui.theme.RecipesTheme

@Composable
fun RecipeScreen(modifier: Modifier = Modifier) {

}

@Preview(showBackground = true)
@Composable
fun RecipeScreenPreview() {
    RecipesTheme {
        RecipeScreen()
    }
}