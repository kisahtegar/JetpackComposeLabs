package com.kisahcode.jetpackcomposelabs.ui.components

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.kisahcode.jetpackcomposelabs.ui.theme.JetpackComposeLabsTheme

/**
 * SectionText composable function displays a section header text.
 *
 * The text is styled with the headlineSmall typography from the MaterialTheme and extra bold font
 * weight. It also has padding applied to its horizontal and vertical edges.
 *
 * @param title The title to be displayed as the section header.
 * @param modifier Modifier to be applied to the Text composable.
 */
@Composable
fun SectionText(
    title: String,
    modifier: Modifier = Modifier,
) {
    Text(
        text = title,
        style = MaterialTheme.typography.headlineSmall.copy(
            fontWeight = FontWeight.ExtraBold
        ),
        modifier = modifier
            .padding(horizontal = 16.dp, vertical = 8.dp),
    )
}

/**
 * SectionTextPreview provides a preview of the SectionText composable.
 *
 * This preview is useful for seeing how the SectionText composable looks in different configurations
 * in Android Studio.
 */
@Preview(showBackground = true)
@Composable
fun SectionTextPreview() {
    JetpackComposeLabsTheme {
        SectionText(title = "Products")
    }
}