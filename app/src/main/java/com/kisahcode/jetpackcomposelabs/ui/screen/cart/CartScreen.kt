package com.kisahcode.jetpackcomposelabs.ui.screen.cart

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.kisahcode.jetpackcomposelabs.R

/**
 * Composable function that displays the cart screen.
 *
 * This function represents the UI for the cart screen. It currently displays a centered
 * text indicating the cart menu. The layout is a Box that fills the entire screen size
 * with the text centered.
 *
 * @param modifier Modifier to be applied to the layout. Defaults to [Modifier].
 */
@Composable
fun CartScreen(
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center,
    ) {
        Text(stringResource(R.string.menu_cart))
    }
}
