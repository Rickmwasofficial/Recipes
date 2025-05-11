package com.example.recipes

import android.annotation.SuppressLint
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.rounded.Notifications
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ExperimentalMaterial3Api
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.LineHeightStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.res.TypedArrayUtils.getString
import com.example.recipes.data.MealData.getMeals
import com.example.recipes.data.UserData
import com.example.recipes.ui.theme.RecipesTheme
import java.time.format.TextStyle

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun HomeScreen(modifier: Modifier = Modifier) {
        Scaffold(
            modifier = modifier
                .fillMaxSize()
                .background(color = MaterialTheme.colorScheme.background)
        ) { innerPadding ->
            FullHomeScreen(modifier = Modifier.padding(innerPadding))
        }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun FullHomeScreen(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colorScheme.background),
    ) {
        TopNavBar()
        SearchSection()
        RecipesSection()
        ArticleSection()
        BottomNavSection()
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
                .border(1.dp, MaterialTheme.colorScheme.onBackground,shape = RoundedCornerShape(25.dp))
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
@OptIn(ExperimentalMaterial3Api::class)
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
                .background(color = MaterialTheme.colorScheme.surfaceContainer, shape = RoundedCornerShape(10.dp)),
            contentAlignment = Alignment.CenterStart,
        ) {
            if (true) {
                Text(
                    text = "Search Contact",
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
        modifier = Modifier.padding(end = 5.dp),
        colors = ButtonColors(
            containerColor = if (stringResource(category) == "Main Course") {
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

@Composable
fun RecipesSection(modifier: Modifier = Modifier) {
    val meals = getMeals()
    val mealsCat = meals.map { it.category }.toSet()
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(start = 18.dp, end = 1.dp, bottom = 10.dp, top = 20.dp),
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        Text(
            text = "Categories",
            style = MaterialTheme.typography.bodyLarge
        )

        LazyRow(
            modifier = Modifier
        ) {
            items(mealsCat.toList()) { category ->
                CategoryBtn(category)
            }
        }
        LazyRow() {

        }

    }
}

@Composable
fun ArticleSection(modifier: Modifier = Modifier) {

}

@Composable
fun BottomNavSection(modifier: Modifier = Modifier) {

}

@RequiresApi(Build.VERSION_CODES.O)
@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    RecipesTheme(darkTheme = true) {
        HomeScreen()
    }
}