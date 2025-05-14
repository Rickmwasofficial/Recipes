package com.example.recipes

import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionLayout
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.example.recipes.ui.theme.RecipesTheme
import androidx.navigation.compose.composable
import androidx.navigation.NavType
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.navArgument
import com.example.recipes.components.BottomNavSection

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

class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            RecipesTheme {
                NavigationStack()
            }
        }
    }
}


@OptIn(ExperimentalSharedTransitionApi::class)
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun NavigationStack(modifier: Modifier = Modifier) {
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
        NavigationStack()
    }
}