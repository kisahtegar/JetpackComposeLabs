package com.kisahcode.jetpackcomposelabs.ui.screen.profile

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.kisahcode.jetpackcomposelabs.R

/**
 * Composable function that displays the profile screen.
 *
 * This function creates a full-screen layout with a centered text element.
 * The text content is retrieved from the string resources using [R.string.menu_profile].
 *
 * @param modifier Optional [Modifier] to be applied to the [Box] layout.
 * This allows customization of the layout's appearance and behavior.
 */
@Composable
fun ProfileScreen(
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center,
    ) {
        Text(stringResource(R.string.menu_profile))
    }
}