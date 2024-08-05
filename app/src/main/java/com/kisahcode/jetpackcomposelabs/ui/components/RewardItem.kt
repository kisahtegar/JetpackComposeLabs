package com.kisahcode.jetpackcomposelabs.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
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
 * Composable function that displays a reward item.
 *
 * This function creates a column layout that displays a reward image, title, and required points.
 * It is designed to be used as an item in a list of rewards in the UI.
 *
 * @param image Resource ID of the reward image.
 * @param title Title of the reward.
 * @param requiredPoint Number of points required to obtain the reward.
 * @param modifier Modifier to be applied to the layout. Defaults to [Modifier].
 */
@Composable
fun RewardItem(
    image: Int,
    title: String,
    requiredPoint: Int,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
    ) {
        Image(
            painter = painterResource(image),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(170.dp)
                .clip(Shapes.medium)
        )
        Text(
            text = title,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis,
            style = MaterialTheme.typography.titleMedium.copy(
                fontWeight = FontWeight.ExtraBold
            )
        )
        Text(
            text = stringResource(R.string.required_point, requiredPoint),
            style = MaterialTheme.typography.titleSmall,
            color = MaterialTheme.colorScheme.secondary
        )
    }
}

/**
 * Preview of the [RewardItem] composable function.
 *
 * This function provides a preview of the [RewardItem] composable with sample data.
 */
@Composable
@Preview(showBackground = true)
fun RewardItemPreview() {
    JetpackComposeLabsTheme {
        RewardItem(R.drawable.reward_4, "Jaket Hoodie Dicoding", 4000)
    }
}