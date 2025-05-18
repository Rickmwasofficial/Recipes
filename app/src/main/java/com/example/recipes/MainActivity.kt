package com.example.recipes

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionLayout
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.recipes.data.MealRepository
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
        val mealRepository = MealRepository()
        setContent {
            RecipesTheme {
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
                NavigationStack(mealRepository = mealRepository)
            }
        }
    }
}

@OptIn(ExperimentalSharedTransitionApi::class)
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun NavigationStack(modifier: Modifier = Modifier, mealRepository: MealRepository) {
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
                        this@SharedTransitionLayout,
                        this,
                        navController,
                        navBackStackEntry,
                        mealRepository = mealRepository
                    )
                }
            }
            composable(
                route = Screen.Fav.route,
            ) {
                if (navBackStackEntry != null) {
                    FavoriteScreen(navBackStackEntry, navController, mealRepository = mealRepository)
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
                    text = text!!,
                    mealRepository = mealRepository)
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