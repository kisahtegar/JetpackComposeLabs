package com.kisahcode.jetpackcomposelabs

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.kisahcode.jetpackcomposelabs.ui.theme.JetpackComposeLabsTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            JetpackComposeLabsTheme {
                Column {
                    StatefulTemperatureInput()
                    ConverterApp()
                    TwoWayConverterApp()
                }
            }
        }
    }
}

/**
 * A composable function that manages its own state for temperature input and conversion.
 * This function converts a given Celsius temperature to Fahrenheit and displays the result.
 *
 * @param modifier Modifier to be applied to the Column.
 */
@Composable
fun StatefulTemperatureInput(
    modifier: Modifier = Modifier
) {
    // State variables for input and output temperatures.
    var input by remember { mutableStateOf("") }
    var output by remember { mutableStateOf("") }

    // Column layout with padding.
    Column(modifier.padding(16.dp)) {
        Text(
            text = stringResource(R.string.stateful_converter),
            style = MaterialTheme.typography.headlineSmall
        )
        OutlinedTextField(
            value = input,
            label = { Text(stringResource(R.string.enter_celsius)) },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            onValueChange = { newInput ->
                // Update input state and convert to Fahrenheit.
                input = newInput
                output = convertToFahrenheit(newInput)
            },
        )
        Text(stringResource(R.string.temperature_fahrenheit, output))
    }
}

/**
 * A stateless composable function for temperature input and conversion display.
 * This function displays a text field for entering a Celsius temperature and
 * a Text for showing the converted Fahrenheit temperature.
 *
 * The state (input and output values) is hoisted to the caller of this composable.
 *
 * @param input The input temperature in Celsius as a String.
 * @param output The converted temperature in Fahrenheit as a String.
 * @param onValueChange A callback to handle changes in the input value.
 * @param modifier Modifier to be applied to the Column.
 */
@Composable
fun StatelessTemperatureInput(
    input: String,
    output: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(modifier.padding(16.dp)) {
        Text(
            text = stringResource(R.string.stateless_converter),
            style = MaterialTheme.typography.headlineSmall
        )
        OutlinedTextField(
            value = input,
            label = { Text(stringResource(R.string.enter_celsius)) },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            onValueChange = onValueChange
        )
        Text(stringResource(R.string.temperature_fahrenheit, output))
    }
}

/**
 * A composable function for the ConverterApp. This function manages the state for temperature input
 * and output, and uses the StatelessTemperatureInput composable to display the input field and the
 * converted output.
 *
 * The state for input and output is managed within this composable and passed down to
 * StatelessTemperatureInput for rendering.
 *
 * @param modifier Modifier to be applied to the ConverterApp composable.
 */
@Composable
fun ConverterApp(
    modifier: Modifier = Modifier
) {
    var input by remember { mutableStateOf("") }
    var output by remember { mutableStateOf("") }

    StatelessTemperatureInput(
        input = input,
        output = output,
        onValueChange = { newInput ->
            input = newInput
            output = convertToFahrenheit(newInput)
        }
    )

}

/**
 * An enum class representing different temperature scales.
 *
 * This enum defines two temperature scales: Celsius and Fahrenheit.
 * Each enum value is associated with a human-readable scale name.
 *
 * @property scaleName The name of the temperature scale.
 */
enum class Scale(val scaleName: String) {
    CELSIUS("Celsius"),
    FAHRENHEIT("Fahrenheit")
}

/**
 * A composable function that provides a text input for entering a temperature value.
 *
 * This function displays an input field for the specified temperature scale (Celsius or Fahrenheit).
 * It uses the provided state and callback function to handle user input and updates.
 *
 * @param scale The temperature scale for which the input is intended (Celsius or Fahrenheit).
 * @param input The current value of the input field.
 * @param onValueChange A callback function that is triggered when the input value changes.
 * @param modifier Modifier to be applied to the Column containing the input field.
 */
@Composable
fun GeneralTemperatureInput(
    scale: Scale,
    input: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(modifier) {
        OutlinedTextField(
            value = input,
            label = { Text(stringResource(R.string.enter_temperature, scale.scaleName)) },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            onValueChange = onValueChange,
        )
    }
}

/**
 * A composable function that provides a two-way temperature converter application.
 *
 * This function allows users to input temperatures in both Celsius and Fahrenheit.
 * It updates the other temperature value accordingly, demonstrating two-way data binding.
 *
 * @param modifier Modifier to be applied to the Column containing the input fields and text.
 */
@Composable
fun TwoWayConverterApp(
    modifier: Modifier = Modifier
) {
    // State variables for input and output temperatures.
    var celsius by remember { mutableStateOf("") }
    var fahrenheit by remember { mutableStateOf("") }

    Column(modifier.padding(16.dp)) {
        Text(
            text = stringResource(id = R.string.two_way_converter),
            style = MaterialTheme.typography.headlineSmall
        )
        GeneralTemperatureInput(
            scale = Scale.CELSIUS,
            input = celsius,
            onValueChange = { newInput ->
                celsius = newInput
                fahrenheit = convertToFahrenheit(newInput)
            }
        )
        GeneralTemperatureInput(
            scale = Scale.FAHRENHEIT,
            input = fahrenheit,
            onValueChange = { newInput ->
                fahrenheit = newInput
                celsius = convertToCelsius(newInput)
            }
        )
    }
}

/**
 * Converts a temperature value in Celsius to Fahrenheit.
 *
 * This function takes a string input representing a temperature in Celsius,
 * converts it to a Double, and then applies the formula to convert it to Fahrenheit.
 * If the input cannot be converted to a Double, the function returns "null".
 *
 * @param celsius The temperature value in Celsius as a String.
 * @return The converted temperature value in Fahrenheit as a String.
 */
private fun convertToFahrenheit(celsius: String) =
    celsius.toDoubleOrNull()?.let {
        (it * 9 / 5) + 32
    }.toString()

/**
 * Converts a temperature value in Fahrenheit to Celsius.
 *
 * This function takes a string input representing a temperature in Fahrenheit,
 * converts it to a Double, and then applies the formula to convert it to Celsius.
 * If the input cannot be converted to a Double, the function returns "null".
 *
 * @param fahrenheit The temperature value in Fahrenheit as a String.
 * @return The converted temperature value in Celsius as a String.
 */
private fun convertToCelsius(fahrenheit: String) =
    fahrenheit.toDoubleOrNull()?.let {
        (it - 32) * 5 / 9
    }.toString()

/**
 * A preview composable function that displays the temperature converter application.
 *
 * This function showcases the `StatefulTemperatureInput`, `ConverterApp`, and `TwoWayConverterApp`
 * composable functions within a column layout for preview purposes.
 */
@Preview(showBackground = true, device = Devices.PIXEL_4)
@Composable
fun TemperatureConverterPreview() {
    JetpackComposeLabsTheme {
        Column {
            StatefulTemperatureInput()
            ConverterApp()
            TwoWayConverterApp()
        }
    }
}