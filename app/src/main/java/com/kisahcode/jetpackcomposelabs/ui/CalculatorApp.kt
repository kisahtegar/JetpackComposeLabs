package com.kisahcode.jetpackcomposelabs.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.kisahcode.jetpackcomposelabs.R
import com.kisahcode.jetpackcomposelabs.ui.theme.JetpackComposeLabsTheme

/**
 * Composable function that displays a simple calculator app.
 *
 * This function provides a user interface to input length and width values, calculates the area
 * based on these values, and displays the result. It uses state to manage user input and the result.
 *
 * @param modifier Modifier to be applied to the layout. Defaults to [Modifier].
 */
@Composable
fun CalculatorApp(
    modifier: Modifier = Modifier,
) {
    // State variables to hold the user input and the result
    var lengthInput by remember { mutableStateOf("") }
    var widthInput by remember { mutableStateOf("") }
    var result by remember { mutableStateOf("") }

    // Convert the input values to Double, defaulting to 0.0 if conversion fails
    val length = lengthInput.toDoubleOrNull() ?: 0.0
    val width = widthInput.toDoubleOrNull() ?: 0.0

    Box(
        modifier = modifier.fillMaxSize().padding(16.dp),
        contentAlignment = Alignment.Center,
    ) {
        Column {
            TextField(
                value = lengthInput,
                onValueChange = { lengthInput = it },
                label = { Text(stringResource(R.string.enter_length)) },
                singleLine = true,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            )
            Spacer(modifier.height(8.dp))
            TextField(
                value = widthInput,
                onValueChange = { widthInput = it },
                label = { Text(stringResource(R.string.enter_width)) },
                singleLine = true,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            )
            Button(onClick = { result = (length * width).toString() }) {
                Text(stringResource(R.string.count))
            }
            Text(
                text = stringResource(R.string.result, result),
            )
        }
    }
}

/**
 * Preview function for the CalculatorApp composable.
 *
 * This function provides a preview of the CalculatorApp composable within the Jetpack Compose
 * tooling in Android Studio. It applies the JetpackComposeLabsTheme to the CalculatorApp to
 * ensure that the preview matches the app's theme.
 */
@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    JetpackComposeLabsTheme {
        CalculatorApp()
    }
}