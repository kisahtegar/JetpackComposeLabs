package com.kisahcode.jetpackcomposelabs

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.kisahcode.jetpackcomposelabs.ui.components.Search
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
 * Currently, it contains a Column with a Banner composable.
 *
 * @param modifier Modifier to be applied to the Column.
 */
@Composable
fun JetCoffeeApp(modifier: Modifier = Modifier) {
    Column {
        Banner()
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