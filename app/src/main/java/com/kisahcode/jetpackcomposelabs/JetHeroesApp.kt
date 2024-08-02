package com.kisahcode.jetpackcomposelabs

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.SearchBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.kisahcode.jetpackcomposelabs.data.HeroRepository
import com.kisahcode.jetpackcomposelabs.model.HeroesData
import com.kisahcode.jetpackcomposelabs.ui.theme.JetpackComposeLabsTheme
import kotlinx.coroutines.launch

/**
 * Composable function that displays a list of heroes using a lazy column.
 *
 * This function creates a box layout that contains a LazyColumn to display a list of heroes.
 * Each hero is represented by a [HeroListItem] composable that shows the hero's name and photo.
 * The list of heroes is provided by the [JetHeroesViewModel], and it is grouped by the initial
 * letter of each hero's name. A "scroll to top" button is shown when the user scrolls down the
 * list. The list includes sticky headers for each group of heroes and a search bar for filtering
 * the list of heroes based on the search query.
 *
 * @param modifier Modifier to be applied to the layout. Defaults to [Modifier].
 * @param viewModel The ViewModel that provides the list of heroes. Defaults to an instance of
 *                  [JetHeroesViewModel] created with a [ViewModelFactory] that uses [HeroRepository].
 */
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun JetHeroesApp(
    modifier: Modifier = Modifier,
    viewModel: JetHeroesViewModel = viewModel(factory = ViewModelFactory(HeroRepository()))
) {
    val groupedHeroes by viewModel.groupedHeroes.collectAsState()
    val query by viewModel.query

    Box(modifier = modifier) {
        val scope = rememberCoroutineScope()
        val listState = rememberLazyListState()
        val showButton: Boolean by remember {
            derivedStateOf { listState.firstVisibleItemIndex > 0 }
        }

        LazyColumn(
            state = listState,
            contentPadding = PaddingValues(bottom = 80.dp)
        ) {
            item {
                SearchBar(
                    query = query,
                    onQueryChange = viewModel::search,
                    modifier = Modifier.background(MaterialTheme.colorScheme.primary)
                )
            }
            groupedHeroes.forEach { (initial, heroes) ->
                stickyHeader { 
                    CharacterHeader(char = initial)
                }
                items(heroes, key = { it.id }) { hero ->
                    HeroListItem(
                        name = hero.name,
                        photoUrl = hero.photoUrl,
                        modifier = Modifier
                            .fillMaxWidth()
                            .animateItemPlacement(tween(durationMillis = 100))
                    )
                }
            }
        }

        AnimatedVisibility(
            visible = showButton,
            enter = fadeIn() + slideInVertically(),
            exit = fadeOut() + slideOutVertically(),
            modifier = Modifier
                .padding(bottom = 30.dp)
                .align(Alignment.BottomCenter)
        ) {
            ScrollToTopButton(
                onClick = {
                    scope.launch {
                        listState.scrollToItem(index = 0)
                    }
                }
            )
        }
    }
}

/**
 * Composable function that displays a hero's name and photo in a row.
 *
 * This function creates a row layout with the hero's photo on the left and their name on the right.
 * The photo is displayed as a circular image, and the name is displayed as a text.
 *
 * @param name The name of the hero to be displayed.
 * @param photoUrl The URL of the hero's photo to be displayed.
 * @param modifier Modifier to be applied to the layout. Defaults to [Modifier].
 */
@Composable
fun HeroListItem(
    name: String,
    photoUrl: String,
    modifier: Modifier = Modifier
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier.clickable {}
    ) {
        AsyncImage(
            model = photoUrl,
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .padding(8.dp)
                .size(60.dp)
                .clip(CircleShape)
        )
        Text(
            text = name,
            fontWeight = FontWeight.Medium,
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .padding(start = 16.dp)
        )
    }
}

/**
 * A composable function that displays a button to scroll to the top of a list.
 *
 * This function creates a button using the FilledTonalButton composable. The button includes an
 * upward-facing arrow icon and a click action.
 *
 * @param onClick The action to be performed when the button is clicked.
 * @param modifier Modifier to be applied to the button layout.
 */
@Composable
fun ScrollToTopButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    FilledTonalButton(
        onClick = onClick,
        modifier = modifier
    ) {
        Icon(
            imageVector = Icons.Filled.KeyboardArrowUp,
            contentDescription = stringResource(R.string.scroll_to_top),
        )
    }
}

/**
 * Composable function that displays a character header.
 *
 * This function creates a [Surface] with a primary background color and displays a character
 * in a centered [Text] composable. It is used to represent section headers in a list, such as
 * the first letter of a group of items.
 *
 * @param char The character to be displayed in the header.
 * @param modifier Modifier to be applied to the layout. Defaults to [Modifier].
 */
@Composable
fun CharacterHeader(
    char: Char,
    modifier: Modifier = Modifier
) {
    Surface(
        color = MaterialTheme.colorScheme.primary,
        modifier = modifier
    ) {
        Text(
            text = char.toString(),
            fontWeight = FontWeight.Black,
            color = Color.White,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        )
    }
}

/**
 * Composable function that displays a search bar.
 *
 * This function creates a search bar with a leading search icon and a placeholder text.
 * The search bar allows the user to input a search query, and it provides callbacks for query changes.
 * The design and layout of the search bar are customizable through the [modifier] parameter.
 *
 * @param query The current search query string.
 * @param onQueryChange Callback function to be invoked when the search query changes.
 * @param modifier Modifier to be applied to the layout. Defaults to [Modifier].
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchBar(
    query: String,
    onQueryChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    SearchBar(
        query = query,
        onQueryChange = onQueryChange,
        onSearch = {},
        active = false,
        onActiveChange = {},
        leadingIcon = {
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.onSurfaceVariant
            )
        },
        placeholder = {
            Text(stringResource(R.string.search_hero))
        },
        shape = MaterialTheme.shapes.large,
        modifier = modifier
            .padding(16.dp)
            .fillMaxWidth()
            .heightIn(min = 48.dp)
    ) {
    }
}

/**
 * A preview composable function for the JetHeroesApp.
 *
 * This function sets up a preview environment for the JetHeroesApp composable using the
 * JetpackComposeLabsTheme.
 */
@Preview(showBackground = true)
@Composable
fun JetHeroesAppPreview() {
    JetpackComposeLabsTheme {
        JetHeroesApp()
    }
}

/**
 * A preview composable function for the HeroListItem.
 *
 * This function sets up a preview environment for the HeroListItem composable using the
 * JetpackComposeLabsTheme.
 */
@Preview(showBackground = true)
@Composable
fun HeroListItemPreview() {
    JetpackComposeLabsTheme {
        HeroListItem(
            name = "H.O.S. Cokroaminoto",
            photoUrl = ""
        )
    }
}
