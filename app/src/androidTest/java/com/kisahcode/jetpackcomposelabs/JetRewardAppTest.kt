package com.kisahcode.jetpackcomposelabs

import androidx.activity.ComponentActivity
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performScrollToIndex
import androidx.navigation.compose.ComposeNavigator
import androidx.navigation.testing.TestNavHostController
import com.kisahcode.jetpackcomposelabs.model.FakeRewardDataSource
import com.kisahcode.jetpackcomposelabs.ui.navigation.Screen
import com.kisahcode.jetpackcomposelabs.ui.theme.JetpackComposeLabsTheme
import org.junit.Before
import org.junit.Rule
import org.junit.Test

/**
 * UI Test class for the JetRewardApp.
 *
 * This class contains a set of UI tests that verify the correct navigation and UI interactions
 * within the JetRewardApp. The tests are designed to ensure that the app's navigation works as
 * expected and that UI elements are displayed correctly during different user actions.
 *
 * The tests use the Jetpack Compose UI testing framework to interact with and assert the state of
 * the composable screens. The `TestNavHostController` is used to simulate and verify the app's
 * navigation behavior.
 */
class JetRewardAppTest {

    // Provides the Compose testing environment and tools for interacting with and asserting on UI elements.
    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    // Simulates and verifies navigation behavior within the app during tests.
    private lateinit var navController: TestNavHostController

    /**
     * Sets up the test environment before each test.
     *
     * This function initializes the `TestNavHostController` and sets the content of the test rule
     * with the `JetRewardApp` composable wrapped in the app's theme. The `ComposeNavigator` is
     * added to the `navController` to handle the navigation within the Compose UI. This setup is
     * necessary for all the tests in this class to ensure that the app's navigation is properly
     * simulated and can be verified.
     */
    @Before
    fun setUp() {
        composeTestRule.setContent {
            JetpackComposeLabsTheme {
                navController = TestNavHostController(LocalContext.current)
                navController.navigatorProvider.addNavigator(ComposeNavigator())
                JetRewardApp(navController = navController)
            }
        }
    }

    /**
     * Verifies that the start destination of the `NavHost` is the Home screen.
     *
     * This test asserts that the initial route of the app is the Home screen by checking the current
     * route in the navigation back stack. This ensures that the app starts at the correct screen when
     * it is first launched.
     */
    @Test
    fun navHost_verifyStartDestination() {
        navController.assertCurrentRouteName(Screen.Home.route)
    }

    /**
     * Tests that clicking on a reward item in the list navigates to the Detail screen with the correct data.
     *
     * This test scrolls to a specific item in the reward list, clicks on it, and then verifies that
     * the app navigates to the Detail screen. It also checks that the title of the reward is correctly
     * displayed on the Detail screen, ensuring that the data passed during navigation is correct.
     */
    @Test
    fun navHost_clickItem_navigatesToDetailWithData() {
        composeTestRule.onNodeWithTag("RewardList").performScrollToIndex(10)
        composeTestRule.onNodeWithText(FakeRewardDataSource.dummyRewards[10].title).performClick()
        navController.assertCurrentRouteName(Screen.DetailReward.route)
        composeTestRule.onNodeWithText(FakeRewardDataSource.dummyRewards[10].title).assertIsDisplayed()
    }

    /**
     * Tests that the bottom navigation works correctly and navigates to the appropriate screens.
     *
     * This test clicks on each item in the bottom navigation bar and verifies that the app navigates
     * to the correct screen: Cart, Profile, and Home. This ensures that the bottom navigation is
     * functioning as intended and that each menu item directs the user to the correct screen.
     */
    @Test
    fun navHost_bottomNavigation_working() {
        composeTestRule.onNodeWithStringId(R.string.menu_cart).performClick()
        navController.assertCurrentRouteName(Screen.Cart.route)
        composeTestRule.onNodeWithStringId(R.string.menu_profile).performClick()
        navController.assertCurrentRouteName(Screen.Profile.route)
        composeTestRule.onNodeWithStringId(R.string.menu_home).performClick()
        navController.assertCurrentRouteName(Screen.Home.route)
    }

    /**
     * Tests that clicking the back button on the Detail screen navigates back to the Home screen.
     *
     * This test navigates to the Detail screen by clicking on a reward item in the list and then
     * clicks the back button. It verifies that the app navigates back to the Home screen, ensuring
     * that the back navigation works correctly.
     */
    @Test
    fun navHost_clickItem_navigatesBack() {
        composeTestRule.onNodeWithTag("RewardList").performScrollToIndex(10)
        composeTestRule.onNodeWithText(FakeRewardDataSource.dummyRewards[10].title).performClick()
        navController.assertCurrentRouteName(Screen.DetailReward.route)
        composeTestRule.onNodeWithContentDescription(composeTestRule.activity.getString(R.string.back)).performClick()
        navController.assertCurrentRouteName(Screen.Home.route)
    }

    /**
     * Tests the navigation flow from the Detail screen to the Cart screen and back to the Home screen.
     *
     * This test verifies that when the user adds an item to the cart from the Detail screen, the app
     * navigates to the Cart screen. It also checks that navigating back to the Home screen works
     * correctly and that the back stack is maintained properly.
     */
    @Test
    fun navHost_checkout_rightBackStack() {
        composeTestRule.onNodeWithText(FakeRewardDataSource.dummyRewards[4].title).performClick()
        navController.assertCurrentRouteName(Screen.DetailReward.route)
        composeTestRule.onNodeWithStringId(R.string.plus_symbol).performClick()
        composeTestRule.onNodeWithContentDescription("Order Button").performClick()
        navController.assertCurrentRouteName(Screen.Cart.route)
        composeTestRule.onNodeWithStringId(R.string.menu_home).performClick()
        navController.assertCurrentRouteName(Screen.Home.route)
    }
}