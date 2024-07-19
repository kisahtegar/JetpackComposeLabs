package com.kisahcode.jetpackcomposelabs.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.kisahcode.jetpackcomposelabs.R
import com.kisahcode.jetpackcomposelabs.model.Menu
import com.kisahcode.jetpackcomposelabs.ui.theme.JetpackComposeLabsTheme

/**
 * MenuItem composable function displays a menu item card.
 *
 * The card includes an image of the menu item, its title, and its price. The image is clipped to
 * a rounded corner shape. The title is shown in bold with a maximum of 2 lines, and the price is
 * shown below the title.
 *
 * @param menu The menu item to be displayed.
 * @param modifier Modifier to be applied to the card.
 */
@Composable
fun MenuItem(
    menu: Menu,
    modifier: Modifier = Modifier,
) {
    ElevatedCard(
        modifier = modifier.width(140.dp),
        shape = RoundedCornerShape(8.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.background,
        ),
    ) {
        Column {
            Image(
                painter = painterResource(menu.image),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(120.dp)
                    .clip(RoundedCornerShape(8.dp))
            )
            Column(modifier = Modifier.padding(8.dp)) {
                Text(
                    text = menu.title,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                    style = MaterialTheme.typography.titleMedium.copy(
                        fontWeight = FontWeight.ExtraBold
                    ),
                )
                Text(
                    text = menu.price,
                    style = MaterialTheme.typography.titleSmall,
                )
            }
        }
    }
}

/**
 * MenuItemPreview composable function provides a preview of the MenuItem composable.
 *
 * This function is used for development purposes to display a preview of the MenuItem
 * within the JetpackComposeLabsTheme. It helps in visualizing the MenuItem layout and design.
 */
@Composable
@Preview(showBackground = true)
fun MenuItemPreview() {
    JetpackComposeLabsTheme {
        MenuItem(
            menu = Menu(R.drawable.menu2, "Hot Pumpkin Spice Latte Premium", "Rp 18.000"),
            modifier = Modifier.padding(8.dp)
        )
    }
}