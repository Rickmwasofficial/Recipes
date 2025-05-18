package com.example.recipes

import HomeViewModelFactory
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.BoundsTransform
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.animation.core.Spring.StiffnessLow
import androidx.compose.animation.core.spring
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.rounded.FavoriteBorder
import androidx.compose.material.icons.rounded.Notifications
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
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
import com.example.recipes.components.BoxImageColorBackground
import com.example.recipes.components.ButtonWithIcon
import com.example.recipes.components.ImageTextRow
import com.example.recipes.components.TransitionScopeImg
import com.example.recipes.data.MealRepository
import com.example.recipes.data.UserData
import com.example.recipes.model.ArticlesModel
import com.example.recipes.model.MealModel
import com.example.recipes.ui.theme.RecipesTheme

@OptIn(ExperimentalSharedTransitionApi::class)
val boundsTransform = BoundsTransform { initialBounds, targetBounds ->
    spring(stiffness = StiffnessLow, dampingRatio = 0.65f)
}

@OptIn(ExperimentalSharedTransitionApi::class)
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun HomeScreen(sharedTransitionScope: SharedTransitionScope, animatedContentScope: AnimatedContentScope, navController: NavHostController, navBackStackEntry: NavBackStackEntry, modifier: Modifier = Modifier, mealRepository: MealRepository,
               homeViewModel: HomeViewModel = viewModel(
                   factory = HomeViewModelFactory(mealRepository)
               )) {
        Scaffold(
            modifier = modifier
                .fillMaxSize(),
            containerColor = MaterialTheme.colorScheme.background
        ) { innerPadding ->
            val homeUiState by homeViewModel.uiState.collectAsState()
            FullHomeScreen(homeUiState.meals, homeUiState.category, homeUiState.categories, homeViewModel, homeUiState.searchString, homeUiState.articles, sharedTransitionScope, animatedContentScope, navController, navBackStackEntry, modifier = Modifier.padding(innerPadding))
        }
}

fun navigateToRecipe(navController: NavHostController, mealNo: Int) {
    navController.navigate(Screen.Recipe.createRoute(mealNo - 1))
}

fun navigateToArticle(navController: NavHostController, articleNo: Int) {
    navController.navigate(Screen.Article.createRoute(articleNo))
}

@OptIn(ExperimentalSharedTransitionApi::class)
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun FullHomeScreen(meals: List<MealModel>, currentCat: Int, categories: Set<Int>, homeViewModel: HomeViewModel, searchString: String, articles: List<ArticlesModel>, sharedTransitionScope: SharedTransitionScope, animatedContentScope: AnimatedContentScope, navController: NavHostController, navBackStackEntry: NavBackStackEntry, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colorScheme.background),
    ) {
        TopNavBar()
        SearchSection(homeViewModel, searchString)
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            verticalArrangement = Arrangement.spacedBy(2.dp)
        ) {
            item {
                RecipesSection(meals, currentCat, categories, homeViewModel, sharedTransitionScope, animatedContentScope, navController)
            }
            item {
                ArticleSection(articles, sharedTransitionScope, animatedContentScope, navController)
            }
        }
        BottomNavSection(navBackStackEntry, navController)
    }
}

