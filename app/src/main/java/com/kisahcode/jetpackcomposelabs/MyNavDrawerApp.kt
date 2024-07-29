package com.kisahcode.jetpackcomposelabs

import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.activity.compose.LocalOnBackPressedDispatcherOwner
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.kisahcode.jetpackcomposelabs.ui.theme.JetpackComposeLabsTheme
import kotlinx.coroutines.launch

/**
 * The main composable function for the navigation drawer application.
 *
 * This function sets up the navigation drawer along with a top bar and handles user interactions.
 * It manages the state of the drawer, displays a snackbar with an optional action, and handles
 * back press events to close the drawer if it's open.
 *
 * @param modifier Modifier to be applied to the layout.
 */
@Composable
fun MyNavDrawerApp(
    modifier: Modifier = Modifier
) {
    // State for managing the navigation drawer's open/closed state.
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()
    val snackbarHostState = remember { SnackbarHostState() } // State for showing snackbars.
    val context = LocalContext.current

    // List of menu items for the navigation drawer.
    val items = listOf(
        MenuItem(
            title = stringResource(R.string.home),
            icon = Icons.Default.Home
        ),
        MenuItem(
            title = stringResource(R.string.favourite),
            icon = Icons.Default.Favorite
        ),
        MenuItem(
            title = stringResource(R.string.profile),
            icon = Icons.Default.AccountCircle
        ),
    )

    // State to keep track of the currently selected menu item.
    val selectedItem = remember { mutableStateOf(items[0]) }

    // Handle back press to close the drawer if it's open.
    // for optional we can use.. BackHandler(enabled = drawerState.isOpen){
    BackPressHandler(enabled = drawerState.isOpen) {
        scope.launch {
            drawerState.close()
        }
    }

    Scaffold(
        snackbarHost = { SnackbarHost(snackbarHostState) },
        topBar = {
            MyTopBar(
                onMenuClick = {
                    scope.launch {
                        if (drawerState.isClosed) {
                            drawerState.open()
                        } else {
                            drawerState.close()
                        }
                    }
                }
            )
        },
    ) { paddingValues ->
        ModalNavigationDrawer(
            modifier = Modifier.padding(paddingValues),
            drawerState = drawerState,
            gesturesEnabled = drawerState.isOpen,
            drawerContent = {
                ModalDrawerSheet {
                    Spacer(Modifier.height(12.dp))
                    items.forEach { item ->
                        NavigationDrawerItem(
                            label = { Text(item.title) },
                            icon = { Icon(item.icon, null)},
                            selected = item == selectedItem.value,
                            onClick = {
                                scope.launch {
                                    drawerState.close()

                                    // Show a snackbar with an optional action.
                                    val snackbarResult = snackbarHostState.showSnackbar(
                                        message = context.resources.getString(R.string.coming_soon, item.title),
                                        actionLabel = context.resources.getString(R.string.subscribe_question),
                                        withDismissAction = true,
                                        duration = SnackbarDuration.Short
                                    )

                                    // Show a Toast if the user clicks the snackbar action.
                                    if (snackbarResult == SnackbarResult.ActionPerformed) {
                                        Toast.makeText(
                                            context,
                                            context.resources.getString(R.string.subscribed_info),
                                            Toast.LENGTH_SHORT
                                        ).show()
                                    }
                                }
                                selectedItem.value = item // Update the selected item.
                            },
                            modifier = Modifier.padding(horizontal = 12.dp)
                        )
                    }
                }
            },
            content = {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center,
                ) {
                    Text(
                        if (drawerState.isClosed) {
                            stringResource(R.string.swipe_to_open)
                        } else {
                            stringResource(R.string.swipe_to_close)
                        }
                    )
                }
            }
        )
    }
}

/**
 * A composable function that displays a top app bar with a menu button.
 *
 * This function creates a `TopAppBar` with a title and a navigation icon (menu button).
 * The menu button triggers a callback function when clicked.
 *
 * @param onMenuClick A lambda function to be called when the menu button is clicked.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyTopBar(
    onMenuClick: () -> Unit
) {
    TopAppBar(
        title = { Text(stringResource(R.string.app_name)) },
        navigationIcon = {
            IconButton(
                onClick = { onMenuClick() }
            ) {
                Icon(
                    imageVector = Icons.Default.Menu,
                    contentDescription = stringResource(R.string.menu)
                )
            }
        },
    )
}

/**
 * A data class representing an item in a menu.
 *
 * This class is used to define a menu item with a title and an icon.
 *
 * @property title The title of the menu item.
 * @property icon The icon associated with the menu item, represented as an `ImageVector`.
 */
data class MenuItem(
    val title: String,
    val icon: ImageVector
)

/**
 * A composable function that handles back press events.
 *
 * This function allows you to handle the back press event in a custom way by providing
 * a callback function to be invoked when the back button is pressed. The handling can
 * be enabled or disabled based on the `enabled` parameter.
 *
 * @param enabled A Boolean value that determines whether the back press handler is enabled or not. Default is true.
 * @param onBackPressed A lambda function to be called when the back button is pressed.
 */
@Composable
fun BackPressHandler(enabled: Boolean = true, onBackPressed: () -> Unit) {
    // Remember the current onBackPressed callback to avoid recomposition issues
    val currentOnBackPressed by rememberUpdatedState(onBackPressed)

    // Create an OnBackPressedCallback instance to handle the back press events
    val backCallback = remember {
        object : OnBackPressedCallback(enabled) {
            override fun handleOnBackPressed() {
                currentOnBackPressed()
            }

        }
    }

    // Update the enabled state of the backCallback whenever the enabled parameter changes
    SideEffect {
        backCallback.isEnabled = enabled
    }

    // Obtain the OnBackPressedDispatcher from the current context
    val backDispatcher = checkNotNull(LocalOnBackPressedDispatcherOwner.current) {
        "No OnBackPressedDispatcherOwner was provided via LocalOnBackPressedDispatcherOwner"
    }.onBackPressedDispatcher

    // Obtain the LifecycleOwner from the current context
    val lifecycleOwner = LocalLifecycleOwner.current

    // Use DisposableEffect to manage the lifecycle of the backCallback
    DisposableEffect(lifecycleOwner, backDispatcher) {
        backDispatcher.addCallback(lifecycleOwner, backCallback)
        onDispose {
            backCallback.remove()
        }
    }
}

/**
 * A preview function for the MyNavDrawerApp composable.
 *
 * This function is used to display a preview of the MyNavDrawerApp composable within the Android
 * Studio Preview panel. It wraps the MyNavDrawerApp composable in the JetpackComposeLabsTheme to
 * ensure the theme is applied.
 */
@Preview(showBackground = true)
@Composable
fun MyNavDrawerAppPreview() {
    JetpackComposeLabsTheme {
        MyNavDrawerApp()
    }
}