package com.kisahcode.jetpackcomposelabs.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

/**
 * HomeSection composable function displays a section with a title and content.
 *
 * This function uses a slot-based layout to allow flexible content display within a section.
 * It consists of a column that contains a SectionText composable for the title and a content slot
 * for the custom content.
 *
 * @param title The title to be displayed in the section.
 * @param content The content to be displayed within the section.
 * @param modifier Modifier to be applied to the Column.
 */
@Composable
fun HomeSection(
    title: String,
    content: @Composable () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(modifier) {
        SectionText(title, modifier)
        content()
    }
}