package com.example.recipes

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.WindowManager
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionLayout
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import kotlinx.coroutines.delay
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.recipes.data.ArticleData.getArticles
import com.example.recipes.data.MealData.getMeals
import com.example.recipes.model.ArticlesModel
import com.example.recipes.model.MealModel
import com.example.recipes.ui.theme.RecipesTheme

sealed class Screen(val route: String) {
    object Home: Screen("homeScreen")
    object Recipe : Screen("detailScreen/{text}") {
        fun createRoute(text: Int): String = "detailScreen/$text"
    }
    object Article : Screen("articleScreen/{text}") {
        fun createRoute(text: Int): String = "articleScreen/$text"
    }
    object Fav: Screen("favScreen")
    object Prof: Screen("profileScreen")
}

const val TAG = "MainActivity"


// MainActivity.kt
class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)

        // Used to track when the UI is ready
        var uiReady = false

        setContent {
            RecipesTheme {// Safely extract parcelable extras with null handling
                val meals = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                    intent.getParcelableArrayListExtra("meals_list", MealModel::class.java)
                } else {
                    @Suppress("DEPRECATION")
                    intent.getParcelableArrayListExtra<MealModel>("meals_list")
                }
                val articles = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                    intent.getParcelableArrayListExtra("articles_list", ArticlesModel::class.java)
                } else {
                    @Suppress("DEPRECATION")
                    intent.getParcelableArrayListExtra<ArticlesModel>("articles_list")
                }

                val context = LocalContext.current

                // This tracks when composition has completed
                LaunchedEffect(Unit) {
                    Log.d(TAG, "Starting resource loading...")

                    // Mark UI as ready
                    uiReady = true

                    Log.d(TAG, "UI is ready!")

                    // Send broadcast to finish splash only when UI is truly ready
                    val finishIntent = Intent("finish_splash")
                    context.sendBroadcast(finishIntent)
                    Log.d(TAG, "Broadcast sent to finish splash screen")
                }

                // Use null-safe operators to safely handle potentially null extras
                val mealsList = meals?.toList() ?: emptyList()
                val articlesList = articles?.toList() ?: emptyList()

                NavigationStack(mealsList, articlesList)
            }
        }
    }
}

@OptIn(ExperimentalSharedTransitionApi::class)
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun NavigationStack(meals: List<MealModel>, articles: List<ArticlesModel>, modifier: Modifier = Modifier) {
    val navController = rememberNavController()
    val navBackStackEntry = navController.currentBackStackEntryAsState().value
    SharedTransitionLayout {
        NavHost(
            navController = navController,
            startDestination = Screen.Home.route,
        ) {
            composable(
                route = Screen.Home.route,
            ) {
                if (navBackStackEntry != null) {
                    HomeScreen(
                        meals,
                        articles,
                        this@SharedTransitionLayout,
                        this,
                        navController,
                        navBackStackEntry
                    )
                }
            }
            composable(
                route = Screen.Fav.route,
            ) {
                if (navBackStackEntry != null) {
                    FavoriteScreen(navBackStackEntry, navController)
                }
            }
            composable(
                route = Screen.Prof.route,
            ) {
                if (navBackStackEntry != null) {
                    ProfileScreen(navBackStackEntry, navController)
                }
            }
            composable(
                route = Screen.Recipe.route,
                arguments = listOf(
                    navArgument("text") {
                        type = NavType.IntType
                        nullable = false
                    }
                )
            ) { backStackEntry ->
                val text = backStackEntry.arguments?.getInt("text")
                RecipeScreen(
                    this@SharedTransitionLayout,
                    this,
                    navController = navController,
                    text = text)
            }
            composable(
                route = Screen.Article.route,
                arguments = listOf(
                    navArgument("text") {
                        type = NavType.IntType
                        nullable = false
                    }
                )
            ) { backStackEntry ->
                val text = backStackEntry.arguments?.getInt("text")
                ArticleScreen(
                    this@SharedTransitionLayout,
                    this,
                    navController = navController,
                    text = text)
            }
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Preview(showBackground = true)
@Composable
fun RecipePreview() {
    RecipesTheme(darkTheme = true) {
//        NavigationStack()
    }
}