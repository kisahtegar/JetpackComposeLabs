package com.kisahcode.jetpackcomposelabs

import androidx.navigation.NavController
import org.junit.Assert

/**
 * Extension function for NavController to assert the current route name.
 *
 * This function simplifies the process of asserting that the current route name in the navigation
 * back stack matches an expected route name during UI tests. It helps ensure that the navigation
 * in the app behaves as expected.
 *
 * @param expectedRouteName The expected name of the current route.
 * @throws AssertionError if the current route name does not match the expected route name.
 */
fun NavController.assertCurrentRouteName(expectedRouteName: String) {
    Assert.assertEquals(expectedRouteName, currentBackStackEntry?.destination?.route)
}
