package com.kisahcode.jetpackcomposelabs.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.kisahcode.jetpackcomposelabs.R
import com.kisahcode.jetpackcomposelabs.ui.theme.JetpackComposeLabsTheme
import com.kisahcode.jetpackcomposelabs.ui.theme.Shapes

/**
 * Composable function that displays an item in the cart.
 *
 * This function creates a row layout to display an item in the cart. It includes an image of the
 * reward, the title, the required points, and a counter to increase or decrease the quantity of
 * the item.
 *
 * @param rewardId The ID of the reward item.
 * @param image The resource ID of the image to be displayed for the reward.
 * @param title The title of the reward.
 * @param totalPoint The total points required to obtain the reward.
 * @param count The current quantity of the item in the cart.
 * @param onProductCountChanged Callback to handle changes in the product count. It takes the reward
 *                              ID and the new count as parameters.
 * @param modifier Modifier to be applied to the layout. Defaults to [Modifier].
 */
@Composable
fun CartItem(
    rewardId: Long,
    image: Int,
    title: String,
    totalPoint: Int,
    count: Int,
    onProductCountChanged: (id: Long, count: Int) -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier.fillMaxWidth()
    ) {
        Image(
            painter = painterResource(image),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(90.dp)
                .clip(Shapes.small)
        )
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
                .weight(1.0f)
        ) {
            Text(
                text = title,
                maxLines = 3,
                overflow = TextOverflow.Ellipsis,
                style = MaterialTheme.typography.titleMedium.copy(
                    fontWeight = FontWeight.ExtraBold
                )
            )
            Text(
                text = stringResource(
                    R.string.required_point,
                    totalPoint
                ),
                color = MaterialTheme.colorScheme.secondary,
                style = MaterialTheme.typography.titleSmall,
            )
        }
        ProductCounter(
            orderId = rewardId,
            orderCount = count,
            onProductIncreased = { onProductCountChanged(rewardId, count + 1) },
            onProductDecreased = { onProductCountChanged(rewardId, count - 1) },
            modifier = Modifier.padding(8.dp)
        )
    }
}

/**
 * Preview of the [CartItem] composable function.
 *
 * This function provides a preview of the [CartItem] composable with sample data.
 */
@Composable
@Preview(showBackground = true)
fun CartItemPreview() {
    JetpackComposeLabsTheme {
        CartItem(
            4, R.drawable.reward_4, "Jaket Hoodie Dicoding", 4000, 0,
            onProductCountChanged = { rewardId, count -> },
        )
    }
}