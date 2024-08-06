package com.kisahcode.jetpackcomposelabs.ui.navigation

/**
 * Represents different screens in the app's navigation system.
 *
 * @property route The route string associated with each screen.
 */
sealed class Screen(val route: String) {
    /**
     * The Home screen.
     */
    data object Home : Screen("home")

    /**
     * The Cart screen.
     */
    data object Cart : Screen("cart")

    /**
     * The Profile screen.
     */
    data object Profile : Screen("profile")
}