package com.example.recipes.components

import androidx.annotation.DrawableRes
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.recipes.R
import com.example.recipes.Screen

@Composable
fun IconWithText(@DrawableRes icon: Int?, text: String, isSelected: Boolean, onClick: () -> Unit, modifier: Modifier = Modifier) {
    val targetColor = if (isSelected) colorResource(R.color.btn) else MaterialTheme.colorScheme.onBackground

    val animatedColor by animateColorAsState(
        targetValue = targetColor,
        animationSpec = tween(3000),
        label = "Text Color Animation"
    )

    Column(
        modifier = modifier
            .width(70.dp)
            .height(60.dp)
            .clickable { onClick() }
            .background(color = MaterialTheme.colorScheme.surfaceContainer),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(2.dp),
    ) {
        if (isSelected) {
            HorizontalDivider(
                modifier = Modifier.fillMaxWidth(),
                thickness = 4.dp,
                color = colorResource(R.color.btn)
            )
        }
        Icon(
            painter = painterResource(icon!!),
            contentDescription = null,
            tint = animatedColor,
            modifier = if (isSelected) Modifier.padding(top = 4.dp) else Modifier.padding(top = 10.dp)
        )
        Text(
            text = text,
            style = MaterialTheme.typography.bodySmall,
            color = animatedColor,
            modifier = Modifier
        )
    }
}


fun navigateToPage(navController: NavHostController, route: Screen) {
    navController.navigate(route.route) {
        // Pop up to the start destination of the graph to
        // avoid building up a large stack of destinations
        // on the back stack as users select items
        popUpTo(navController.graph.findStartDestination().id) {
            saveState = true
        }
        // Avoid multiple copies of the same destination when
        // reselecting the same item
        launchSingleTop = true
        // Restore state when reselecting a previously selected item
        restoreState = true
    }
}


@Composable
fun BottomNavSection(navBackStackEntry: NavBackStackEntry, navController: NavHostController, modifier: Modifier = Modifier) {
    val pages = mapOf(
        "Home" to R.drawable.home_24dp_e3e3e3_fill0_wght400_grad0_opsz24,
        "Favorites" to R.drawable.bookmark_24dp_e3e3e3_fill0_wght400_grad0_opsz24,
        "Profile" to R.drawable.person_24dp_e3e3e3_fill0_wght400_grad0_opsz24
    )

    Card(
        modifier = modifier
            .fillMaxWidth(),
        shape = RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp, bottomEnd = 0.dp, bottomStart = 0.dp),
        colors = CardColors(
            containerColor = MaterialTheme.colorScheme.surfaceContainer,
            contentColor = MaterialTheme.colorScheme.onSurface,
            disabledContainerColor = MaterialTheme.colorScheme.surfaceContainer,
            disabledContentColor = MaterialTheme.colorScheme.surfaceContainer
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 10.dp
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceAround,
        ) {
            IconWithText(pages["Home"], "Home", navBackStackEntry.destination.route == Screen.Home.route, { navigateToPage(navController, Screen.Home) })
            IconWithText(pages["Favorites"], "Favorites", navBackStackEntry.destination.route == Screen.Fav.route, { navigateToPage(navController, Screen.Fav) })
            IconWithText(pages["Profile"], "Profile", navBackStackEntry.destination.route == Screen.Prof.route, { navigateToPage(navController, Screen.Prof) })
        }
    }
}
