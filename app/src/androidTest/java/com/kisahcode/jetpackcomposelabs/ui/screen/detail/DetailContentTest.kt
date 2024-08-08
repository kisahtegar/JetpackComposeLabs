package com.kisahcode.jetpackcomposelabs.ui.screen.detail

import androidx.activity.ComponentActivity
import androidx.compose.ui.test.assert
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsEnabled
import androidx.compose.ui.test.assertIsNotEnabled
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.onRoot
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.printToLog
import com.kisahcode.jetpackcomposelabs.R
import com.kisahcode.jetpackcomposelabs.model.OrderReward
import com.kisahcode.jetpackcomposelabs.model.Reward
import com.kisahcode.jetpackcomposelabs.onNodeWithStringId
import com.kisahcode.jetpackcomposelabs.ui.theme.JetpackComposeLabsTheme
import org.junit.Before
import org.junit.Rule
import org.junit.Test

/**
 * UI tests for the DetailContent composable.
 *
 * This class contains tests that verify the correct behavior of the DetailContent composable,
 * ensuring that the UI updates as expected when interacting with the product's detail page. The
 * tests include verifying the display of content, enabling/disabling buttons based on user
 * interactions, and checking the correct display of product counts.
 */
class DetailContentTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    // A fake order reward used for testing purposes
    private val fakeOrderReward = OrderReward(
        reward = Reward(4, R.drawable.reward_4, "Jaket Hoodie Dicoding", 7500),
        count = 0
    )

    /**
     * Sets up the testing environment for the DetailContent composable.
     *
     * This function is called before each test to prepare the Compose test rule's content. It initializes
     * the UI by setting the content to the `DetailContent` composable using a fake `OrderReward` object.
     * Additionally, it logs the UI tree to the logcat for debugging purposes.
     *
     * The fake `OrderReward` object contains pre-defined data to simulate a real scenario, ensuring that
     * the tests have consistent and controlled inputs. This helps in verifying the correctness of the UI
     * behavior under test conditions.
     */
    @Before
    fun setUp() {
        // Sets the content to the DetailContent composable with fake data before each test
        composeTestRule.setContent {
            JetpackComposeLabsTheme {
                DetailContent(
                    fakeOrderReward.reward.image,
                    fakeOrderReward.reward.title,
                    fakeOrderReward.reward.requiredPoint,
                    fakeOrderReward.count,
                    onBackClick = {},
                    onAddToCart = {}
                )
            }
        }
        // Logs the current UI hierarchy for debugging and inspection
        composeTestRule.onRoot().printToLog("currentLabelExists")
    }

    /**
     * Verifies that the DetailContent composable displays the correct UI elements.
     *
     * This test checks whether the title and required points of the reward are correctly displayed
     * when the DetailContent composable is rendered. It ensures that the essential UI elements are
     * visible to the user, confirming that the composable is functioning as expected.
     */
    @Test
    fun detailContent_isDisplayed() {
        // Asserts that the reward title is displayed on the screen
        composeTestRule.onNodeWithText(fakeOrderReward.reward.title).assertIsDisplayed()

        // Asserts that the required points text is displayed on the screen
        composeTestRule.onNodeWithText(
            composeTestRule.activity.getString(
                R.string.required_point,
                fakeOrderReward.reward.requiredPoint
            )
        ).assertIsDisplayed()
    }

    /**
     * Verifies that the order button in DetailContent composable enables after adding products.
     *
     * This test checks the behavior of the order button in the DetailContent composable when
     * adding products. It ensures that initially, the button is disabled and becomes enabled
     * after products are added, indicating correct user interaction and state management.
     */
    @Test
    fun increaseProduct_buttonEnabled() {
        // Asserts that the order button is initially disabled
        composeTestRule.onNodeWithContentDescription("Order Button").assertIsNotEnabled()
        // Performs clicks on the plus symbol to simulate adding products
        composeTestRule.onNodeWithStringId(R.string.plus_symbol).performClick()
        // Asserts that the order button becomes enabled after products are added
        composeTestRule.onNodeWithContentDescription("Order Button").assertIsEnabled()
    }

    /**
     * Verifies that the product counter in the DetailContent composable updates correctly.
     *
     * This test ensures that when the plus symbol button is clicked multiple times, the counter
     * correctly reflects the number of products added.
     */
    @Test
    fun increaseProduct_correctCounter() {
        // Simulate two clicks on the plus symbol to increase the product count
        composeTestRule.onNodeWithStringId(R.string.plus_symbol).performClick().performClick()
        // Assert that the counter displays the correct number of products added
        composeTestRule.onNodeWithTag("count").assert(hasText("2"))
    }

    /**
     * Verifies that the product counter in the DetailContent composable does not go below zero.
     *
     * This test ensures that when the minus symbol button is clicked while the counter is at zero,
     * the counter remains at zero and does not display a negative value.
     */
    @Test
    fun decreaseProduct_stillZero() {
        // Simulate a click on the minus symbol to decrease the product count
        composeTestRule.onNodeWithStringId(R.string.minus_symbol).performClick()
        // Assert that the counter remains at zero and does not go negative
        composeTestRule.onNodeWithTag("count").assert(hasText("0"))
    }
}