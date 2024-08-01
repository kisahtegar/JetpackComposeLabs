package com.kisahcode.jetpackcomposelabs

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.kisahcode.jetpackcomposelabs.model.HeroesData
import com.kisahcode.jetpackcomposelabs.ui.theme.JetpackComposeLabsTheme

/**
 * Composable function that displays a list of heroes using a lazy column.
 *
 * This function creates a box layout that contains a LazyColumn to display a list of heroes.
 * Each hero is represented by a [HeroListItem] composable that shows the hero's name and photo.
 * The list of heroes is provided by the [HeroesData] object.
 *
 * @param modifier Modifier to be applied to the layout. Defaults to [Modifier].
 */
@Composable
fun JetHeroesApp(
    modifier: Modifier = Modifier,
) {
    Box(modifier = modifier) {
        LazyColumn {
            items(HeroesData.heroes, key = { it.id }) { hero ->
                HeroListItem(
                    name = hero.name,
                    photoUrl = hero.photoUrl,
                    modifier = Modifier.fillMaxWidth()
                )
            }
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
