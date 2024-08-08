package com.kisahcode.jetpackcomposelabs.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kisahcode.jetpackcomposelabs.ui.theme.JetpackComposeLabsTheme

/**
 * Composable function that displays a product counter with increase and decrease buttons.
 *
 * This function creates a row layout containing two buttons for increasing and decreasing the
 * product count, and a text displaying the current count. The increase and decrease buttons call
 * the provided callback functions with the order ID when clicked.
 *
 * @param orderId ID of the order associated with the product.
 * @param orderCount Current count of the product.
 * @param onProductIncreased Callback function to be called when the increase button is clicked,
 *                           with the order ID as a parameter.
 * @param onProductDecreased Callback function to be called when the decrease button is clicked,
 *                           with the order ID as a parameter.
 * @param modifier Modifier to be applied to the layout. Defaults to [Modifier].
 */
@Composable
fun ProductCounter(
    orderId: Long,
    orderCount: Int,
    onProductIncreased: (Long) -> Unit,
    onProductDecreased: (Long) -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier.size(width = 110.dp, height = 40.dp).padding(4.dp)
    ) {
        Surface(
            shape = RoundedCornerShape(size = 5.dp),
            border = BorderStroke(1.dp, MaterialTheme.colorScheme.primary),
            color = Color.Transparent,
            contentColor = MaterialTheme.colorScheme.primary,
            modifier = Modifier.size(30.dp)
        ) {
            Text(
                text = "—",
                fontSize = 22.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .weight(1f)
                    .clickable {
                        onProductDecreased(orderId)
                    }
            )
        }
        Text(
            text = orderCount.toString(),
            modifier = Modifier
                .testTag("count")
                .weight(1f),
            fontSize = 20.sp,
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Bold
        )
        Surface(
            shape = RoundedCornerShape(size = 5.dp),
            border = BorderStroke(1.dp, MaterialTheme.colorScheme.primary),
            color = Color.Transparent,
            contentColor = MaterialTheme.colorScheme.primary,
            modifier = Modifier.size(30.dp)
        ) {
            Text(
                text = "＋",
                fontSize = 22.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .weight(1f)
                    .clickable {
                        onProductIncreased(orderId)
                    }
            )
        }
    }
}

/**
 * Preview of the [ProductCounter] composable function.
 *
 * This function provides a preview of the [ProductCounter] composable with sample data.
 */
@Preview
@Composable
fun ProductCounterPreview() {
    JetpackComposeLabsTheme {
        ProductCounter(
            orderId = 1,
            orderCount = 0,
            onProductIncreased = { },
            onProductDecreased = { }
        )
    }
}
