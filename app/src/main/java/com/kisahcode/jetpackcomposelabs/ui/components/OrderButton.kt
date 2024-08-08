package com.kisahcode.jetpackcomposelabs.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.kisahcode.jetpackcomposelabs.ui.theme.JetpackComposeLabsTheme

/**
 * Composable function that displays an order button.
 *
 * This function creates a button with specified text, an onClick event handler, and optional
 * modifiers and enabled state. It is designed to be used as an order button in the UI.
 *
 * @param text The text to be displayed on the button.
 * @param modifier Modifier to be applied to the button. Defaults to [Modifier].
 * @param enabled Boolean indicating whether the button is enabled or not. Defaults to true.
 * @param onClick Lambda function to be invoked when the button is clicked.
 */
@Composable
fun OrderButton(
    text: String,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    onClick: () -> Unit,
) {
    Button(
        onClick = onClick,
        enabled = enabled,
        modifier = modifier
            .fillMaxWidth()
            .height(52.dp)
            .semantics(mergeDescendants = true) {
                contentDescription = "Order Button"
            }
    ) {
        Text(
            text = text,
            modifier = Modifier.align(Alignment.CenterVertically)
        )
    }
}

/**
 * Preview of the [OrderButton] composable function.
 *
 * This function provides a preview of the [OrderButton] composable with sample data.
 */
@Composable
@Preview(showBackground = true)
fun OrderButtonPreview() {
    JetpackComposeLabsTheme {
        OrderButton(
            text = "Order",
            onClick = {}
        )
    }
}