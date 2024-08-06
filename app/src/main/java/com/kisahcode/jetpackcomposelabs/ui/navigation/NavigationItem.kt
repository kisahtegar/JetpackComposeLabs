package com.kisahcode.jetpackcomposelabs.ui.navigation

import androidx.compose.ui.graphics.vector.ImageVector

/**
 * Data class representing an item in the bottom navigation bar.
 *
 * @property title The title or label associated with this navigation item.
 * @property icon The icon to display for this navigation item, represented as an [ImageVector].
 * @property screen The destination screen associated with this navigation item.
 */
data class NavigationItem(
    val title: String,
    val icon: ImageVector,
    val screen: Screen
)