package com.example.recipes

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
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
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Delete
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavHostController
import com.example.recipes.components.BottomNavSection
import com.example.recipes.data.MealData.getMeals
import com.example.recipes.model.MealModel
import com.example.recipes.ui.theme.RecipesTheme
import kotlin.math.min

@Composable
fun FavoriteScreen(navBackStackEntry: NavBackStackEntry, navController: NavHostController, modifier: Modifier = Modifier) {
    Scaffold(
        modifier = modifier.fillMaxSize(),
    ) { innerPadding ->
        Favorites(navBackStackEntry, navController, Modifier.padding(innerPadding))
    }
}

@Composable
fun FavoriteCard(mealModel: MealModel, dragEffect: Float, modifier: Modifier = Modifier) {
    Card(
        modifier = modifier
            .padding(10.dp)
            .clip(RoundedCornerShape(10.dp)),
        backgroundColor = MaterialTheme.colorScheme.surfaceContainer.copy(alpha = 1f - dragEffect)
    ) {
        Row(
            modifier = Modifier.fillMaxSize(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            Box(
                modifier = Modifier
                    .size(80.dp)
                    .clip(RoundedCornerShape(topStart = 10.dp, bottomStart = 10.dp))
                    .padding(3.dp),
            ) {
                Image(
                    painter = painterResource(mealModel.img1),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize().clip(RoundedCornerShape(topStart = 10.dp, bottomStart = 10.dp))
                )
            }
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
                            text = stringResource(mealModel.time),
                            style = MaterialTheme.typography.bodySmall,
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
                            text = stringResource(mealModel.servings),
                            style = MaterialTheme.typography.bodySmall,
                            fontWeight = FontWeight.ExtraBold
                        )
                    }
                }
            }
            Box(
                modifier = Modifier
                    .padding(end = 15.dp)
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

@SuppressLint("RememberReturnType")
@Composable
fun Favorites(navBackStackEntry: NavBackStackEntry, navController: NavHostController, modifier: Modifier = Modifier) {
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
            itemsIndexed(getMeals()) { index, meal ->
                val offsetFraction = remember { mutableStateOf(0f) }
                val dragEffect = min(1f, offsetFraction.value * 0.15f)

                FavoriteCard(meal, dragEffect)

                LaunchedEffect(index) {
                    snapshotFlow { index }
                        .collect { visibleIndex ->
                            offsetFraction.value = visibleIndex.toFloat() / getMeals().lastIndex
                        }
                }
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