package com.example.recipes

import android.annotation.SuppressLint
import android.os.Build
import androidx.annotation.DrawableRes
import androidx.annotation.RequiresApi
import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.BoundsTransform
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.animation.core.Spring.StiffnessHigh
import androidx.compose.animation.core.Spring.StiffnessLow
import androidx.compose.animation.core.Spring.StiffnessMediumLow
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
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
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.rounded.FavoriteBorder
import androidx.compose.material.icons.rounded.Notifications
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CardElevation
import androidx.compose.material3.Divider
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.contentColorFor
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.LineHeightStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.res.TypedArrayUtils.getString
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.recipes.components.BottomNavSection
import com.example.recipes.data.ArticleData.getArticles
import com.example.recipes.data.MealData.getMeals
import com.example.recipes.data.UserData
import com.example.recipes.model.ArticlesModel
import com.example.recipes.model.MealModel
import com.example.recipes.ui.theme.RecipesTheme
import java.time.format.TextStyle

@OptIn(ExperimentalSharedTransitionApi::class)
val boundsTransform = BoundsTransform { initialBounds, targetBounds ->
    spring(stiffness = StiffnessLow, dampingRatio = 0.65f)
}

@OptIn(ExperimentalSharedTransitionApi::class)
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun HomeScreen(sharedTransitionScope: SharedTransitionScope, animatedContentScope: AnimatedContentScope, navController: NavHostController, navBackStackEntry: NavBackStackEntry, modifier: Modifier = Modifier) {
        Scaffold(
            modifier = modifier
                .fillMaxSize(),
            containerColor = MaterialTheme.colorScheme.background
        ) { innerPadding ->
            FullHomeScreen(sharedTransitionScope, animatedContentScope, navController, navBackStackEntry, modifier = Modifier.padding(innerPadding))
        }
}

fun navigateToRecipe(navController: NavHostController, mealNo: Int) {
    navController.navigate(Screen.Recipe.createRoute(mealNo))
}

fun navigateToArticle(navController: NavHostController, articleNo: Int) {
    navController.navigate(Screen.Article.createRoute(articleNo))
}

