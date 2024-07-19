package com.kisahcode.jetpackcomposelabs

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.kisahcode.jetpackcomposelabs.model.dummyCategory
import com.kisahcode.jetpackcomposelabs.ui.components.CategoryItem
import com.kisahcode.jetpackcomposelabs.ui.components.Search
import com.kisahcode.jetpackcomposelabs.ui.components.SectionText
import com.kisahcode.jetpackcomposelabs.ui.theme.JetpackComposeLabsTheme

/**
 * MainActivity is the entry point of the application.
 *
 * This activity sets the content of the app to the JetCoffeeApp composable wrapped in the app's theme.
 */
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            JetpackComposeLabsTheme {
                JetCoffeeApp()
            }
        }
    }
}

/**
 * JetCoffeeApp composable function displays the main content of the application.
 *
 * Currently, it contains a Column with a Banner composable, a SectionText composable, and a
 * CategoryRow composable.
 *
 * @param modifier Modifier to be applied to the Column.
 */
@Composable
fun JetCoffeeApp(modifier: Modifier = Modifier) {
    Column {
        Banner()
        SectionText(stringResource(R.string.section_category))
        CategoryRow()
    }
}

/**
 * Banner composable function displays a banner image with a search bar overlaid.
 *
 * The image is displayed at a fixed height of 160.dp and scales to fill the width while maintaining
 * aspect ratio. The search bar is overlaid on top of the image.
 *
 * @param modifier Modifier to be applied to the Box containing the banner image and search bar.
 */
@Composable
fun Banner(modifier: Modifier = Modifier) {
    Box(modifier = modifier) {
        Image(
            painter = painterResource(R.drawable.banner),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.height(160.dp)
        )
        Search()
    }
}

/**
 * CategoryRow composable function displays a row of categories using a LazyRow.
 *
 * Each category is represented by a CategoryItem composable, displaying an image and a text label.
 * The row is horizontally scrollable, with spacing between items and padding around the content.
 *
 * @param modifier Modifier to be applied to the LazyRow.
 */
@Composable
fun CategoryRow(modifier: Modifier = Modifier) {
    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        contentPadding = PaddingValues(horizontal = 16.dp),
        modifier = modifier.padding(16.dp)
    ) {
        items(
            dummyCategory,
            key = {it.textCategory}
        ) { category ->
            CategoryItem(category)
        }
    }
}


/**
 * BannerPreview provides a preview of the Banner composable.
 *
 * This preview is useful for seeing how the Banner composable looks in different configurations
 * in Android Studio.
 */
@Composable
@Preview(showBackground = true)
fun BannerPreview() {
    JetpackComposeLabsTheme {
        Banner()
    }
}

/**
 * CategoryRowPreview provides a preview of the CategoryRow composable.
 *
 * This preview is useful for seeing how the CategoryRow composable looks in different configurations
 * in Android Studio.
 */
@Composable
@Preview(showBackground = true)
fun CategoryRowPreview() {
    JetpackComposeLabsTheme {
        CategoryRow()
    }
}

/**
 * JetCoffeeAppPreview provides a preview of the JetCoffeeApp composable.
 *
 * This preview is useful for seeing how the JetCoffeeApp composable looks in different configurations
 * in Android Studio.
 */
@Preview(showBackground = true, device = Devices.PIXEL_4)
@Composable
fun JetCoffeeAppPreview() {
    JetpackComposeLabsTheme {
        JetCoffeeApp()
    }
}