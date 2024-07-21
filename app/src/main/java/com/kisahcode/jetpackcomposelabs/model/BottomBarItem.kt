package com.kisahcode.jetpackcomposelabs.model

import androidx.compose.ui.graphics.vector.ImageVector

/**
 * Data class representing a bottom bar item.
 *
 * @property title The title of the bottom bar item.
 * @property icon The icon associated with the bottom bar item, represented as an ImageVector.
 */
data class BottomBarItem(
    val title: String,
    val icon: ImageVector,
)