package com.kisahcode.jetpackcomposelabs

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExpandLess
import androidx.compose.material.icons.outlined.ExpandMore
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kisahcode.jetpackcomposelabs.ui.theme.JetpackComposeLabsTheme

// List of names to be displayed
val sampleName = listOf(
    "Andre",
    "Desta",
    "Parto",
    "Wendy",
    "Komeng",
    "Raffi Ahmad",
    "Andhika Pratama",
    "Vincent Ryan Rompies"
)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            JetpackComposeLabsTheme {
                JetpackComposeLabsApp() // Sets the content to be displayed
            }
        }
    }
}

/**
 * JetpackComposeLabsApp composable function serves as the main entry point for the app's UI.
 *
 * It displays a surface that takes up the entire screen and sets the background color
 * according to the current theme. Within this surface, it calls the GreetingList composable
 * to display a list of greeting cards.
 */
@Composable
fun JetpackComposeLabsApp() {
    // Surface composable to provide a background for the app
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        GreetingList(sampleName)
    }
}

/**
 * JetpackComposeLabsAppPreview composable function provides a preview of the JetpackComposeLabsApp composable.
 *
 * It applies the JetpackComposeLabsTheme to the JetpackComposeLabsApp composable and is annotated
 * with @Preview to enable previews in the Android Studio design tool. Two previews are provided:
 * one for the default light theme and one for the dark theme.
 */
@Preview(showBackground = true, device = Devices.PIXEL_4)
@Preview(showBackground = true, device = Devices.PIXEL_4, uiMode = UI_MODE_NIGHT_YES)
@Composable
fun JetpackComposeLabsAppPreview() {
    // Apply the JetpackComposeLabsTheme to the preview
    JetpackComposeLabsTheme {
        // Call the main app composable to display its preview
        JetpackComposeLabsApp()
    }
}

/**
 * GreetingList composable function displays a list of greeting cards for a given list of names.
 *
 * If the list of names is not empty, it displays a lazy column of greeting cards.
 * If the list is empty, it displays a message indicating no people to greet.
 *
 * @param names The list of names to be displayed in the greeting cards.
 */
@Composable
fun GreetingList(names: List<String>) {
    // Check if the list of names is not empty
    if (names.isNotEmpty()) {
//        Column {
//            for (name in names) {
//                Greeting(name)
//            }
//        }
        LazyColumn {
            items(names) { name ->
                Greeting(name)
            }
        }
    } else {
        Box(contentAlignment = Alignment.Center) {
            Text("No people to greet :(")
        }
    }
}

/**
 * Greeting composable function displays a greeting card for a given name.
 *
 * The card includes an image, a greeting message, a welcome message, and an icon button
 * to expand or collapse the card. The image size is animated based on the expansion state.
 *
 * @param name The name to be displayed in the greeting message.
 */
@Composable
fun Greeting(name: String) {
    // State to track if the card is expanded or not
    var isExpanded by remember { mutableStateOf(false) }

    /**
     * Animate the size of the image based on the expansion state.
     * If the card is expanded, the image size will be 120.dp, otherwise 80.dp.
     */
    val animatedSizeDp by animateDpAsState(
        targetValue = if (isExpanded) 120.dp else 80.dp,
        animationSpec = spring(
            dampingRatio = Spring.DampingRatioMediumBouncy,
            stiffness = Spring.StiffnessLow
        ), label = ""
    )

    // Card composable to hold the greeting content
    Card(
        shape = MaterialTheme.shapes.medium,
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer,
        ),
        modifier = Modifier.padding(vertical = 4.dp, horizontal = 8.dp)
    ) {
        // Row composable to arrange the image, text, and icon horizontally
        Row(
            modifier = Modifier.padding(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Image composable to display a picture (Jetpack Compose logo)
            Image(
                painter = painterResource(R.drawable.jetpack_compose),
                contentDescription = "Logo Jetpack Compose",
                modifier = Modifier.size(animatedSizeDp)
            )
            Spacer(modifier = Modifier.width(8.dp))
            // Column composable to arrange the text vertically
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = "Hello $name!",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                )
                Text(
                    text = "Welcome to Dicoding!",
                    style = MaterialTheme.typography.bodyLarge.copy(
                        fontStyle = FontStyle.Italic
                    )
                )
            }
            // IconButton to toggle the expansion state of the card
            IconButton(onClick = { isExpanded = !isExpanded }) {
                Icon(
                    imageVector = if (isExpanded) Icons.Filled.ExpandLess else Icons.Outlined.ExpandMore,
                    contentDescription = if (isExpanded) "Show less" else "Show more"
                )
            }
        }
    }
}

/**
 * GreetingPreview composable function provides a preview of the Greeting composable.
 *
 * It applies the JetpackComposeLabsTheme to the Greeting composable and is annotated with @Preview
 * to enable previews in the Android Studio design tool. Two previews are provided: one for the default
 * light theme and one for the dark theme.
 */
@Preview(showBackground = true)
@Preview(showBackground = true, uiMode = UI_MODE_NIGHT_YES)
@Composable
fun GreetingPreview() {
    // Apply the JetpackComposeLabsTheme to the preview
    JetpackComposeLabsTheme {
        // Call the Greeting composable to display its preview with the name "Jetpack Compose"
        Greeting("Jetpack Compose")
    }
}