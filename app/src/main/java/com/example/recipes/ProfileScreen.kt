package com.example.recipes

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavHostController
import com.example.recipes.components.BottomNavSection
import com.example.recipes.ui.theme.RecipesTheme

@Composable
fun ProfileScreen(navBackStackEntry: NavBackStackEntry, navController: NavHostController, modifier: Modifier = Modifier) {
    Scaffold(
        modifier = modifier.fillMaxSize(),
    ) { innerPadding ->
        Profile(navBackStackEntry, navController, Modifier.padding(innerPadding))
    }
}

@Composable
fun Profile(navBackStackEntry: NavBackStackEntry, navController: NavHostController, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Login to view profile",
            modifier = Modifier.weight(1f),
            textAlign = TextAlign.Center
        )
        BottomNavSection(navBackStackEntry, navController)
    }
}

@Preview(showBackground = true)
@Composable
fun ProfileScreenPreview() {
    RecipesTheme(darkTheme = true) {
//        ProfileScreen()
    }
}
