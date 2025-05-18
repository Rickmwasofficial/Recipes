package com.example.recipes

import FavoriteViewModelFactory
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Delete
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.runtime.getValue
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavHostController
import com.example.recipes.components.BottomNavSection
import com.example.recipes.components.BoxImage
import com.example.recipes.components.ImageTextRow
import com.example.recipes.data.MealRepository
import com.example.recipes.model.MealModel
import com.example.recipes.ui.theme.RecipesTheme

@Composable
fun FavoriteScreen(navBackStackEntry: NavBackStackEntry, navController: NavHostController, mealRepository: MealRepository,
                   modifier: Modifier = Modifier,
                   favoriteViewModel: FavoriteViewModel = viewModel(
                       factory = FavoriteViewModelFactory(mealRepository)
                   )) {
    val favoriteUiState by favoriteViewModel.uiState.collectAsState()
    Scaffold(
        modifier = modifier.fillMaxSize(),
    ) { innerPadding ->
        Favorites(favoriteUiState.favMeals, favoriteViewModel, navBackStackEntry, navController, Modifier.padding(innerPadding))
    }
}


@Composable
fun FavoriteCard(mealModel: MealModel, favoriteViewModel: FavoriteViewModel, modifier: Modifier = Modifier) {
    Card(
        modifier = modifier
            .padding(10.dp)
            .clip(RoundedCornerShape(10.dp))
            .background(MaterialTheme.colorScheme.surfaceContainer)
    ) {
        Row(
            modifier = Modifier.fillMaxSize(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            BoxImage(80.dp, RoundedCornerShape(topStart = 10.dp, bottomStart = 10.dp), mealModel.img1)
            Column(
                modifier = Modifier
                    .fillMaxHeight()
                    .padding(3.dp)
                    .weight(1f),
                verticalArrangement = Arrangement.spacedBy(10.dp),
                horizontalAlignment = Alignment.Start
            ) {
                Text(
                    text = stringResource(mealModel.name),
                    style = MaterialTheme.typography.bodyLarge,
                    fontWeight = FontWeight.ExtraBold,
                    modifier = Modifier
                )
                Row(
                    modifier = Modifier,
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(5.dp)
                ) {
                    ImageTextRow(R.drawable.schedule_24dp_e3e3e3_fill0_wght400_grad0_opsz24, mealModel.time)
                    ImageTextRow(R.drawable.mood_24dp_e3e3e3_fill0_wght400_grad0_opsz24, mealModel.servings)
                }
            }
            Box(
                modifier = Modifier
                    .padding(end = 15.dp)
                    .clickable(onClick = { favoriteViewModel.likeItem(mealModel.id) })
            ) {
                Icon(
                    imageVector = Icons.Rounded.Delete,
                    contentDescription = null,
                    tint = Color.Red,
                    modifier = Modifier.size(30.dp)
                )
            }
        }
    }
}

@Composable
fun Favorites(meal: MutableList<MealModel>, favoriteViewModel: FavoriteViewModel, navBackStackEntry: NavBackStackEntry, navController: NavHostController, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Row(
            modifier = modifier
                .fillMaxWidth()
                .padding(start = 10.dp, end = 18.dp, bottom = 10.dp, top = 20.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            Text(
                text = "Favorites",
                style = MaterialTheme.typography.headlineSmall,
                color = MaterialTheme.colorScheme.onBackground,
                fontWeight = FontWeight.ExtraBold,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .weight(1f)
            )
        }
        LazyColumn(
            modifier = Modifier.weight(1f)
        ) {
            items(meal) { meal ->
                FavoriteCard(meal, favoriteViewModel)
            }
        }
        BottomNavSection(navBackStackEntry, navController)
    }
}

@Preview
@Composable
fun FavoriteScreenPreview() {
    RecipesTheme(darkTheme = true) {
//        FavoriteCard()
    }
}