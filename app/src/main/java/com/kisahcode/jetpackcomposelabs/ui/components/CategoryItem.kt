package com.kisahcode.jetpackcomposelabs.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.paddingFromBaseline
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kisahcode.jetpackcomposelabs.R
import com.kisahcode.jetpackcomposelabs.model.Category
import com.kisahcode.jetpackcomposelabs.ui.theme.JetpackComposeLabsTheme

/**
 * CategoryItem composable function displays an item representing a category.
 *
 * The item includes an image and a text label, both centered horizontally. The image is displayed
 * inside a circular shape, and the text label is shown below the image with some padding from the
 * baseline.
 *
 * @param category The category data containing the image and text resource IDs.
 * @param modifier Modifier to be applied to the Column containing the category item.
 */
@Composable
fun CategoryItem(
    category: Category,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Display the category image inside a circular shape
        Image(
            painter = painterResource(category.imageCategory),
            contentDescription = null,
            modifier = Modifier
                .size(60.dp)
                .clip(CircleShape)
        )
        // Display the category text label below the image
        Text(
            text = stringResource(id = category.textCategory),
            fontSize = 10.sp,
            modifier = Modifier.paddingFromBaseline(top = 16.dp, bottom = 8.dp)
        )
    }
}

/**
 * CategoryItemPreview provides a preview of the CategoryItem composable.
 *
 * This preview is useful for seeing how the CategoryItem composable looks in different configurations
 * in Android Studio.
 */
@Preview(showBackground = true)
@Composable
fun CategoryItemPreview() {
    JetpackComposeLabsTheme {
        CategoryItem(
            category = Category(
                R.drawable.icon_category_cappuccino,
                R.string.category_cappuccino
            ),
            modifier = Modifier.padding(horizontal = 8.dp)
        )
    }
}