package com.kisahcode.jetpackcomposelabs

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.kisahcode.jetpackcomposelabs.ui.navigation.NavigationItem
import com.kisahcode.jetpackcomposelabs.ui.navigation.Screen
import com.kisahcode.jetpackcomposelabs.ui.screen.cart.CartScreen
import com.kisahcode.jetpackcomposelabs.ui.screen.detail.DetailScreen
import com.kisahcode.jetpackcomposelabs.ui.screen.home.HomeScreen
import com.kisahcode.jetpackcomposelabs.ui.screen.profile.ProfileScreen
import com.kisahcode.jetpackcomposelabs.ui.theme.JetpackComposeLabsTheme

/**
 * JetRewardApp is a composable function that sets up the main structure of the app.
 *
 * This function creates a scaffold with a bottom navigation bar and a navigation host. It manages
 * the navigation between different screens (Home, Cart, Profile, DetailReward) using the NavHostController.
 *
 * @param modifier A Modifier for customizing the appearance and behavior of the composable.
 * @param navController The NavHostController for managing the navigation stack, with a default value.
 */
@Composable
fun JetRewardApp(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
) {
    // Get the current back stack entry and the current route
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    Scaffold(
        bottomBar = {
            // Display the bottom bar only if the current route is not the DetailReward screen
            if (currentRoute != Screen.DetailReward.route) {
                BottomBar(navController)
            }
        },
        modifier = modifier
    ) { innerPadding ->
        // Set up the navigation host to manage navigation between screens
        NavHost(
            navController = navController,
            startDestination = Screen.Home.route,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(Screen.Home.route) {
                HomeScreen(
                    navigationToDetail = { rewardId ->
                        // Navigate to the DetailReward screen with the given rewardId
                        navController.navigate(Screen.DetailReward.createRoute(rewardId))
                    }
                )
            }
            composable(Screen.Cart.route) {
                CartScreen()
            }
            composable(Screen.Profile.route) {
                ProfileScreen()
            }
            // Composable for the DetailReward screen with rewardId as argument
            composable(
                Screen.DetailReward.route,
                arguments = listOf(navArgument("rewardId") { type = NavType.LongType })
            ) {
                // Get the rewardId from arguments
                val id = it.arguments?.getLong("rewardId") ?: -1L

                DetailScreen(
                    rewardId = id,
                    navigateBack = {
                        // Navigate back to the previous screen
                        navController.navigateUp()
                    },
                    navigateToCart = {
                        // Remove the current screen from the back stack
                        navController.popBackStack()
                        navController.navigate(Screen.Cart.route) {
                            popUpTo(navController.graph.findStartDestination().id) {
                                saveState = true
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    }
                )
            }
        }
    }
}

/**
 * BottomBar is a composable function that creates a bottom navigation bar for the app.
 *
 * This function dynamically generates navigation items based on the provided list of `NavigationItem`
 * objects. It updates the selected item based on the current navigation route and handles navigation
 * events when an item is clicked.
 *
 * @param navController The NavHostController for managing the navigation stack.
 * @param modifier A Modifier for customizing the appearance and behavior of the composable.
 */
@Composable
fun BottomBar(
    navController: NavHostController,
    modifier: Modifier = Modifier,
) {
    NavigationBar(
        modifier = modifier
    ) {
        // Get the current back stack entry to determine the current route
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route

        // Define a list of navigation items
        val navigationItem = listOf(
            NavigationItem(
                title = stringResource(R.string.menu_home),
                icon = Icons.Default.Home,
                screen = Screen.Home
            ),
            NavigationItem(
                title = stringResource(R.string.menu_cart),
                icon = Icons.Default.ShoppingCart,
                screen = Screen.Cart
            ),
            NavigationItem(
                title = stringResource(R.string.menu_profile),
                icon = Icons.Default.AccountCircle,
                screen = Screen.Profile
            ),
        )

        navigationItem.map { item ->
            NavigationBarItem(
                icon = {
                    Icon(
                        imageVector = item.icon,
                        contentDescription = item.title
                    )
                },
                label = { Text(item.title) },
                selected = currentRoute == item.screen.route,
                onClick = {
                    navController.navigate(item.screen.route) {
                        // Pop up to the start destination of the graph to avoid building up a large stack of destinations on the back stack as users select items
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true
                        }
                        // Avoid multiple copies of the same destination when reselecting the same item
                        restoreState = true
                        launchSingleTop = true
                    }
                },
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun JetHeroesAppPreview() {
    JetpackComposeLabsTheme {
        JetRewardApp()
    }
}