@OptIn(ExperimentalSharedTransitionApi::class)
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun FullHomeScreen(sharedTransitionScope: SharedTransitionScope, animatedContentScope: AnimatedContentScope, navController: NavHostController, navBackStackEntry: NavBackStackEntry, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colorScheme.background),
    ) {
        TopNavBar()
        SearchSection()
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            verticalArrangement = Arrangement.spacedBy(2.dp)
        ) {
            item {
                RecipesSection(sharedTransitionScope, animatedContentScope, navController)
            }
            item {
                ArticleSection(sharedTransitionScope, animatedContentScope, navController)
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
        Box(
            modifier = Modifier
                .size(50.dp)
                .background(color = MaterialTheme.colorScheme.background)
                .border(
                    1.dp,
                    MaterialTheme.colorScheme.onBackground,
                    shape = RoundedCornerShape(25.dp)
                )
        ) {
            Image(
                painter = painterResource(UserData.getUsers()[0].img),
                contentDescription = null,
                contentScale = ContentScale.FillWidth,
                alignment = Alignment.TopCenter,
                modifier = Modifier.clip(shape = RoundedCornerShape(25.dp))
            )
        }
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
            ){

            }
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun SearchSection(modifier: Modifier = Modifier) {
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
            if (true) {
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
                value = "",
                onValueChange = {  },
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
        IconButton(
            onClick = {  },
            modifier = Modifier
                .background(color = colorResource(R.color.btn), shape = RoundedCornerShape(10.dp))
                .height(40.dp)
        ) {
            Icon(
                imageVector = Icons.Rounded.Search,
                contentDescription = null,
                tint =  MaterialTheme.colorScheme.onSurface
            )
        }

    }
}

@Composable
fun CategoryBtn(category: Int, modifier: Modifier = Modifier) {
    Button(
        onClick = {  },
        modifier = modifier
            .padding(end = 5.dp)
            .height(35.dp),
        colors = ButtonColors(
            containerColor = if (stringResource(category) == "All") {
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
fun RecipeCard(sharedTransitionScope: SharedTransitionScope, animatedContentScope: AnimatedContentScope, navController: NavHostController, index: Int, meal: MealModel, modifier: Modifier = Modifier) {
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
            Box(
                modifier = modifier
                    .width(155.dp)
                    .height(210.dp)
                    .background(Color.Black)
                    .clip(shape = RoundedCornerShape(15.dp)),
            )
            with(sharedTransitionScope) {
                Image(
                    painter = painterResource(meal.img1),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    alignment = Alignment.Center,
                    modifier = Modifier
                        .sharedElement(
                            sharedContentState = rememberSharedContentState(key = "header-${index}"),
                            animatedVisibilityScope = animatedContentScope,
                            boundsTransform = boundsTransform
                        )
                        .fillMaxSize()
                        .clip(shape = RoundedCornerShape(15.dp)),
                    alpha = 0.6f,
                )
            }
            IconButton(
                onClick = {  },
                modifier = Modifier
                    .height(40.dp)
                    .align(alignment = Alignment.TopEnd)
            ) {
                Icon(
                    imageVector = Icons.Rounded.FavoriteBorder,
                    contentDescription = null,
                    tint =  Color.White
                )
            }
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
                                sharedContentState = rememberSharedContentState(key = "title-${index}"),
                                animatedVisibilityScope = animatedContentScope,
                                boundsTransform = boundsTransform
                            )
                    )
                    Row(
                        modifier = Modifier
                            .height(20.dp)
                            .sharedElement(
                                sharedContentState = rememberSharedContentState(key = "row-${index}"),
                                animatedVisibilityScope = animatedContentScope,
                                boundsTransform = boundsTransform
                            )
                        ,
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(5.dp)
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(2.dp)
                        ) {
                            Image(
                                painter = painterResource(R.drawable.schedule_24dp_e3e3e3_fill0_wght400_grad0_opsz24),
                                contentDescription = null,
                                modifier = Modifier.size(14.dp)
                            )
                            Text(
                                text = stringResource(meal.time),
                                style = MaterialTheme.typography.bodySmall,
                                color = Color.White,
                                fontWeight = FontWeight.ExtraBold
                            )
                        }
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(2.dp)
                        ) {
                            Image(
                                painter = painterResource(R.drawable.mood_24dp_e3e3e3_fill0_wght400_grad0_opsz24),
                                contentDescription = null,
                                modifier = Modifier.size(14.dp),
                            )
                            Text(
                                text = stringResource(meal.servings),
                                style = MaterialTheme.typography.bodySmall,
                                color = Color.White,
                                fontWeight = FontWeight.ExtraBold
                            )
                        }
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
            modifier = Modifier.fillMaxSize()
        ) {
            with(sharedTransitionScope) {
                Image(
                    painter = painterResource(article.img1),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxWidth()
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
fun RecipesSection(sharedTransitionScope: SharedTransitionScope, animatedContentScope: AnimatedContentScope, navController: NavHostController, modifier: Modifier = Modifier) {
    val meals = getMeals()
    val mealsCat1 = meals.map { it.category }.toSet()
    val mealsCat = mutableSetOf(R.string.all) + mealsCat1
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

            items(mealsCat.toList()) { category ->
                CategoryBtn(category)
            }
        }
        LazyRow() {
            itemsIndexed(meals) { index, meal ->
                RecipeCard(sharedTransitionScope, animatedContentScope, navController, index, meal)
            }
        }

    }
}

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun ArticleSection(sharedTransitionScope: SharedTransitionScope, animatedContentScope: AnimatedContentScope, navController: NavHostController, modifier: Modifier = Modifier) {
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
            itemsIndexed(getArticles()) { index, article ->
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