package com.example.recipes

import HomeViewModelFactory
import android.annotation.SuppressLint
import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.rounded.FavoriteBorder
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection
import androidx.compose.ui.input.nestedscroll.NestedScrollSource
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.recipes.boundsTransform
import com.example.recipes.components.BoxImage
import com.example.recipes.components.BoxImageColorBackground
import com.example.recipes.components.ButtonWithIcon
import com.example.recipes.components.ImageTextRow
import com.example.recipes.data.MealData.meals
import com.example.recipes.data.MealRepository
import com.example.recipes.model.IngredientsModel
import com.example.recipes.model.MealModel
import com.example.recipes.ui.theme.RecipesTheme


@SuppressLint("StateFlowValueCalledInComposition")
@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun RecipeScreen(sharedTransitionScope: SharedTransitionScope, animatedContentScope: AnimatedContentScope, navController: NavHostController, text: Int, modifier: Modifier = Modifier, mealRepository: MealRepository, homeViewModel: HomeViewModel = viewModel(
    factory = HomeViewModelFactory(mealRepository)
)) {
    val currentMeal = mealRepository.meals.value[text]
    Surface(
        modifier = modifier
            .fillMaxSize()
    ) {
        var currentImageSize by remember { mutableStateOf(480.dp) }

        val nestedScrollConnection = remember {
            object : NestedScrollConnection {
                override fun onPreScroll(available: Offset, source: NestedScrollSource): Offset {
                    val delta = available.y
                    val newImageSize = currentImageSize + delta.dp

                    // Constrain the image size within the allowed bounds
                    currentImageSize = newImageSize.coerceIn(380.dp, 480.dp)

                    // Return the consumed scroll amount
                    return Offset.Zero
                }
            }
        }
        Box(Modifier.fillMaxSize().nestedScroll(nestedScrollConnection)) {
            RecipeDesc(currentImageSize, sharedTransitionScope, animatedContentScope, navController = navController, meal = currentMeal, index = text, homeViewModel = homeViewModel)
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .offset {
                        IntOffset(0, currentImageSize.roundToPx())
                    }
                    .padding(bottom = 50.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                item {
                    Ingredients(meal = currentMeal)
                }
                item {
                    About(meal = currentMeal)
                }
            }
        }
    }
}

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun RecipeDesc(imageHeight: Dp, sharedTransitionScope: SharedTransitionScope, animatedContentScope: AnimatedContentScope, navController: NavHostController, index: Int, modifier: Modifier = Modifier, meal: MealModel, homeViewModel: HomeViewModel) {
    val icon = if (meal.liked) Icons.Filled.Favorite else Icons.Rounded.FavoriteBorder
    Card(
        modifier = modifier
            .fillMaxWidth()
            .height(imageHeight),
        shape = RoundedCornerShape(topStart = 0.dp, topEnd = 0.dp, bottomEnd = 16.dp, bottomStart = 16.dp),
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
        ) {
            BoxImageColorBackground(meal.img2, RoundedCornerShape(topStart = 0.dp, topEnd = 0.dp, bottomEnd = 16.dp, bottomStart = 16.dp), meal.id, sharedTransitionScope, animatedContentScope, modifier
                .fillMaxSize()
                .background(Color.Black))
            Box(
                modifier = modifier
                    .fillMaxSize()
                    .background(Color.Transparent),
                contentAlignment = Alignment.Center
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(imageHeight - 50.dp)
                        .padding(horizontal = 23.dp)
                        .background(Color.Transparent),
                    horizontalAlignment = Alignment.Start,
                    verticalArrangement = Arrangement.SpaceBetween
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 35.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        ButtonWithIcon(Icons.AutoMirrored.Rounded.ArrowBack,  { navController.popBackStack() })
                        ButtonWithIcon(icon, { homeViewModel.likeItem(meal.id) }, tint = if (meal.liked) Color.Green else Color.White)
                    }
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 5.dp),
                        verticalArrangement = Arrangement.spacedBy(10.dp),
                        horizontalAlignment = Alignment.Start
                    ) {
                        with(sharedTransitionScope) {
                            Text(
                                text = stringResource(meal.name),
                                style = MaterialTheme.typography.headlineLarge,
                                color = Color.White,
                                fontWeight = FontWeight.ExtraBold,
                                modifier = Modifier
                                    .sharedElement(
                                        sharedContentState = rememberSharedContentState(key = "title-${meal.id}"),
                                        animatedVisibilityScope = animatedContentScope,
                                        boundsTransform = boundsTransform
                                    )
                                    .width(250.dp)
                            )
                            Row(
                                modifier = Modifier
                                    .height(20.dp)
                                    .sharedElement(
                                        sharedContentState = rememberSharedContentState(key = "row-${meal.id}"),
                                        animatedVisibilityScope = animatedContentScope,
                                        boundsTransform = boundsTransform
                                    ),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.spacedBy(5.dp)
                            ) {
                                ImageTextRow(R.drawable.schedule_24dp_e3e3e3_fill0_wght400_grad0_opsz24, meal.time)
                                ImageTextRow(R.drawable.mood_24dp_e3e3e3_fill0_wght400_grad0_opsz24, meal.servings)
                            }
                        }
                        Text(
                            text = stringResource(meal.shortDesc),
                            style = MaterialTheme.typography.bodyMedium,
                            color = Color.White,
                            fontWeight = FontWeight.SemiBold
                        )
                    }

                }

            }

        }
    }
}

@Composable
fun IngredientCard(ingredient: IngredientsModel, modifier: Modifier = Modifier) {
    Card (
        modifier = modifier
            .width(105.dp)
            .height(120.dp)
            .padding(end = 10.dp)
            .clip(shape = RoundedCornerShape(15.dp)),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 10.dp,
            draggedElevation = 50.dp,
            pressedElevation = 20.dp
        )
    ) {
        Column(
            modifier = modifier
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceEvenly,
        ) {
            BoxImage(60.dp, RoundedCornerShape(30.dp), ingredient.img)
            Column(
                verticalArrangement = Arrangement.spacedBy(1.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = stringResource(ingredient.name),
                    style = MaterialTheme.typography.bodyMedium,
                    fontWeight = FontWeight.ExtraBold,
                    textAlign = TextAlign.Center,
                    modifier = Modifier,
                    maxLines = 1
                )
                Text(
                    text = stringResource(ingredient.qty),
                    style = MaterialTheme.typography.bodySmall,
                    modifier = Modifier
                )
            }
        }
    }
}

@Composable
fun Ingredients(meal: MealModel, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(start = 18.dp, end = 1.dp, bottom = 10.dp, top = 15.dp),
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        Text(
            text = stringResource(R.string.ingredients_title),
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.ExtraBold
        )
        LazyRow() {
            items(meal.ingredients) { ingredient ->
                IngredientCard(ingredient)
            }
        }
    }
}

@Composable
fun About(meal: MealModel, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(start = 18.dp, end = 1.dp, bottom = 10.dp, top = 15.dp),
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        Text(
            text = stringResource(R.string.about_title),
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.ExtraBold
        )
        Text(
            text = stringResource(meal.about),
            style = MaterialTheme.typography.bodyMedium
        )
    }
}

@Preview(showBackground = true)
@Composable
fun RecipeScreenPreview() {
    RecipesTheme(darkTheme = true) {
//        RecipeScreen()
    }
}