@Composable
fun TopNavBar(modifier: Modifier = Modifier) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(start = 18.dp, end = 18.dp, bottom = 10.dp, top = 30.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceAround
    ) {
        BoxImage(50.dp, RoundedCornerShape(25.dp), UserData.getUsers()[0].img)
        Text(
            text = stringResource(R.string.greeting, stringResource(UserData.getUsers()[0].name)),
            style = MaterialTheme.typography.headlineSmall,
            color = MaterialTheme.colorScheme.onBackground,
            fontWeight = FontWeight.ExtraBold,
            textAlign = TextAlign.Start,
            modifier = Modifier
                .weight(1f)
                .padding(horizontal = 14.dp)
        )
        Box(
            modifier = Modifier
                .size(28.dp)
                .clip(shape = RoundedCornerShape(5.dp))
                .background(color = MaterialTheme.colorScheme.surfaceContainer),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = Icons.Rounded.Notifications,
                contentDescription = null,
                modifier = Modifier
                    .size(23.dp),
                tint = MaterialTheme.colorScheme.onSurface
            )
            Box(
                modifier = Modifier
                    .size(10.dp)
                    .background(color = colorResource(R.color.btn), shape = CircleShape)
                    .align(Alignment.TopEnd)
                    .padding(10.dp),
            )
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun SearchSection(homeViewModel: HomeViewModel, searchString: String, modifier: Modifier = Modifier) {
    val context = LocalContext.current
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(start = 18.dp, end = 18.dp, bottom = 10.dp, top = 20.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(15.dp)
    ) {
        Box(
            modifier = Modifier
                .weight(1f)
                .height(40.dp)
                .clip(shape = RoundedCornerShape(10.dp))
                .background(
                    color = MaterialTheme.colorScheme.surfaceContainer,
                    shape = RoundedCornerShape(10.dp)
                ),
            contentAlignment = Alignment.CenterStart,
        ) {
            if (searchString == "") {
                Text(
                    text = "Search Recipe",
                    color = MaterialTheme.colorScheme.secondary,
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier
                        .padding(horizontal = 10.dp)
                        .clip(shape = RoundedCornerShape(10.dp))
                )
            }
            BasicTextField(
                value = searchString,
                onValueChange = { homeViewModel.searchItems(it, context) },
                singleLine = true,
                textStyle = MaterialTheme.typography.bodyMedium.copy(
                    color = MaterialTheme.colorScheme.secondary
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.CenterStart)
                    .clip(shape = RoundedCornerShape(10.dp))
                    .padding(horizontal = 10.dp)
            )
        }
        ButtonWithIcon(Icons.Rounded.Search, { homeViewModel.searchItems(searchString, context) }, tint = MaterialTheme.colorScheme.onSurface, bgColor = colorResource(R.color.btn))
    }
}

@Composable
fun CategoryBtn(category: Int, currentCat: Int, homeViewModel: HomeViewModel, modifier: Modifier = Modifier) {
    Button(
        onClick = { homeViewModel.filterRecipes(category) },
        modifier = modifier
            .padding(end = 5.dp)
            .height(35.dp),
        colors = ButtonColors(
            containerColor = if (stringResource(category) == stringResource(currentCat)) {
                colorResource(R.color.btn)
            } else {
                MaterialTheme.colorScheme.surfaceContainer
            },
            contentColor = MaterialTheme.colorScheme.onSurface,
            disabledContainerColor = MaterialTheme.colorScheme.onSurface,
            disabledContentColor = MaterialTheme.colorScheme.onSurface
        )
    ) {
        Text(
            text = stringResource(category)
        )
    }
}

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun RecipeCard(sharedTransitionScope: SharedTransitionScope, animatedContentScope: AnimatedContentScope, navController: NavHostController, index: Int, meal: MealModel, homeViewModel: HomeViewModel, modifier: Modifier = Modifier) {
    val icon = if (meal.liked) Icons.Filled.Favorite else Icons.Rounded.FavoriteBorder

    ElevatedCard(
        onClick = { navigateToRecipe(navController, index) },
        modifier = modifier
            .width(155.dp)
            .height(210.dp)
            .padding(end = 10.dp)
            .clip(shape = RoundedCornerShape(15.dp)),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 10.dp,
            draggedElevation = 20.dp,
            pressedElevation = 20.dp
        )
    ) {
        Box(
            modifier = Modifier.fillMaxSize()
        ) {
            BoxImageColorBackground(meal.img1, RoundedCornerShape(15.dp), meal.id, sharedTransitionScope, animatedContentScope, modifier
                .width(155.dp)
                .height(210.dp)
                .background(Color.Black)
                .clip(shape = RoundedCornerShape(15.dp)))
            ButtonWithIcon(
                icon,
                { homeViewModel.likeItem(meal.id) },
                Modifier.align(alignment = Alignment.TopEnd),
                bgColor = Color.Transparent,
                tint = if (meal.liked) Color.Green else Color.White)
            Column(
                modifier = Modifier
                    .align(alignment = Alignment.BottomStart)
                    .fillMaxWidth()
                    .padding(start = 10.dp, bottom = 10.dp, end = 5.dp),
                horizontalAlignment = Alignment.Start,
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                with(sharedTransitionScope) {
                    Text(
                        text = stringResource(meal.name),
                        style = MaterialTheme.typography.labelLarge,
                        color = Color.White,
                        fontWeight = FontWeight.ExtraBold,
                        modifier = Modifier
                            .sharedElement(
                                sharedContentState = rememberSharedContentState(key = "title-${meal.id}"),
                                animatedVisibilityScope = animatedContentScope,
                                boundsTransform = boundsTransform
                            )
                    )
                    Row(
                        modifier = Modifier
                            .height(20.dp)
                            .sharedElement(
                                sharedContentState = rememberSharedContentState(key = "row-${meal.id}"),
                                animatedVisibilityScope = animatedContentScope,
                                boundsTransform = boundsTransform
                            )
                        ,
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(5.dp)
                    ) {
                        ImageTextRow(R.drawable.schedule_24dp_e3e3e3_fill0_wght400_grad0_opsz24, meal.time)
                        ImageTextRow(R.drawable.mood_24dp_e3e3e3_fill0_wght400_grad0_opsz24, meal.servings)
                    }
                }
            }
        }

    }
}

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun ArticleCard(sharedTransitionScope: SharedTransitionScope, animatedContentScope: AnimatedContentScope, navController: NavHostController, index: Int, article: ArticlesModel, modifier: Modifier = Modifier) {
    ElevatedCard(
        onClick = { navigateToArticle(navController, index) },
        modifier = modifier
            .width(255.dp)
            .height(195.dp)
            .padding(end = 10.dp)
            .clip(shape = RoundedCornerShape(15.dp)),
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .clip(shape = RoundedCornerShape(15.dp))
        ) {
            with(sharedTransitionScope) {
                TransitionScopeImg(
                    article.img1,
                    modifier
                        .width(255.dp)
                        .weight(1f)
                        .clip(shape = RoundedCornerShape(topStart = 15.dp, topEnd = 15.dp))
                        .sharedElement(
                            sharedContentState = rememberSharedContentState(key = "articleHeader-${index}"),
                            animatedVisibilityScope = animatedContentScope,
                            boundsTransform = boundsTransform
                        )
                )
            }

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(color = MaterialTheme.colorScheme.surfaceContainer)
                    .padding(horizontal = 13.dp, vertical = 8.dp)
            ) {
                with(sharedTransitionScope) {
                    Text(
                        text = stringResource(article.name),
                        style = MaterialTheme.typography.bodyMedium,
                        fontWeight = FontWeight.Bold,
                        maxLines = 1,
                        modifier = Modifier.sharedElement(
                            sharedContentState = rememberSharedContentState(key = "articleTitle-${index}"),
                            animatedVisibilityScope = animatedContentScope,
                            boundsTransform = boundsTransform
                        )
                    )
                    Text(
                        text = stringResource(article.desc),
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        maxLines = 2,
                        modifier = Modifier.sharedElement(
                            sharedContentState = rememberSharedContentState(key = "desc-${index}"),
                            animatedVisibilityScope = animatedContentScope,
                            boundsTransform = boundsTransform
                        )
                    )
                }
            }
        }

    }
}

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun RecipesSection(meals: List<MealModel>, currentCat: Int, categories: Set<Int>, homeViewModel: HomeViewModel, sharedTransitionScope: SharedTransitionScope, animatedContentScope: AnimatedContentScope, navController: NavHostController, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(start = 18.dp, end = 1.dp, bottom = 10.dp, top = 15.dp),
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        Text(
            text = stringResource(R.string.categories_title),
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.ExtraBold
        )

        LazyRow(
            modifier = Modifier
        ) {

            items(categories.toList()) { category ->
                CategoryBtn(category, currentCat, homeViewModel)
            }
        }
        LazyRow() {
            itemsIndexed(meals) { index, meal ->
                RecipeCard(sharedTransitionScope, animatedContentScope, navController, meal.id, meal, homeViewModel)
            }
        }

    }
}

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun ArticleSection(articles: List<ArticlesModel>, sharedTransitionScope: SharedTransitionScope, animatedContentScope: AnimatedContentScope, navController: NavHostController, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(start = 18.dp, end = 1.dp, bottom = 10.dp, top = 5.dp),
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        Text(
            text = "Articles",
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.ExtraBold
        )

        LazyRow() {
            itemsIndexed(articles) { index, article ->
                ArticleCard(sharedTransitionScope, animatedContentScope, navController, index, article)
            }
        }

    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    RecipesTheme(darkTheme = true) {
//        HomeScreen()
    }
}