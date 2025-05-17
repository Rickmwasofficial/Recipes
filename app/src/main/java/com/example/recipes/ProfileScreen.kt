package com.example.recipes

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideIn
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalWindowInfo
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavHostController
import com.example.recipes.components.BottomNavSection
import com.example.recipes.data.UserData.getUsers
import com.example.recipes.model.ProfileCardsModel
import com.example.recipes.ui.theme.RecipesTheme
import kotlinx.coroutines.delay
import kotlin.math.roundToInt

@Composable
fun ProfileScreen(navBackStackEntry: NavBackStackEntry, navController: NavHostController, modifier: Modifier = Modifier) {
    Scaffold(
        modifier = modifier.fillMaxSize()
            .background(color = MaterialTheme.colorScheme.background),
    ) { innerPadding ->
        val profileCards: List<ProfileCardsModel> = listOf(
            ProfileCardsModel(Icons.Filled.LocationOn, "My Address"),
            ProfileCardsModel(Icons.Filled.AccountBox, "Account"),
            ProfileCardsModel(Icons.Filled.Notifications, "Notifications"),
            ProfileCardsModel(Icons.Filled.Phone, "Contact Us"),
            ProfileCardsModel(Icons.Filled.Star, "Rate Our App")
        )
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
        ) {
            TopProfile()
            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                verticalArrangement = Arrangement.spacedBy(1.dp)
            ) {
                item {
                    ProfileCard(profileCards.subList(0, 2), 15.dp)
                    ProfileCard(profileCards.subList(2, 5), 1.dp)
                }
            }
            BottomNavSection(navBackStackEntry, navController)
        }
    }
}


@Composable
fun TopProfile(modifier: Modifier = Modifier) {
    val shape = RoundedCornerShape(bottomStart = 30.dp, bottomEnd = 30.dp)
    var isVisible by remember { mutableStateOf(false) }

    // Trigger the animation after a delay to ensure it is noticeable
    LaunchedEffect(Unit) {
        delay(500) // Adjust delay as needed
        isVisible = true
    }
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(270.dp)
            .background(MaterialTheme.colorScheme.surface, shape),
    ) {
        Image(
            painter = painterResource(R.drawable.splashbg),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize().clip(shape),
            alpha = 0.2f
        )
        Column(
            modifier = Modifier.fillMaxWidth().align(Alignment.Center),
            verticalArrangement = Arrangement.spacedBy(10.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            AnimatedVisibility(
                visible = isVisible,
                enter = slideIn(initialOffset = { IntOffset(0, -300) }, animationSpec = tween(durationMillis = 1000, easing = LinearEasing))
            ) {
                Box(
                    modifier = Modifier
                        .size(130.dp)
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(75.dp))
                        .background(brush = Brush.radialGradient(colors = listOf(Color.Green, Color.White), center = Offset(0f, 1f), radius = 305f))
                ) {
                    Image(
                        painter = painterResource(getUsers()[0].img),
                        contentDescription = null,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier.size(120.dp).clip(RoundedCornerShape(60.dp)).align(Alignment.Center)
                    )
                }
            }
            AnimatedVisibility(
                visible = isVisible,
                enter = slideIn(initialOffset = { IntOffset(300, 0) }, animationSpec = tween(durationMillis = 1900, easing = LinearEasing))
            ) {
                Text(
                    text = stringResource(getUsers()[0].name),
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.headlineSmall,
                    color = MaterialTheme.colorScheme.onSurface,
                    fontWeight = FontWeight.ExtraBold,
                    modifier = Modifier.fillMaxWidth()
                )
            }
            AnimatedVisibility(
                visible = isVisible,
                enter = slideIn(initialOffset = { IntOffset(-300, 0) }, animationSpec = tween(durationMillis = 2000, easing = LinearEasing))
            ) {
                Text(
                    text = stringResource(R.string.user1_desc),
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurface,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(horizontal = 70.dp)
                )
            }
        }
    }
}

@Composable
fun CardRow(item: ProfileCardsModel, modifier: Modifier = Modifier) {
    Row(
        modifier = modifier
            .padding(horizontal = 20.dp),
        horizontalArrangement = Arrangement.spacedBy(10.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = item.icon,
            contentDescription = null
        )
        Text(
            text = item.text,
            textAlign = TextAlign.Start,
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurface,
            modifier = Modifier.padding(horizontal = 10.dp).weight(1f)
        )
        Icon(
            imageVector = item.icon2,
            contentDescription = null
        )
    }
}

@Composable
fun ProfileCard(items: List<ProfileCardsModel>, verticalPadding: Dp, modifier: Modifier = Modifier) {
    Card(
        modifier = modifier
            .padding(horizontal = 20.dp, vertical = verticalPadding)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(vertical = 20.dp),
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            for (item in items) {
                CardRow(item)
                if (items.indexOf(item) + 1 != items.size) {
                    Spacer(modifier = Modifier.height(1.dp).fillMaxWidth().border(1.dp, color = Color.White))
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ProfileScreenPreview() {
    RecipesTheme(darkTheme = true) {
    }
}
