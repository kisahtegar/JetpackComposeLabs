package com.kisahcode.jetpackcomposelabs

import androidx.activity.ComponentActivity
import androidx.annotation.StringRes
import androidx.compose.ui.test.SemanticsNodeInteraction
import androidx.compose.ui.test.junit4.AndroidComposeTestRule
import androidx.compose.ui.test.onNodeWithText
import androidx.test.ext.junit.rules.ActivityScenarioRule

/**
 * Extension function for AndroidComposeTestRule to simplify finding a node by its string resource ID.
 *
 * This function allows you to locate a UI element in your Compose UI tests by using a string resource
 * ID. It simplifies the process of finding a node by abstracting the call to `getString` and directly
 * using the result in `onNodeWithText`.
 *
 * @param id The resource ID of the string to look up.
 * @return A SemanticsNodeInteraction object that allows you to interact with the located node.
 */
fun <A : ComponentActivity> AndroidComposeTestRule<ActivityScenarioRule<A>, A>.onNodeWithStringId(
    @StringRes id: Int,
): SemanticsNodeInteraction = onNodeWithText(activity.getString(id))