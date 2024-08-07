package com.kisahcode.jetpackcomposelabs.ui

import androidx.activity.ComponentActivity
import androidx.compose.ui.test.assertHasNoClickAction
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import com.kisahcode.jetpackcomposelabs.R
import com.kisahcode.jetpackcomposelabs.ui.theme.JetpackComposeLabsTheme
import org.junit.Before
import org.junit.Rule
import org.junit.Test

/**
 * Test class for the CalculatorApp composable function.
 *
 * This class contains UI tests to verify the functionality of the CalculatorApp, including correct
 * calculations and handling of invalid input.
 */
class CalculatorAppTest {

    /**
     * A JUnit rule to set up the Compose testing environment.
     *
     * This rule sets up the activity and initializes the Compose content for testing.
     */
    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    /**
     * Sets up the content for testing.
     *
     * This method is called before each test to set up the CalculatorApp composable within
     * the JetpackComposeLabsTheme.
     */
    @Before
    fun setUp() {
        composeTestRule.setContent {
            JetpackComposeLabsTheme {
                CalculatorApp()
            }
        }
    }

    /**
     * Tests the calculation of the area of a rectangle.
     *
     * This test inputs valid length and width values, performs the calculation, and verifies
     * the result displayed in the UI is correct.
     */
    @Test
    fun calculate_area_of_rectangle_correct() {
        composeTestRule
            .onNodeWithText(composeTestRule.activity.getString(R.string.enter_length))
            .performTextInput("3")
        composeTestRule
            .onNodeWithText(composeTestRule.activity.getString(R.string.enter_width))
            .performTextInput("4")
        composeTestRule
            .onNodeWithText(composeTestRule.activity.getString(R.string.count))
            .performClick()
        composeTestRule
            .onNodeWithText(composeTestRule.activity.getString(R.string.count), useUnmergedTree = true)
            .assertHasNoClickAction()
        composeTestRule
            .onNodeWithText(composeTestRule.activity.getString(R.string.result, 12.0))
            .assertExists()
    }

    /**
     * Tests the handling of incorrect input.
     *
     * This test inputs invalid length values, performs the calculation, and verifies
     * that the result displayed in the UI is handled correctly (i.e., not calculated).
     */
    @Test
    fun wrong_input_not_calculated() {
        composeTestRule
            .onNodeWithText(composeTestRule.activity.getString(R.string.enter_length))
            .performTextInput("..3")
        composeTestRule
            .onNodeWithText(composeTestRule.activity.getString(R.string.enter_width))
            .performTextInput("4")
        composeTestRule
            .onNodeWithText(composeTestRule.activity.getString(R.string.count))
            .performClick()
        composeTestRule
            .onNodeWithText(composeTestRule.activity.getString(R.string.result, 0.0))
            .assertExists()
    }
}