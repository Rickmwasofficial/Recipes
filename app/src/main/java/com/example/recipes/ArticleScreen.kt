package com.example.recipes

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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowBack
import androidx.compose.material.icons.rounded.FavoriteBorder
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection
import androidx.compose.ui.input.nestedscroll.NestedScrollSource
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.recipes.data.ArticleData.getArticles
import com.example.recipes.data.MealData.getMeals
import com.example.recipes.model.ArticlesModel
import com.example.recipes.model.MealModel

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun ArticleScreen(sharedTransitionScope: SharedTransitionScope, animatedContentScope: AnimatedContentScope, navController: NavHostController, text: Int?, modifier: Modifier = Modifier) {
    val currentArticle = getArticles()[text!!]
    Surface(
        modifier = modifier
            .fillMaxSize()
    ) {
        var currentArticleImageSize by remember { mutableStateOf(480.dp) }

        val nestedScrollConnection = remember {
            object : NestedScrollConnection {
                override fun onPreScroll(available: Offset, source: NestedScrollSource): Offset {
                    val delta = available.y
                    val newImageSize = currentArticleImageSize + delta.dp

                    // Constrain the image size within the allowed bounds
                    currentArticleImageSize = newImageSize.coerceIn(300.dp, 480.dp)

                    // Return the consumed scroll amount
                    return Offset.Zero
                }
            }
        }
        Box(Modifier.fillMaxSize().nestedScroll(nestedScrollConnection)) {
            ArticleDesc(currentArticleImageSize, sharedTransitionScope, animatedContentScope, navController = navController, article = currentArticle, index = text)

            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .offset {
                        IntOffset(0, currentArticleImageSize.roundToPx())
                    }
                    .padding(bottom = 50.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                item {
                    Article(article = currentArticle)
                }
            }
        }

    }
}

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun ArticleDesc(imageHeight: Dp, sharedTransitionScope: SharedTransitionScope, animatedContentScope: AnimatedContentScope, navController: NavHostController, index: Int, modifier: Modifier = Modifier, article: ArticlesModel = getArticles()[0]) {
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
            Box(
                modifier = modifier
                    .fillMaxSize()
                    .background(MaterialTheme.colorScheme.surfaceContainer),
            )
            with(sharedTransitionScope) {
                Image(
                    painter = painterResource(article.img1),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    alignment = Alignment.CenterStart,
                    alpha = 0.6f,
                    modifier = Modifier
                        .fillMaxSize()
                        .sharedElement(
                            sharedContentState = rememberSharedContentState(key = "articleHeader-${index}"),
                            animatedVisibilityScope = animatedContentScope,
                            boundsTransform = boundsTransform
                        )
                        .clip(RoundedCornerShape(topStart = 0.dp, topEnd = 0.dp, bottomEnd = 16.dp, bottomStart = 16.dp))
                )
            }
            Box(
                modifier = modifier
                    .fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(imageHeight - 50.dp)
                        .padding(horizontal = 23.dp),
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
                        IconButton(
                            onClick = { navController.popBackStack() },
                            modifier = Modifier
                                .background(
                                    MaterialTheme.colorScheme.surfaceContainer,
                                    shape = RoundedCornerShape(10.dp)
                                )
                                .height(40.dp)
                        ) {
                            Icon(
                                imageVector = Icons.AutoMirrored.Rounded.ArrowBack,
                                contentDescription = null,
                                tint =  Color.White
                            )
                        }
                        IconButton(
                            onClick = {  },
                            modifier = Modifier
                                .background(
                                    MaterialTheme.colorScheme.surfaceContainer,
                                    shape = RoundedCornerShape(10.dp)
                                )
                                .height(40.dp)
                        ) {
                            Icon(
                                imageVector = Icons.Rounded.FavoriteBorder,
                                contentDescription = null,
                                tint =  Color.White
                            )
                        }
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
                                text = stringResource(article.name),
                                style = MaterialTheme.typography.headlineLarge,
                                color = Color.White,
                                fontWeight = FontWeight.ExtraBold,
                                modifier = Modifier
                                    .sharedElement(
                                        sharedContentState = rememberSharedContentState(key = "articleTitle-${index}"),
                                        animatedVisibilityScope = animatedContentScope,
                                        boundsTransform = boundsTransform
                                    )
                                    .width(250.dp)
                            )
                            Text(
                                text = stringResource(article.desc),
                                style = MaterialTheme.typography.bodyMedium,
                                color = Color.White,
                                fontWeight = FontWeight.SemiBold,
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
    }
}


@Composable
fun Article(article: ArticlesModel, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(start = 18.dp, end = 1.dp, bottom = 10.dp, top = 15.dp),
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        Text(
            text = stringResource(article.content),
            style = MaterialTheme.typography.bodyMedium
        )
        Button(
            onClick = {},
            modifier = Modifier
                .fillMaxWidth()
                .padding(end = 10.dp),
            colors = ButtonColors(
                containerColor = colorResource(R.color.btn),
                contentColor = Color.White,
                disabledContentColor = Color.White,
                disabledContainerColor = colorResource(R.color.btn)
            ),
            shape = RoundedCornerShape(8.dp)
        ) {
            Text(
                text = "Add To Favourites",
                style = MaterialTheme.typography.bodyLarge
            )
        }
    }
}
